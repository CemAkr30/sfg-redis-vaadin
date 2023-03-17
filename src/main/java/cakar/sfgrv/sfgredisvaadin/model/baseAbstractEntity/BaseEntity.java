package cakar.sfgrv.sfgredisvaadin.model.baseAbstractEntity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@MappedSuperclass
@Data
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
}
