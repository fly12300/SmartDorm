package cn.edu.xmu.dorm.mapper;

import cn.edu.xmu.dorm.mapper.po.AlarmPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlarmPoMapper extends JpaRepository<AlarmPo,Long> {
    List<AlarmPo> findAllByDormId(Long userId);
}
