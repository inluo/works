package works.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回结果类，服务端响应的数据最终都会封装成此对象
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDate<T> implements Serializable {

    private Integer code; //编码：1成功，0和其它数字为失败

    private String msg; //错误信息

    private T data; //数据

    private Map map = new HashMap(); //动态数据


    public static <T> ResultDate<T> success(T object) {
        ResultDate<T> result = new ResultDate<T>();
        result.data = object;
        result.code = 1;
        return result;
    }


    public static <T> ResultDate<T> error(String msg) {
        ResultDate result = new ResultDate();
        result.msg = msg;
        result.code = 0;
        return result;
    }

    public ResultDate<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

    public ResultDate(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
