package works.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import works.common.ResultDate;
import works.entity.Employee;
import works.entity.User;
import works.service.EmployeeService;
import works.service.UserService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResultDate page(int pageNum,int pageSize){
        return  employeeService.page(pageNum,pageSize);
    }

    @GetMapping("/{username}")
    public ResultDate getOne(@PathVariable String username){
        return  employeeService.getByUsername(username);
    }

    @PostMapping
    public ResultDate save(@RequestBody Employee employee){
        employeeService.saveOrUpdate(employee);

        LambdaQueryWrapper<User> userLambdaQueryWrapper=new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername,employee.getUsername());
        User user=userService.getOne(userLambdaQueryWrapper);
        user.setName(employee.getName());
        userService.saveOrUpdate(user);

        return  ResultDate.success(user);
    }

}
