package works.service;

import com.baomidou.mybatisplus.extension.service.IService;
import works.common.ResultDate;
import works.entity.Dto.UserDto;
import works.entity.User;

public interface UserService extends IService<User> {

    ResultDate login(User user);

    ResultDate register(User user);

    ResultDate getByUserName(String username);

//    ResultDate updatePassword(UserDto userDto);
}
