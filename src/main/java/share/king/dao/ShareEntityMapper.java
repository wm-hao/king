package share.king.dao;

import org.apache.ibatis.annotations.Mapper;
import share.king.entity.ShareEntity;
import share.king.entity.ShareEntityExample;

import java.util.List;

@Mapper
public interface ShareEntityMapper {
    long countByExample(ShareEntityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShareEntity record);

    int insertSelective(ShareEntity record);

    ShareEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShareEntity record);

    int updateByPrimaryKey(ShareEntity record);

    List<ShareEntity> selectAll();
}