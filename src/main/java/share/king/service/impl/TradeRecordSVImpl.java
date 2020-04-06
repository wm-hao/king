package share.king.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.king.dao.TradeRecordMapper;
import share.king.dto.trade.StatisticsDayBuy;
import share.king.entity.TradeRecord;
import share.king.service.interfaces.ITradeRecordSV;
import share.king.util.Common;
import share.king.util.Utils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class TradeRecordSVImpl implements ITradeRecordSV {

    @Autowired
    TradeRecordMapper tradeRecordMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tradeRecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TradeRecord record) {
        record.setState(Common.StatusCode.SUCCESS.getCode());
        record.setCreateDate(new Date());
        return tradeRecordMapper.insert(record);
    }

    @Override
    public int insertSelective(TradeRecord record) {
        return tradeRecordMapper.insertSelective(record);
    }

    @Override
    public TradeRecord selectByPrimaryKey(Integer id) {
        return tradeRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TradeRecord record) {
        return tradeRecordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TradeRecord record) {
        return tradeRecordMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<TradeRecord> selectAll() {
        return tradeRecordMapper.selectAll();
    }

    @Override
    public PageInfo<TradeRecord> selectByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TradeRecord> recordEntities = tradeRecordMapper.selectAll();
        return new PageInfo<>(recordEntities);
    }

    @Override
    public PageInfo<TradeRecord> selectByCondition(Integer userId, int pageNum, int pageSize, Timestamp startDate, Timestamp endDate, String profit, String asc) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(tradeRecordMapper.selectByCondition(userId, startDate, endDate, profit, asc));
    }

    @Override
    public int updateBatch(List<TradeRecord> tradeRecordEntities) {
        return tradeRecordMapper.updateBatch(tradeRecordEntities);
    }

    @Override
    public int insertBatch(List<TradeRecord> tradeRecords, Integer userId) {
        for (TradeRecord tradeRecord : tradeRecords) {
            tradeRecord.setCreateDate(new Date());
            tradeRecord.setState(Common.StatusCode.SUCCESS.getCode());
            tradeRecord.setUserId(userId);
        }
        return tradeRecordMapper.insertBatch(tradeRecords);
    }

    @Override
    public List<StatisticsDayBuy> getTotalGroupByDate(Integer userId) {
        List<StatisticsDayBuy> statisticsDayBuys = tradeRecordMapper.getTotalGroupByDate(userId);
        for (StatisticsDayBuy statisticsDayBuy : statisticsDayBuys) {
            if (statisticsDayBuy.getWeekDay() > 0) {
                statisticsDayBuy.setWeekDayDesc(Utils.convertWeekDay(statisticsDayBuy.getWeekDay()));
            }
            String year = statisticsDayBuy.getDay().substring(0, 4);
            String yearMonth = statisticsDayBuy.getDay().substring(0, 7);
            statisticsDayBuy.setYear(year);
            statisticsDayBuy.setMonth(yearMonth);
        }
        return statisticsDayBuys;
    }

    @Override
    public List<StatisticsDayBuy> getTotalByBuyCountAll(Integer userId) {
        return tradeRecordMapper.getTotalByBuyCount(userId);
    }

    @Override
    public List<StatisticsDayBuy> getTopBottomProfitAll(Integer userId, String desc) {
        return tradeRecordMapper.getTopBottomProfit(userId, desc);
    }

    @Override
    public PageInfo<StatisticsDayBuy> getTopBottomProfit(int pageNum, int pageSize, Integer userId, String desc) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(tradeRecordMapper.getTopBottomProfit(userId, desc));
    }

    @Override
    public PageInfo<StatisticsDayBuy> getTotalByBuyCount(int pageNum, int pageSize, Integer userId) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(tradeRecordMapper.getTotalByBuyCount(userId));
    }

    @Override
    public List<StatisticsDayBuy> getProfitCompare(int userId) {
        return tradeRecordMapper.getProfitCompare(userId);
    }


}
