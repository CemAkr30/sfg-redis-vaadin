package cakar.sfgrv.sfgredisvaadin.dto;

import cakar.sfgrv.sfgredisvaadin.dto.baseAbstractDto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDTO  extends BaseDto {
    private String name;
    private String surname;
    private String identityNumber;
    private boolean islemTuru=false;
}
