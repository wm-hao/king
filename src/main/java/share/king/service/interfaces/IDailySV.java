package share.king.service.interfaces;

import com.github.pagehelper.PageInfo;
import share.king.dto.DailyQry;
import share.king.entity.Daily;

public interface IDailySV {

    int insert(Daily record);

    PageInfo<Daily> qryDailyByPage(DailyQry dailyQry);

    int updateSelective(Daily record);
}
