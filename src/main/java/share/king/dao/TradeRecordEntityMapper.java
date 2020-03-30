package share.king.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import share.king.entity.TradeRecordEntity;
import share.king.entity.TradeRecordEntityExample;

@Mapper
public interface TradeRecordEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TradeRecordEntity record);

    int insertSelective(TradeRecordEntity record);

    List<TradeRecordEntity> selectByExample(TradeRecordEntityExample example);

    TradeRecordEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TradeRecordEntity record);

    int updateByPrimaryKey(TradeRecordEntity record);

    List<TradeRecordEntity> selectAll();
}