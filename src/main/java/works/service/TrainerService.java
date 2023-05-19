package works.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import works.common.ResultDate;
import works.entity.Trainer;

import java.util.List;

public interface TrainerService extends IService<Trainer> {
    ResultDate page(int pageNum , int pageSize,String name);

    ResultDate getAllName();

    ResultDate saveTrainer(Trainer trainer);

    ResultDate delTrainer(Long id);

//    ResultDate delByIds(List<Long> ids);

    ResultDate getByUsername(String username);

    ResultDate getpage(Page<Trainer> trainerPage, String name);
}
