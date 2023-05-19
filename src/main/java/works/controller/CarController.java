package works.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import works.common.ResultDate;
import works.entity.Car;
import works.service.CarService;

import org.springframework.stereotype.Controller;


@RestController
@RequestMapping("/car")
@CrossOrigin
public class CarController {

        @Resource
        private CarService carService;

        @GetMapping
        public ResultDate page(int pageNum, int pageSize, String num){
                return  carService.page(pageNum,pageSize,num);
        }

        @PostMapping
        public ResultDate saveCar(@RequestBody Car car){
                return ResultDate.success(carService.saveOrUpdate(car));
        }

        @DeleteMapping("/{id}")
        public ResultDate delCar(@PathVariable int id){
                return ResultDate.success(carService.removeById(id));
        }

        @GetMapping("/all")
        public ResultDate all(){
                return  ResultDate.success(carService.count());
        }


}

