package share.king.service.interfaces;

import share.king.entity.User;

public interface IUserSV {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findByUserName(String userName);

    boolean validateUserInfo(User User);
}
