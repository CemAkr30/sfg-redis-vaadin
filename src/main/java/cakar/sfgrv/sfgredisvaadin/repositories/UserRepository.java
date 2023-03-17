package cakar.sfgrv.sfgredisvaadin.repositories;



import cakar.sfgrv.sfgredisvaadin.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, String> {

    List<User> findByIdentityNumberStartsWithIgnoreCase(String name);
    List<User> findBySurnameStartsWithIgnoreCase(String surname);
    List<User> findByNameStartsWithIgnoreCase(String name);
}
