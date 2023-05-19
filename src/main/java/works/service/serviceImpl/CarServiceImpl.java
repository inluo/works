package works.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import works.common.ResultDate;
import works.entity.Car;
import works.entity.Dto.CarDto;
import works.entity.Trainer;
import works.mapper.CarMapper;
import works.service.CarService;
import works.service.TrainerService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CarServiceImpl  extends ServiceImpl<CarMapper, Car> implements CarService {

    @Autowired
    private TrainerService trainerService;

    @Override
    public ResultDate page(int pageNum, int pageSize,String num) {
        Page<Car> page=new Page<>(pageNum,pageSize);
        Page<CarDto> CarDtoPage = new Page<>();

        LambdaQueryWrapper<Car> queryWrapper=new LambdaQueryWrapper<>();

        queryWrapper.like(StringUtils.isNotEmpty(num), Car::getNum,num);

        Page<Car> car = page(page, queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(car, CarDtoPage, "records");

        List<Car> records=car.getRecords();

        List<CarDto> list = records.stream().map((item) -> {
            CarDto carDto = new CarDto();

            BeanUtils.copyProperties(item, carDto,"true");

            Integer trainerId = item.getTrainerId();//教练id

            //根据id查询教练对象
            Trainer trainer = trainerService.getById(trainerId);
            if (trainer != null&&trainer.getStatus()==1) {
                String trainerName = trainer.getName();
                carDto.setTrainerName(trainerName);
            }

            return carDto;

        }).collect(Collectors.toList());

        CarDtoPage.setRecords(list);

        return ResultDate.success(CarDtoPage);
    }
}
