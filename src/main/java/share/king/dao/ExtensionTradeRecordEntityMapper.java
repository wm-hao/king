package share.king.dao;

import org.apache.ibatis.annotations.Param;
import share.king.entity.TradeRecordEntity;

import java.sql.Timestamp;
import java.util.List;

public interface ExtensionTradeRecordEntityMapper {

    List<TradeRecordEntity> selectAll();

    List<TradeRecordEntity> selectByCondition(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate, @Param("profit") String profit, @Param("asc") String asc);

    int updateBatch(@Param("list") List<TradeRecordEntity> tradeRecordEntities);
}