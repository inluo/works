package works.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import works.common.ResultDate;
import works.entity.Score;
import works.service.ScoreService;

import java.util.List;

@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

//    @GetMapping("/studentid")
//    public ResultDate getOByStudent(Integer id){
//        return scoreService.getByStudent(id);
//    }

    @PostMapping
    public ResultDate saveScore(@RequestBody Score score){
        return scoreService.scorecontent(score);
    }


    //管理员查看评价内容
    @GetMapping("/page")
    public ResultDate page(Integer pageNum,Integer pageSize,String trainerName,String studentName){
        ResultDate r = scoreService.getPage(new Page<>(pageNum, pageSize), trainerName, studentName);
        return r;
    }

    //根据id查询内容
    @GetMapping("/{id}")
    public ResultDate getById(@PathVariable Integer id){
        Score byId = scoreService.getById(id);
        if (byId==null){
            return ResultDate.error("暂无评价");
        }
        return ResultDate.success(byId);
    }

    //根据id查询内容
    @GetMapping("/trainer/{id}")
    public ResultDate getByTrainer(@PathVariable Integer id){
        return scoreService.getByTrainer(id);
    }

    //根据id查询内容
    @DeleteMapping("/del/{id}")
    public ResultDate del(@PathVariable Integer id){
        return ResultDate.success(scoreService.removeById(id));
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/batch")
    public ResultDate delBatch(@RequestBody List<Long> ids){
        return scoreService.delByIds(ids);
    }


}
