package share.king.dao;

import org.apache.ibatis.annotations.Param;
import share.king.entity.Daily;

import java.sql.Timestamp;
import java.util.List;

public interface ExtDailyMapper {

    List<Daily> findAll(@Param("userId") Integer useId, @Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate, @Param("desc") String desc, @Param("content") String content);
}
