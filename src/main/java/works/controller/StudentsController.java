package works.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import works.common.ResultDate;
import works.entity.Students;
import works.service.StudentsService;

import java.util.List;

@RestController
@RequestMapping("/students")
@CrossOrigin
@Slf4j
public class StudentsController {

    @Autowired
    private StudentsService studentsService;

    @GetMapping
    public ResultDate page(int pageNum, int pageSize,String name){
        return  studentsService.page(pageNum,pageSize, name);
    }

    @GetMapping("/{id}")
    public ResultDate page(@PathVariable int id){
        return  ResultDate.success(studentsService.getById(id));
    }

    @PostMapping
    public ResultDate save(@RequestBody Students students){
        return studentsService.saveStudents(students);
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultDate del(@PathVariable int id){
        return studentsService.delStudents(id);
    }

//    /**
//     * 批量删除
//     * @param ids
//     * @return
//     */
//    @PostMapping("/batch")
//    public ResultDate delBatch(@RequestBody List<Long> ids){
//        return studentsService.delByIds(ids);
//    }

    @GetMapping("/getone/{id}")
    public ResultDate getOne(@PathVariable int id){
        return  ResultDate.success(studentsService.getById(id));
    }

    //通过username查询获取对象
    @GetMapping("/username/{username}")
    public ResultDate getOne(@PathVariable String username){
        return  studentsService.getByUsername(username);
    }

    //考试通过
    @PostMapping("/example")
    public ResultDate example(@RequestBody Students students){
        students.setStudying(students.getStudying()+1);
        return  ResultDate.success(studentsService.saveOrUpdate(students));
    }

    //教练请求学生数据
    @GetMapping("/bytrainer")
    public ResultDate pageByTrainer(int pageNum, int pageSize,String name,Integer trainerId){
        return studentsService.pageByTrainer(pageNum,pageSize,name,trainerId);
    }

    //获取通过的学生
    @GetMapping("/all")
    public ResultDate getall(){
        return studentsService.getAll();
    }



}
