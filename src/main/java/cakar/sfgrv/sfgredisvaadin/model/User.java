package cakar.sfgrv.sfgredisvaadin.model;

import cakar.sfgrv.sfgredisvaadin.model.baseAbstractEntity.BaseEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.redis.core.RedisHash;

@Data
@EqualsAndHashCode // This is the key in Redis -> Entity ile eş değerdir Redis mantığı kalıcı olarak verileri tutmaz sadece key value mantığı ile çalışır.
@RedisHash("users")  // ihtiyaca göre verileri çekeriz. Bu yüzden RedisHash ile key değerini veriyoruz.
public class User extends BaseEntity{
    private String name;
    private String surname;
    private String identityNumber;
}
