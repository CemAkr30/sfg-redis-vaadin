package cakar.sfgrv.sfgredisvaadin.service.jpaService;


import cakar.sfgrv.sfgredisvaadin.dto.UserDTO;
import cakar.sfgrv.sfgredisvaadin.genUtils.GenUtilMap;
import cakar.sfgrv.sfgredisvaadin.model.User;
import cakar.sfgrv.sfgredisvaadin.repositories.UserRepository;
import cakar.sfgrv.sfgredisvaadin.service.UserService;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.Set;

import static cakar.sfgrv.sfgredisvaadin.utilsAbstract.BaseDefault.checkNullStr;
import static cakar.sfgrv.sfgredisvaadin.utilsAbstract.BaseDefault.isEmptySet;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

//    @InitBinder
//    public void initBinder(WebDataBinder webDataBinder){
//        webDataBinder.addValidators(new UserValidation());
//    }

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<User>  userList = (List<User>) userRepository.findAll();
        return  new GenUtilMap<UserDTO, User>().pojoToListDto(new UserDTO(),userList);
    }

    @Override
    public List<UserDTO> findByIdentityNumberStartsWithIgnoreCase(String identityNumber) {
        return new GenUtilMap<UserDTO,User>().pojoToListDto(new UserDTO(),userRepository.findByIdentityNumberStartsWithIgnoreCase(identityNumber));
    }

    @Override
    public List<UserDTO> findBySurnameStartsWithIgnoreCase(String surname) {
        return new GenUtilMap<UserDTO,User>().pojoToListDto(new UserDTO(),userRepository.findBySurnameStartsWithIgnoreCase(surname));
    }

    @Override
    public List<UserDTO> findByNameStartsWithIgnoreCase(String name) {
        return new GenUtilMap<UserDTO,User>().pojoToListDto(new UserDTO(),userRepository.findByNameStartsWithIgnoreCase(name));
    }

    //@Validated
    public UserDTO saveUser(UserDTO userDTO){
        if(checkNullStr(userDTO.getIdentityNumber()) || checkNullStr(userDTO.getName()) || checkNullStr(userDTO.getSurname())){
            throw new RuntimeException("Kayıt edilmek istenilen alanlar boş olamaz...");
        }
        User user = new GenUtilMap<UserDTO,User>().dtoToPojo(userDTO,new User());
        try {
            userRepository.save(user);
        }catch (Exception e) {
           boolean j = e.getMessage().contains("constraint");
           if(j){
               throw new RuntimeException("Aynı Kimlik Numarasına Sahip Kayıt Olamaz...");
           }
        }
        userDTO.setIslemTuru(true);
        return userDTO;
    }
    @Override
    public void deleteUserAll() {
        userRepository.deleteAll();
    }

    @Override
    public boolean deleteUsers(Set<UserDTO> userDTO) {
        if(isEmptySet(userDTO)){
            return false;
        }
        for(UserDTO rec : userDTO) {
            User user = new GenUtilMap<UserDTO, User>().dtoToPojo(rec, new User());
            userRepository.delete(user);
        }
        return true;
    }

    @Override
    public boolean randomUserCreate() {
        userRepository.save(userCreate());
        return true;
    }

    private User userCreate(){
        User user = new User();
        Faker faker = new Faker();
        user.setName(faker.name().firstName());
        user.setSurname(faker.name().lastName());
        user.setIdentityNumber(getTckn());
        return user;
    }

    static String getTckn() {
        Random rn = new Random();
        int[] tckn = new int[11];
        for (int i = 0; i <= 8; i++) {
            int random = rn.nextInt(9) + 1;
            if (i == 0) {
                while (!(random > 0)) {
                    random = rn.nextInt(9) + 1;
                }
            }
            tckn[i] = random;
        }
        int toplamTek = 0;
        int toplamCift = 0;

        for (int i = 0; i <= 8; i++) {
            if ((i % 2 > 0)) {
                toplamTek += tckn[i];
            } else {
                toplamCift += tckn[i];

            }
        }
        tckn[9] = (((toplamCift * 7) - toplamTek) % 10);
        int toplam = 0;
        for (int i = 0; i <= 9; i++) {
            toplam += tckn[i];
        }
        tckn[10] = toplam % 10;
        String tcknS = "";
        for (int i = 0; i < 11; i++) {
            tcknS += String.valueOf(tckn[i]);
        }
        return tcknS;

    }
}
