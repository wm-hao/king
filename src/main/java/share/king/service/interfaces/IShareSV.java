package share.king.service.interfaces;

import com.github.pagehelper.PageInfo;
import share.king.entity.ShareEntity;

import java.util.List;

public interface IShareSV {

    int deleteByPrimaryKey(Integer id);

    int insert(ShareEntity record);

    int insertSelective(ShareEntity record);

    ShareEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShareEntity record);

    int updateByPrimaryKey(ShareEntity record);

    List<ShareEntity> selectAll();

    PageInfo<ShareEntity> selectByPage(int pageNum, int pageSize);

    PageInfo<ShareEntity> selectByDateType(String startDate, String endDate, String opType);
}
