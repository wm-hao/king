package share.king.dao;

import org.apache.ibatis.annotations.Mapper;
import share.king.entity.BalanceEntity;

@Mapper
public interface BalanceEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BalanceEntity record);

    int insertSelective(BalanceEntity record);

    BalanceEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BalanceEntity record);

    int updateByPrimaryKey(BalanceEntity record);
}