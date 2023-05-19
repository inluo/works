package works.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/*
    教练实体类
 */
@Data
public class Trainer  implements Serializable {

//    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer status;

    private String username;

    private String name;

//    private String password;

    private String phone;

    private String sex;

    private String idNumber;//身份证号

    @TableField(exist = false)
    private double avgscore;

    @TableField(exist = false)
    private String carNum;
}
