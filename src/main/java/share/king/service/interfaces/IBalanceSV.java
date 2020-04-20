package share.king.service.interfaces;

import share.king.entity.Balance;

import java.util.List;

public interface IBalanceSV {

    int insert(Balance record);

    int updateByPrimaryKeySelective(Balance record);

    List<Balance> selectAll(Integer userId);
}
