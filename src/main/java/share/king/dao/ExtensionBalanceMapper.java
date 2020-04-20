package share.king.dao;

import org.apache.ibatis.annotations.Param;
import share.king.entity.Balance;

import java.util.List;

public interface ExtensionBalanceMapper {

    List<Balance> selectAll(@Param("userId") Integer userId);
}