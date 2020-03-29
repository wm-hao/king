package share.king.dao;

import org.apache.ibatis.annotations.Mapper;
import share.king.entity.BalanceChangeEntity;

@Mapper
public interface BalanceChangeEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BalanceChangeEntity record);

    int insertSelective(BalanceChangeEntity record);

    BalanceChangeEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BalanceChangeEntity record);

    int updateByPrimaryKey(BalanceChangeEntity record);
}