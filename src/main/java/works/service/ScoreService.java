package works.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import works.common.ResultDate;
import works.entity.Score;

import java.util.List;

public interface ScoreService extends IService<Score> {
    ResultDate scorecontent(Score score);

    ResultDate getPage(Page<Score> page, String trainerName, String studentName);

    ResultDate getByTrainer(Integer id);

    ResultDate delByIds(List<Long> ids);
}
