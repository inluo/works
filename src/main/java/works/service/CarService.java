package works.service;

import com.baomidou.mybatisplus.extension.service.IService;
import works.common.ResultDate;
import works.entity.Car;

public interface CarService extends IService<Car> {

    ResultDate page(int pageNum , int pageSize,String num);

}
