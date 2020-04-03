package share.king.dao;

import share.king.entity.UserEntity;

public interface ExtensionUserEntityMapper {

    UserEntity findByUserName(String userName);
}