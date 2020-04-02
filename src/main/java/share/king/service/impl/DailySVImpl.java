package share.king.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import share.king.dao.DailyRepository;
import share.king.entity.DailyEntity;
import share.king.service.interfaces.IDailySV;

@Service
public class DailySVImpl implements IDailySV {

    @Autowired
    private DailyRepository dailyRepository;

    @Override
    public Page<DailyEntity> qryPage(int pageNum, int pageSize, Integer userId, String orderColumn, boolean desc) {
        Sort.Order order = new Sort.Order(desc ? Sort.Direction.DESC : Sort.Direction.ASC, orderColumn);
        Sort sort = Sort.by(order);
        DailyEntity dailyEntity = new DailyEntity();
        dailyEntity.setUserId(userId);
        Pageable pageable = PageRequest.of(Math.max(pageNum - 1, 0), pageSize, sort);
        return dailyRepository.findAll(Example.of(dailyEntity), pageable);
    }
}
