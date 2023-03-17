package cakar.sfgrv.sfgredisvaadin.service;



import cakar.sfgrv.sfgredisvaadin.dto.UserDTO;

import java.util.List;
import java.util.Set;


public interface UserService {

    List<UserDTO> getAllUser();
    List<UserDTO> findByIdentityNumberStartsWithIgnoreCase(String identityNumber);
    List<UserDTO> findBySurnameStartsWithIgnoreCase(String surname);
    List<UserDTO> findByNameStartsWithIgnoreCase(String name);
    UserDTO saveUser(UserDTO userDTO);
    void deleteUserAll();

    boolean deleteUsers(Set<UserDTO> userDTO);

    boolean randomUserCreate();
}
