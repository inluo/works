package works.service.serviceImpl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import works.common.ResultDate;
import works.entity.*;
import works.entity.Dto.StudentsDto;
import works.mapper.StudentsMapper;
import works.service.StudentsService;
import works.service.TrainerService;
import works.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentsServiceImpl extends ServiceImpl<StudentsMapper, Students> implements StudentsService {

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private UserService userService;

    @Override
    public ResultDate page(int pageNum, int pageSize,String name) {
        Page<Students> page=new Page<>(pageNum,pageSize);
        Page<StudentsDto> studentsDtoPage = new Page<>();

        LambdaQueryWrapper<Students> queryWrapper=new LambdaQueryWrapper<>();

        queryWrapper.eq(Students::getStatus,1);
        queryWrapper.like(StringUtils.isNotEmpty(name), Students::getName,name);

        Page<Students> students = page(page, queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(students, studentsDtoPage, "records");

        List<Students> records=students.getRecords();

        List<StudentsDto> list = records.stream().map((item) -> {
            StudentsDto studentsDto = new StudentsDto();

            BeanUtils.copyProperties(item, studentsDto);

            Integer trainerId = item.getTrainerId();//教练id

            //根据id查询分类对象
            Trainer trainer = trainerService.getById(trainerId);
            if (trainer != null&&trainer.getStatus()==1) {
                String trainerName = trainer.getName();
                studentsDto.setTrainerName(trainerName);
            }

            return studentsDto;

        }).collect(Collectors.toList());

        studentsDtoPage.setRecords(list);

        return ResultDate.success(studentsDtoPage);
    }

    @Override
    public ResultDate saveStudents(Students students) {

        if (students.getId()==null){

//        if (user==null){
            User user=new User();
            user.setName(students.getName());
            user.setUsername(students.getUsername());
            user.setPassword("123456");
            user.setRole("STUDENTS");
            userService.save(user);
//        }
        }
        this.saveOrUpdate(students);
        LambdaQueryWrapper<User> userLambdaQueryWrapper=new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername,students.getUsername());
        User user=userService.getOne(userLambdaQueryWrapper);
        user.setName(students.getName());
        userService.saveOrUpdate(user);

        return ResultDate.success(user);
    }

    @Override
    public ResultDate delStudents(int id) {
        Students students = this.getById(id);
        students.setStatus(0);
        this.saveOrUpdate(students);


        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername,students.getUsername());
        User user = userService.getOne(lambdaQueryWrapper);//删除用户
        user.setStatus(0);
        userService.saveOrUpdate(user);

        return ResultDate.success("ok");
    }



    @Override
    public ResultDate getByUsername(String username) {
        LambdaQueryWrapper<Students> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Students::getUsername,username);
        Students students = getOne(queryWrapper);

        StudentsDto studentsDto=new StudentsDto();
        BeanUtils.copyProperties(students,studentsDto);
        Integer trainerId = students.getTrainerId();
        //获取教练名字
        if (trainerId!=null){
            String trainer = trainerService.getById(students.getTrainerId()).getName();
            studentsDto.setTrainerName(trainer);

            return ResultDate.success(studentsDto);
        }
        return ResultDate.success(students);
    }

    @Override
    public ResultDate pageByTrainer(int pageNum, int pageSize, String name, Integer trainerId) {
        Page<Students> page=new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<Students> queryWrapper=new LambdaQueryWrapper<>();

        queryWrapper.eq(Students::getStatus,1);
        queryWrapper.like(StringUtils.isNotEmpty(name),Students::getName,name);
        queryWrapper.eq(Students::getTrainerId,trainerId);
        queryWrapper.orderByAsc(Students::getStudying);
        this.page(page,queryWrapper);

        return ResultDate.success(page);
    }

    @Override
    public ResultDate getAll() {
        LambdaQueryWrapper<Students> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Students::getStudying,5);
        int count = count(queryWrapper);
        return ResultDate.success(count);
    }
}
