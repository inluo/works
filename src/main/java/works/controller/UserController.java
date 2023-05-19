package works.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import works.common.ResultDate;
import works.entity.Dto.UserDto;
import works.entity.User;
import works.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    //登录
    @PostMapping("/login")
    public ResultDate login(@RequestBody User user){
        return  userService.login(user);
    }

    //注册
    @PostMapping("/register")
    public ResultDate register(@RequestBody User user){
        return  userService.register(user);
    }

    @GetMapping("/username/{username}")
    public ResultDate getByOne(@PathVariable String username){

        return userService.getByUserName(username);
    }

    @PostMapping("/updatepassword")
    public ResultDate getByOne(@RequestBody User user){

        return ResultDate.success(userService.saveOrUpdate(user));
    }
}
