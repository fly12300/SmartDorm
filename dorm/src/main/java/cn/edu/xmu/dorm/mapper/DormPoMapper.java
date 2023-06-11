package cn.edu.xmu.dorm.mapper;

import cn.edu.xmu.dorm.mapper.po.DormPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface DormPoMapper extends JpaRepository<DormPo,Long> {
    Optional<DormPo> findByUserId(Long userId);
}
