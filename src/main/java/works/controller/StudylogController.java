package works.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import works.common.ResultDate;
import works.entity.Studylog;
import works.service.StudylogService;

import java.util.List;

@RestController
@RequestMapping("/studylog")
public class StudylogController {

    @Autowired
    private StudylogService studylogService;

    @GetMapping
    public ResultDate page(Integer pageNum,Integer pageSize,String trainerName,String studentName){
        Page<Studylog> page=studylogService.getpage(new Page<>(pageNum,pageSize),trainerName,studentName);
        return ResultDate.success(page);
    }

    /**
     * 根据id,名字查询教练日志
     * @param pageNum
     * @param pageSize
     * @param id
     * @return
     */
    @GetMapping("/id")
    public ResultDate trainerId(Integer pageNum,Integer pageSize,Integer id,String studentname){
        Page<Studylog> page=studylogService.pageByTrianer(new Page<>(pageNum,pageSize),id,studentname);
        return ResultDate.success(page);
    }

    /**
     * 根据学生id查询学生日志
     * @param pageNum
     * @param pageSize
     * @param id
     * @return
     */
    @GetMapping("/studentid")
    public ResultDate studentId(Integer pageNum,Integer pageSize,Integer id){
        Page<Studylog> page=studylogService.pageByStudentId(new Page<>(pageNum,pageSize),id);
        return ResultDate.success(page);
    }

    @DeleteMapping("/del/{id}")
    public ResultDate del(@PathVariable Integer id){
        return ResultDate.success(studylogService.removeById(id));
    }

    @GetMapping("/update/{id}")
    public ResultDate update(@PathVariable Integer id){
        return studylogService.updatesave(id);
    }

    @PostMapping("/add")
    public ResultDate addlog(@RequestBody Studylog studylog){
        return studylogService.savelog(studylog);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/batch")
    public ResultDate delBatch(@RequestBody List<Long> ids){
        return studylogService.delByIds(ids);
    }
}
