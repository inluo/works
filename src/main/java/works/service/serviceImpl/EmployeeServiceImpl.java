package works.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import works.common.ResultDate;
import works.entity.Employee;
import works.mapper.EmployeeMapper;
import works.service.EmployeeService;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public ResultDate page(int pageNum, int pageSize) {

        Page<Employee> page=new Page<>(pageNum,pageSize);

        LambdaQueryWrapper<Employee> queryWrapper=new LambdaQueryWrapper<>();

        page(page,queryWrapper);

        return ResultDate.success(page);
    }

    @Override
    public ResultDate getByUsername(String username) {
        LambdaQueryWrapper<Employee> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,username);
//        Employee employee = employeeMapper.selectOne(queryWrapper);
        Employee employee = getOne(queryWrapper);
        return ResultDate.success(employee);
    }
}
