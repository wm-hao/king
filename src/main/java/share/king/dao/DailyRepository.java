package share.king.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import share.king.entity.DailyEntity;

import java.util.List;

@Repository
public interface DailyRepository extends JpaRepository<DailyEntity, Integer> {

    List<DailyEntity> findByUserId(Integer userId);



}
