package works.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import works.common.ResultDate;
import works.entity.Students;
import works.entity.Trainer;
import works.service.StudentsService;
import works.service.TrainerService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/echarts")
@CrossOrigin
@Slf4j
public class EchartsController {

    @Autowired
    private StudentsService studentsService;

    @Autowired
    private TrainerService trainerService;


    @GetMapping("/charts1")
    public ResultDate charts1(){

        List<Integer> list=new ArrayList<>();

        for (int studying=1;studying<=5;studying++){
            LambdaQueryWrapper<Students> queryWrapper=new LambdaQueryWrapper<>();
            queryWrapper.eq(Students::getStudying,studying);
            queryWrapper.eq(Students::getStatus,1);
            int count = studentsService.count(queryWrapper);
            list.add(count);
        }
        return ResultDate.success(list);
    }

    @GetMapping("/charts2")
    public ResultDate charts2(){
        List<Integer> list=new ArrayList<>();
        LambdaQueryWrapper<Trainer> trainerLambdaQueryWrapper=new LambdaQueryWrapper<>();
        trainerLambdaQueryWrapper.eq(Trainer::getStatus,1);
        int trainers = trainerService.count(trainerLambdaQueryWrapper);

        LambdaQueryWrapper<Students> studentsLambdaQueryWrapper=new LambdaQueryWrapper<>();
        studentsLambdaQueryWrapper.eq(Students::getStatus,1);
        int students = studentsService.count(studentsLambdaQueryWrapper);

        list.add(trainers);
        list.add(students);

        return ResultDate.success(list);
    }
}
