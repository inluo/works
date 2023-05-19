package works.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
/*
    员工实体类
 */

@Data
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String name;

//    private String password;

    private String phone;

    private String sex;

    private String idNumber;//身份证号

}
