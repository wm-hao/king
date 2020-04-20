package share.king.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.king.dao.BalanceMapper;
import share.king.entity.Balance;
import share.king.service.interfaces.IBalanceSV;
import share.king.util.Common;

import java.util.Date;
import java.util.List;

@Service
public class BalanceSVImpl implements IBalanceSV {

    @Autowired
    private BalanceMapper balanceMapper;

    @Override
    public int insert(Balance record) {
        record.setCreateDate(new Date());
        record.setOptDate(new Date());
        record.setState(Common.StatusCode.SUCCESS.getCode());
        record.setBalanceTypeId(Common.BalanceType.ORDINARY.getType());
        return balanceMapper.insert(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Balance record) {
        record.setOptDate(new Date());
        return balanceMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Balance> selectAll(Integer userId) {
        return balanceMapper.selectAll(userId);
    }
}
