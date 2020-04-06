package share.king.dao;

import share.king.entity.BalanceChange;

import java.util.List;

public interface ExtensionBalanceChangeMapper {

    List<BalanceChange> findAll();
}
