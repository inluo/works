package works.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import works.common.ResultDate;
import works.entity.Students;
import works.entity.Studylog;
import works.mapper.StudentsMapper;
import works.mapper.StudylogMapper;
import works.service.StudentsService;
import works.service.StudylogService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudylogServiceImpl extends ServiceImpl<StudylogMapper, Studylog> implements StudylogService {

    @Autowired
    private StudentsService studentsService;

    @Resource
    private StudylogMapper studylogMapper;

    //请求日志分页数据， 可以根据教练名字和学员名字
    @Override
    public Page<Studylog> getpage(Page<Studylog> objectPage, String trainerName, String studentName) {
        Page<Studylog> getpage = studylogMapper.getpage(objectPage, trainerName, studentName);
        return getpage;
    }

    //学员页面请求练习日志
    @Override
    public Page<Studylog> pageByStudentId(Page<Studylog> objectPage, Integer id) {
        Page<Studylog> getpage = studylogMapper.pageByStudentId(objectPage, id);
        return getpage;
    }

    //教练请求教学日志
    @Override
    public Page<Studylog> pageByTrianer(Page<Studylog> objectPage, Integer id, String studentname) {
        Page<Studylog> getpage = studylogMapper.pageByTrainer(objectPage, id,studentname);
        return getpage;
    }

    //批量删除
    @Override
    public ResultDate delByIds(List<Long> ids) {
        this.removeByIds(ids);
        return ResultDate.success("删除成功");
    }

    @Override
    public ResultDate updatesave(Integer id) {
        Studylog log = getById(id);  //获取对象
        log.setStatus(log.getStatus()+1);//更新状态
        saveOrUpdate(log);

        //当学员签退成功，学时加一
        if (log.getStatus()==4){
            Students students = studentsService.getById(log.getStudentId());
            students.setStudytime(students.getStudytime()+1);
            studentsService.saveOrUpdate(students);
        }

        return ResultDate.success(log);
    }

    /**
     * 学生发起预约
     * @param studylog
     * @return
     */
    @Override
    public ResultDate savelog(Studylog studylog) {
        save(studylog);
        return ResultDate.success("true");
    }


}
