package works.service.serviceImpl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import works.common.ResultDate;
import works.entity.Dto.UserDto;
import works.entity.Students;
import works.entity.Trainer;
import works.entity.User;
import works.mapper.UserMapper;
import works.service.StudentsService;
import works.service.TrainerService;
import works.service.UserService;
import works.utils.TokenUtils;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private StudentsService studentsService;

    @Autowired
    private TrainerService trainerService;
    @Override
    public ResultDate login(User user) {
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();

//        log.info(user.getUsername()+"=====>>"+user.getPassword());
        queryWrapper.eq(User::getUsername,user.getUsername());
        queryWrapper.eq(User::getPassword,user.getPassword());
        User one = this.getOne(queryWrapper);
        UserDto userDto=new UserDto();
        if (one!=null){
            if (one.getStatus()==0){
                return ResultDate.error("该用户不能登录");
            }
            //设置token
            BeanUtil.copyProperties(one,userDto,true);
            String token= TokenUtils.genToken(String.valueOf(one.getId()),one.getPassword());
            userDto.setToken(token);
            return ResultDate.success(userDto);
        }else{
            return ResultDate.error("账号或者密码错误");
        }
    }

    @Override
    public ResultDate register(User user) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        User one = getOne(queryWrapper);
        if (one!=null){
            return ResultDate.error("当前用户名已存在");
        }
        this.save(user);
        if(user.getRole().equals("STUDENTS")){
            Students students=new Students();
            BeanUtil.copyProperties(user,students,"id");
            students.setContent(1);
            studentsService.save(students);
        }else {
            Trainer trainer=new Trainer();
            BeanUtil.copyProperties(user,trainer,"id");
            trainerService.save(trainer);
        }

        return ResultDate.success(user);
    }

    @Override
    public ResultDate getByUserName(String username) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User one = this.getOne(queryWrapper);
        return ResultDate.success(one);
    }



}

