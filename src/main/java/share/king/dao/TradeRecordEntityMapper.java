package share.king.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import share.king.entity.TradeRecordEntity;
import share.king.entity.TradeRecordEntityExample;

@Mapper
public interface TradeRecordEntityMapper extends ExtensionTradeRecordEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TradeRecordEntity record);

    int insertSelective(TradeRecordEntity record);

    List<TradeRecordEntity> selectByExample(TradeRecordEntityExample example);

    TradeRecordEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TradeRecordEntity record);

    int updateByPrimaryKey(TradeRecordEntity record);

}