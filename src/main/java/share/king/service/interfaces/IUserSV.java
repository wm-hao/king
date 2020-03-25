package share.king.service.interfaces;

import share.king.entity.UserEntity;

public interface IUserSV {

    int deleteByPrimaryKey(Integer id);

    int insert(UserEntity record);

    int insertSelective(UserEntity record);

    UserEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserEntity record);

    int updateByPrimaryKey(UserEntity record);

    UserEntity findByUserName(String userName);

    boolean validateUserInfo(UserEntity userEntity);
}
