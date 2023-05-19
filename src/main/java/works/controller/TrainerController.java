package works.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import works.common.ResultDate;
import works.entity.Trainer;
import works.service.TrainerService;

import java.util.List;

@RestController
@RequestMapping("/trainer")
@CrossOrigin
@Slf4j
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @GetMapping
    public ResultDate page(int pageNum,int pageSize,String name){

        return  trainerService.getpage(new Page<Trainer>(pageNum,pageSize),name);
    }

//    @GetMapping
//    public ResultDate page(int pageNum,int pageSize,String name){
//        return  trainerService.page(pageNum,pageSize,name);
//    }


    @GetMapping("/list")
    public ResultDate getList(){
        return trainerService.getAllName();
    }

    /**
     * 保存
     * @param trainer
     * @return
     */
    @PostMapping
    public ResultDate save(@RequestBody Trainer trainer){
        return trainerService.saveTrainer(trainer);
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultDate del(@PathVariable Long id){
        return trainerService.delTrainer(id);
    }

//    /**
//     * 批量删除
//     * @param ids
//     * @return
//     */
//    @PostMapping("/batch")
//    public ResultDate delBatch(@RequestBody List<Long> ids){
//        return trainerService.delByIds(ids);
//    }

    //教练接口，通过username查询获取对象
    @GetMapping("/{username}")
    public ResultDate getOne(@PathVariable String username){
        return  trainerService.getByUsername(username);
    }

}
