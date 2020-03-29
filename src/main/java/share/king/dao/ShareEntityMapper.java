package share.king.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import share.king.entity.ShareEntity;
import share.king.entity.ShareEntityExample;

@Mapper
public interface ShareEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShareEntity record);

    int insertSelective(ShareEntity record);

    List<ShareEntity> selectByExample(ShareEntityExample example);

    ShareEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShareEntity record);

    int updateByPrimaryKey(ShareEntity record);

    List<ShareEntity> selectAll();
}