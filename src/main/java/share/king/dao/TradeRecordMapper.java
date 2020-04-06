package share.king.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import share.king.entity.TradeRecord;
import share.king.entity.TradeRecordExample;
@Mapper
public interface TradeRecordMapper extends ExtensionTradeRecordMapper {
    long countByExample(TradeRecordExample example);

    int deleteByExample(TradeRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TradeRecord record);

    int insertSelective(TradeRecord record);

    List<TradeRecord> selectByExample(TradeRecordExample example);

    TradeRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TradeRecord record, @Param("example") TradeRecordExample example);

    int updateByExample(@Param("record") TradeRecord record, @Param("example") TradeRecordExample example);

    int updateByPrimaryKeySelective(TradeRecord record);

    int updateByPrimaryKey(TradeRecord record);
}