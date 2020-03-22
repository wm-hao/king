package share.king.service.interfaces;

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
}
