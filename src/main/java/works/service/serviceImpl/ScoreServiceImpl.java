package works.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import works.common.ResultDate;
import works.entity.Score;
import works.entity.Students;
import works.mapper.ScoreMapper;
import works.service.ScoreService;
import works.service.StudentsService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements ScoreService {

    @Autowired
    private StudentsService studentsService;

    @Resource
    private ScoreMapper scoreMapper;

    @Override
    public ResultDate scorecontent(Score score) {

        Students students = studentsService.getById(score.getStudentId());  //获取学生对象
        students.setContent(0);         //设置禁止评论
        studentsService.updateById(students);
        this.save(score);
        return ResultDate.success("ok");
    }

    @Override
    public ResultDate getPage(Page<Score> page, String trainerName, String studentName) {
        Page<Score> getpage=scoreMapper.getPage(page,trainerName,studentName);
        return ResultDate.success(getpage);
    }

    //根据教练查评价
    @Override
    public ResultDate getByTrainer(Integer id) {
        LambdaQueryWrapper<Score> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Score::getTrainerId,id);
        queryWrapper.orderByDesc(Score::getCreateTime);
        List<Score> list = list(queryWrapper);
        if (list.size()==0){
            return ResultDate.error("暂无评价");
        }
        return ResultDate.success(list);
    }

    //批量删除
    @Override
    public ResultDate delByIds(List<Long> ids) {
        this.removeByIds(ids);
        return ResultDate.success("删除成功");
    }


}
