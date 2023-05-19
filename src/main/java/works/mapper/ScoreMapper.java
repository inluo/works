package works.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import works.entity.Score;

public interface ScoreMapper extends BaseMapper<Score> {

    Page<Score> getPage(Page<Score> page, @Param("trainerName") String trainerName, @Param("studentName") String studentName);

}
