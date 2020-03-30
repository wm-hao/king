package share.king.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.king.dao.TradeRecordEntityMapper;
import share.king.entity.TradeRecordEntity;
import share.king.service.interfaces.ITradeRecordSV;

import java.util.Date;
import java.util.List;

@Service
public class TradeRecordSVImpl implements ITradeRecordSV {

    @Autowired
    private TradeRecordEntityMapper tradeRecordEntityMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tradeRecordEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TradeRecordEntity record) {
        record.setCreateDate(new Date());
        return tradeRecordEntityMapper.insert(record);
    }

    @Override
    public int insertSelective(TradeRecordEntity record) {
        return insertSelective(record);
    }

    @Override
    public TradeRecordEntity selectByPrimaryKey(Integer id) {
        return tradeRecordEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TradeRecordEntity record) {
        return tradeRecordEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TradeRecordEntity record) {
        return tradeRecordEntityMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<TradeRecordEntity> selectAll() {
        return tradeRecordEntityMapper.selectAll();
    }

    @Override
    public PageInfo<TradeRecordEntity> selectByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TradeRecordEntity> recordEntities = tradeRecordEntityMapper.selectAll();
        return new PageInfo<>(recordEntities);
    }

    @Override
    public PageInfo<TradeRecordEntity> selectByDateType(String startDate, String endDate, String opType) {
        return null;
    }
}
