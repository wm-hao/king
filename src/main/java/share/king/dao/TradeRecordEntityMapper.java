package share.king.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    List<TradeRecordEntity> selectByCondition(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate, @Param("profit") String profit, @Param("asc") String asc);

    int updateBatch(@Param("list") List<TradeRecordEntity> tradeRecordEntities);
}