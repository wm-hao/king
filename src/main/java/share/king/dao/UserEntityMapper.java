package share.king.dao;

import org.apache.ibatis.annotations.Mapper;
import share.king.entity.UserEntity;
import share.king.entity.UserEntityExample;

import java.util.List;

@Mapper
public interface UserEntityMapper extends ExtensionUserEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserEntity record);

    int insertSelective(UserEntity record);

    List<UserEntity> selectByExample(UserEntityExample example);

    UserEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserEntity record);

    int updateByPrimaryKey(UserEntity record);


}