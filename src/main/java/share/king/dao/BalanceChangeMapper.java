package share.king.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import share.king.entity.BalanceChange;
import share.king.entity.BalanceChangeExample;

import java.util.List;

@Mapper
public interface BalanceChangeMapper extends ExtensionBalanceChangeMapper {
    long countByExample(BalanceChangeExample example);

    int deleteByExample(BalanceChangeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BalanceChange record);

    int insertSelective(BalanceChange record);

    List<BalanceChange> selectByExample(BalanceChangeExample example);

    BalanceChange selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BalanceChange record, @Param("example") BalanceChangeExample example);

    int updateByExample(@Param("record") BalanceChange record, @Param("example") BalanceChangeExample example);

    int updateByPrimaryKeySelective(BalanceChange record);

    int updateByPrimaryKey(BalanceChange record);
}