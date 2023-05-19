package works.entity.Dto;

import lombok.Data;
import works.entity.User;

@Data
public class UserDto extends User {
    private String token;
}
