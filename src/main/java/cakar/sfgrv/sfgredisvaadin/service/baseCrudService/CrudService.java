package cakar.sfgrv.sfgredisvaadin.service.baseCrudService;

import java.util.Set;

public interface CrudService<T, ID> {

    Set<T> findAll();

    T findById(ID id);

    T save(T object);

    void delete(T object);

    void deleteById(ID id);

    void deleteAll();

}
