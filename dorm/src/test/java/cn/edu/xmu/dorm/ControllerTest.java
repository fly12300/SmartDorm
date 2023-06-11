package cn.edu.xmu.dorm;


import cn.edu.xmu.dorm.mapper.PowerConsumptionPoMapper;
import cn.edu.xmu.dorm.mapper.SensorPoMapper;
import cn.edu.xmu.dorm.mapper.TotalPowerPoMapper;
import cn.edu.xmu.dorm.mapper.po.PowerConsumptionPo;
import cn.edu.xmu.dorm.mapper.po.SensorPo;
import cn.edu.xmu.dorm.mapper.po.TotalPowerPo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class ControllerTest {
    @Autowired
    private  SensorPoMapper sensorPoMapper;

    @Autowired
    private PowerConsumptionPoMapper powerConsumptionPoMapper;

    @Autowired
    private TotalPowerPoMapper totalPowerPoMapper;

    @Test
    public void testMy(){
        List<SensorPo> all = sensorPoMapper.findAllByDayAndDormId(17661935691L);
        System.out.println(all);
    }
    @Test
    public void test(){
        LocalDate data = LocalDate.of(2023, 4, 1);
        Optional<PowerConsumptionPo> mapper = powerConsumptionPoMapper.findByDeviceIdAndAndCountTime(1L,data);
        System.out.println(mapper);
    }

    @Test
    public void test1(){
        LocalDate data = LocalDate.of(2023, 6, 10);
        String format = String.format("%d-%02d", data.getYear(), data.getMonthValue());
        System.out.println(format);
        List<TotalPowerPo> powerPoMapperByDormIdAndAndCountTime = totalPowerPoMapper.findByDormIdAndAndCountTime(17661935691L,format);
        System.out.println(powerPoMapperByDormIdAndAndCountTime);
    }
}
