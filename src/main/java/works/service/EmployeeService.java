package works.service;

import com.baomidou.mybatisplus.extension.service.IService;
import works.common.ResultDate;
import works.entity.Employee;

public interface EmployeeService extends IService<Employee>{

    ResultDate  page(int pageNum ,int pageSize);

    ResultDate  getByUsername(String username);



}
