package works.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
//学员实体类

@Data
public class Students {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer status;

    private String username;

    private String name;

    private String sex;

    private String phone;

    private String idNumber;

    private Integer studying;

    private Integer studytime;

    private Integer trainerId;

    private Integer content;

}
