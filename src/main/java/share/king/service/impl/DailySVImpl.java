package share.king.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import share.king.dao.DailyRepository;
import share.king.entity.DailyEntity;
import share.king.service.interfaces.IDailySV;

public class DailySVImpl implements IDailySV {

    @Autowired
    private DailyRepository dailyRepository;

    @Override
    public Page<DailyEntity> qryPage(int pageNum, int pageSize, Integer userId, String orderColumn, boolean desc) {
        Sort.Order order = new Sort.Order(desc ? Sort.Direction.DESC : Sort.Direction.ASC, orderColumn);
        Sort sort = Sort.by(order);
        Pageable pageable = PageRequest.of(Math.max(pageNum - 1, 0), pageSize, sort);
        return dailyRepository.findAll(new Specification<DailyEntity>(

        ), pageable);
    }
}
