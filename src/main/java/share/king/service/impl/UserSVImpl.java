package share.king.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.king.dao.UserMapper;
import share.king.entity.User;
import share.king.service.interfaces.IUserSV;
import share.king.util.Common;
import share.king.util.TimeUtil;
import share.king.util.Utils;

import java.util.Date;

@Service
public class UserSVImpl implements IUserSV {

    @Autowired
    UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        Date currDate = new Date();
        record.setPassword(Utils.getMD5(record.getPassword().trim()));
        record.setCreateDate(currDate);
        record.setOptDate(currDate);
        record.setValidDate(currDate);
        record.setState(Common.USER_STATE_U);
        record.setExpireDate(TimeUtil.getTimestampByFormat("20991231235959", TimeUtil.yyyyMMddHHmmss));
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        record.setPassword(Utils.getMD5(record.getPassword()));
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        record.setOptDate(new Date());
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public User findByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }

    @Override
    public boolean validateUserInfo(User user) {
        if (user != null && StringUtils.isNotBlank(user.getUserName()) && StringUtils.isNoneBlank(user.getUserName())) {
            String password = user.getPassword();
            String cipherText = Utils.getMD5(password);
            User dbUser = findByUserName(user.getUserName());
            if (dbUser != null) {
                return StringUtils.equals(cipherText, dbUser.getPassword());
            }
        }
        return false;
    }
}
