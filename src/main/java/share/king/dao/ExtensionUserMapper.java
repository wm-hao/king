package share.king.dao;

import share.king.entity.User;

public interface ExtensionUserMapper {

    User findByUserName(String userName);
}