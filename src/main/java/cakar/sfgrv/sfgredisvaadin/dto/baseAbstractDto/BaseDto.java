package cakar.sfgrv.sfgredisvaadin.dto.baseAbstractDto;


import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.io.Serializable;

@MappedSuperclass
@Data
public class BaseDto implements Serializable {
    private String id;
}
