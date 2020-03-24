package share.king.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.king.dao.UserEntityMapper;
import share.king.entity.UserEntity;
import share.king.service.interfaces.IUserSV;

@Service
public class UserSVImpl implements IUserSV {

    @Autowired
    UserEntityMapper userEntityMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UserEntity record) {
        return userEntityMapper.insert(record);
    }

    @Override
    public int insertSelective(UserEntity record) {
        return userEntityMapper.insertSelective(record);
    }

    @Override
    public UserEntity selectByPrimaryKey(Integer id) {
        return userEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UserEntity record) {
        return userEntityMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKey(UserEntity record) {
        return userEntityMapper.updateByPrimaryKeySelective(record);
    }
}
