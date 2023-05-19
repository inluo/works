package works.service;

import com.baomidou.mybatisplus.extension.service.IService;
import works.common.ResultDate;
import works.entity.Students;

import java.util.List;

public interface StudentsService extends IService<Students> {

    ResultDate page(int pageNum , int pageSize,String name);

    ResultDate saveStudents(Students students);

    ResultDate delStudents(int id);

//    ResultDate delByIds(List<Long> ids);

    ResultDate getByUsername(String username);

    ResultDate pageByTrainer(int pageNum, int pageSize, String name, Integer trainerId);

    ResultDate getAll();
}
