package works.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import works.entity.Trainer;

public interface TrainerMapper extends BaseMapper<Trainer> {
    Page<Trainer> getPage(Page<Trainer> trainerPage, @Param("name") String name);

    Trainer getByUsername(@Param("username") String username);
}
