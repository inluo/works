package works.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import works.common.ResultDate;
import works.entity.Studylog;

import java.util.List;

public interface StudylogService extends IService<Studylog> {

//   ResultDate getpage(Integer pageNum,Integer pageSize,String trainerName,String studentName);

//    ResultDate getpageById(Integer pageNum, Integer pageSize, int id,String studentname);

    ResultDate updatesave(Integer id);

//    ResultDate getpagestu(Integer pageNum, Integer pageSize, Integer id);

    ResultDate savelog(Studylog studylog);

    Page<Studylog> getpage(Page<Studylog> objectPage, String trainerName, String studentName);

    Page<Studylog> pageByStudentId(Page<Studylog> objectPage, Integer id);

    Page<Studylog> pageByTrianer(Page<Studylog> objectPage, Integer id, String studentname);

    ResultDate delByIds(List<Long> ids);
}

