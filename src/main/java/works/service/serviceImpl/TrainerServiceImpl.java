package works.service.serviceImpl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import works.common.ResultDate;
import works.entity.*;
import works.mapper.TrainerMapper;
import works.service.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class TrainerServiceImpl extends ServiceImpl<TrainerMapper, Trainer> implements TrainerService {

    @Resource
    private TrainerMapper trainerMapper;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private UserService userService;

    @Autowired
    private StudentsService studentsService;

    @Autowired
    private CarService carService;

    @Override
    public ResultDate getpage(Page<Trainer> trainerPage, String name) {
        Page<Trainer> page=trainerMapper.getPage(trainerPage,name);
        return ResultDate.success(page);
    }
    //分页查询
    @Override
    public ResultDate page(int pageNum, int pageSize,String name) {

        Page<Trainer> page=new Page<>(pageNum,pageSize);

        LambdaQueryWrapper<Trainer> queryWrapper=new LambdaQueryWrapper<>();

        queryWrapper.eq(Trainer::getStatus,1);

        queryWrapper.like(StringUtils.isNotEmpty(name), Trainer::getName,name);

        page(page,queryWrapper);

        return ResultDate.success(page);
    }

    @Override
    public ResultDate<List<Trainer>> getAllName() {
        LambdaQueryWrapper<Trainer> queryWrapper=new LambdaQueryWrapper<>();
//        queryWrapper.select("name");
        queryWrapper.eq(Trainer::getStatus,1);

        List<Trainer> list=trainerMapper.selectList(queryWrapper);

        return ResultDate.success(list);
    }

    @Override
    public ResultDate saveTrainer(Trainer trainer) {


        if (trainer.getId()==null){
                User user=new User();
                user.setName(trainer.getName());
                user.setUsername(trainer.getUsername());
                user.setPassword("123456");
                user.setRole("TRAINER");
                userService.save(user);
        }
        LambdaQueryWrapper<User> userLambdaQueryWrapper=new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername,trainer.getUsername());
        User user=userService.getOne(userLambdaQueryWrapper);
        user.setName(trainer.getName());
        userService.saveOrUpdate(user);

        this.saveOrUpdate(trainer);


        return ResultDate.success(user);
    }

    @Override
    public ResultDate delTrainer(Long id) {
        Trainer trainer = this.getById(id);
        trainer.setStatus(0);
        this.saveOrUpdate(trainer);

        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername,trainer.getUsername());
        User user = userService.getOne(lambdaQueryWrapper);//删除用户
        user.setStatus(0);
        userService.saveOrUpdate(user);

        LambdaQueryWrapper<Score> scoreLambdaQueryWrapper=new LambdaQueryWrapper<>();
        scoreLambdaQueryWrapper.eq(Score::getTrainerId,id);
        scoreService.remove(scoreLambdaQueryWrapper);       // 删除关于教练的评价

//        LambdaQueryWrapper<Car> carlambdaQueryWrapper=new LambdaQueryWrapper<>();
//        carlambdaQueryWrapper.eq(Car::getTrainerId,trainer.getId());
//        Car car=carService.getOne(carlambdaQueryWrapper);          //将车辆教练置空
//        if (car!=null){
//            car.setTrainerId(null);
//            carService.saveOrUpdate(car);
//        }
        UpdateWrapper<Car> carUpdateWrapper=new UpdateWrapper<>();
        carUpdateWrapper.set("trainer_Id",null);
        carUpdateWrapper.eq("trainer_Id",trainer.getId());       //将车辆教练置空
        carService.update(null,carUpdateWrapper);

        UpdateWrapper<Students> studentsUpdateWrapper=new UpdateWrapper<>();
        studentsUpdateWrapper.set("trainer_Id",null);
        studentsUpdateWrapper.eq("trainer_Id",trainer.getId());       //将学生教练置空
        studentsService.update(null,studentsUpdateWrapper);

        return ResultDate.success("ok");
    }


    @Override
    public ResultDate getByUsername(String username) {
        Trainer trainer =trainerMapper.getByUsername(username);
        return ResultDate.success(trainer);
    }
}
