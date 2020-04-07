package share.king.dao;

import org.apache.ibatis.annotations.Param;
import share.king.dto.trade.StatisticsDayBuy;
import share.king.entity.TradeRecord;

import java.sql.Timestamp;
import java.util.List;

public interface ExtensionTradeRecordMapper {

    List<TradeRecord> selectAll();

    List<TradeRecord> selectByCondition(@Param("code") String code, @Param("name") String name, @Param("alias") String alias, @Param("userId") Integer userId,@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate, @Param("profit") String profit, @Param("asc") String asc);

    int updateBatch(@Param("list") List<TradeRecord> tradeRecordEntities);

    int insertBatch(@Param("list") List<TradeRecord> tradeRecords);

    List<StatisticsDayBuy> getTotalGroupByDate(@Param("userId") Integer userId);

    List<StatisticsDayBuy> getTotalByBuyCount(@Param("userId") Integer userId);

    List<StatisticsDayBuy> getTopBottomProfit(@Param("userId") Integer userId, @Param("desc") String desc);

    List<StatisticsDayBuy> getProfitCompare(@Param("userId") Integer userId);

}