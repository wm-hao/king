package share.king.service.interfaces;

import com.github.pagehelper.PageInfo;
import share.king.dto.TradeRecordQry;
import share.king.dto.trade.StatisticsDayBuy;
import share.king.entity.TradeRecord;

import java.sql.Timestamp;
import java.util.List;

public interface ITradeRecordSV {

    int deleteByPrimaryKey(Integer id);

    int insert(TradeRecord record);

    int insertSelective(TradeRecord record);

    TradeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TradeRecord record);

    int updateByPrimaryKey(TradeRecord record);

    List<TradeRecord> selectAll();

    PageInfo<TradeRecord> selectByPage(int pageNum, int pageSize);

    PageInfo<TradeRecord> selectByCondition(TradeRecordQry tradeRecordQry, int pageNum, int pageSize, Timestamp startDate, Timestamp endDate);

    int updateBatch(List<TradeRecord> tradeRecordEntities);

    int insertBatch(List<TradeRecord> tradeRecords, Integer userId);

    List<StatisticsDayBuy> getTotalGroupByDate(Integer userId);

    List<StatisticsDayBuy> getTotalByBuyCountAll(Integer userId);

    List<StatisticsDayBuy> getTopBottomProfitAll(Integer userId, String desc);

    PageInfo<StatisticsDayBuy> getTopBottomProfit(int pageNum, int pageSize, Integer userId, String desc);

    PageInfo<StatisticsDayBuy> getTotalByBuyCount(int pageNum, int pageSize, Integer userId);

    List<StatisticsDayBuy> getProfitCompare(int userId);
}
