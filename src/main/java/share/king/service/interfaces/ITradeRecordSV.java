package share.king.service.interfaces;

import com.github.pagehelper.PageInfo;
import share.king.entity.TradeRecordEntity;

import java.util.List;

public interface ITradeRecordSV {

    int deleteByPrimaryKey(Integer id);

    int insert(TradeRecordEntity record);

    int insertSelective(TradeRecordEntity record);

    TradeRecordEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TradeRecordEntity record);

    int updateByPrimaryKey(TradeRecordEntity record);

    List<TradeRecordEntity> selectAll();

    PageInfo<TradeRecordEntity> selectByPage(int pageNum, int pageSize);

    PageInfo<TradeRecordEntity> selectByDateType(String startDate, String endDate, String opType);
}
