package cn.edu.xmu.dorm.mapper;

import cn.edu.xmu.dorm.mapper.po.DevicePo;
import cn.edu.xmu.dorm.mapper.po.TotalPowerPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TotalPowerPoMapper extends JpaRepository<TotalPowerPo,Long> {

    @Query(value = "select * from t_total_power where DATE_FORMAT(count_time,'%Y-%m') = ?2 and dorm_id = ?1",nativeQuery = true)
    List<TotalPowerPo> findByDormIdAndAndCountTime(Long dormId, String date);
}
