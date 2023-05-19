package works.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import works.entity.Studylog;

public interface StudylogMapper extends BaseMapper<Studylog> {

    Page<Studylog> getpage(Page<Studylog> objectPage, @Param("trainerName") String trainerName, @Param("studentName")String studentName);

    Page<Studylog> pageByStudentId(Page<Studylog> objectPage, @Param("id")Integer id);

    Page<Studylog> pageByTrainer(Page<Studylog> objectPage, @Param("id")Integer id, @Param("studentName")String studentname);
}
