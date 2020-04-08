package share.king.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import share.king.entity.Daily;
import share.king.entity.DailyExample;

public interface DailyMapper extends ExtDailyMapper {
    long countByExample(DailyExample example);

    int deleteByExample(DailyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Daily record);

    int insertSelective(Daily record);

    List<Daily> selectByExampleWithBLOBs(DailyExample example);

    List<Daily> selectByExample(DailyExample example);

    Daily selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Daily record, @Param("example") DailyExample example);

    int updateByExampleWithBLOBs(@Param("record") Daily record, @Param("example") DailyExample example);

    int updateByExample(@Param("record") Daily record, @Param("example") DailyExample example);

    int updateByPrimaryKeySelective(Daily record);

    int updateByPrimaryKeyWithBLOBs(Daily record);

    int updateByPrimaryKey(Daily record);
}