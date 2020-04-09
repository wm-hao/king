package share.king.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.king.dao.DailyMapper;
import share.king.dto.DailyQry;
import share.king.entity.Daily;
import share.king.service.interfaces.IDailySV;
import share.king.util.Common;
import share.king.util.TimeUtil;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class DailySVImpl implements IDailySV {

    @Autowired
    private DailyMapper dailyMapper;

    @Override
    public int insert(Daily record) {
        record.setCreateDate(new Date());
        record.setOptDate(new Date());
        record.setState(Common.StatusCode.SUCCESS.getCode());
        record.setMonth(TimeUtil.getStringByFormat(new Date(), TimeUtil.yyyyMM));
        return dailyMapper.insert(record);
    }

    @Override
    public PageInfo<Daily> qryDailyByPage(DailyQry dailyQry) {
        PageHelper.startPage(dailyQry.getPageNum(), dailyQry.getPageSize());
        Timestamp startDate = null;
        if (StringUtils.isNotBlank(dailyQry.getStartDate())) {
            startDate = TimeUtil.getTimestampByFormat(dailyQry.getStartDate(), TimeUtil.yyyyMMddHHmmss);
        }
        Timestamp endDate = null;
        if (StringUtils.isNotBlank(dailyQry.getEndDate())) {
            endDate = TimeUtil.getTimestampByFormat(dailyQry.getEndDate(), TimeUtil.yyyyMMddHHmmss);
        }
        return new PageInfo<>(dailyMapper.findAll(dailyQry.getUserId(), startDate, endDate, dailyQry.getAsc(), dailyQry.getContent()));
    }

    @Override
    public int updateSelective(Daily record) {
        record.setOptDate(new Date());
        record.setMonth(TimeUtil.getStringByFormat(new Date(), TimeUtil.yyyyMM));
        return dailyMapper.updateByPrimaryKeySelective(record);
    }
}
