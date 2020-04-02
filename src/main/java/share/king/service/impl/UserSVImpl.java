package share.king.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.king.dao.UserEntityMapper;
import share.king.entity.UserEntity;
import share.king.service.interfaces.IUserSV;
import share.king.util.Common;
import share.king.util.TimeUtil;
import share.king.util.Utils;

import java.util.Date;

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
        Date currDate = new Date();
        record.setPassword(Utils.getMD5(record.getPassword().trim()));
        record.setCreateDate(currDate);
        record.setOptDate(currDate);
        record.setValidDate(currDate);
        record.setState(Common.USER_STATE_U);
        record.setExpireDate(TimeUtil.getTimestampByFormat("20991231235959", TimeUtil.yyyyMMddHHmmss));
        return userEntityMapper.insert(record);
    }

    @Override
    public int insertSelective(UserEntity record) {
        record.setPassword(Utils.getMD5(record.getPassword()));
        return userEntityMapper.insertSelective(record);
    }

    @Override
    public UserEntity selectByPrimaryKey(Integer id) {
        return userEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UserEntity record) {
        record.setOptDate(new Date());
        return userEntityMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKey(UserEntity record) {
        return userEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public UserEntity findByUserName(String userName) {
        return userEntityMapper.findByUserName(userName);
    }

    @Override
    public boolean validateUserInfo(UserEntity userEntity) {
        if (userEntity != null && StringUtils.isNotBlank(userEntity.getUserName()) && StringUtils.isNoneBlank(userEntity.getUserName())) {
            String password = userEntity.getPassword();
            String cipherText = Utils.getMD5(password);
            UserEntity user = findByUserName(userEntity.getUserName());
            if (user != null) {
                return StringUtils.equals(cipherText, user.getPassword());
            }
        }
        return false;
    }
}
