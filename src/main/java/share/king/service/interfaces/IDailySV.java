package share.king.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import share.king.entity.DailyEntity;

@Service
public interface IDailySV {

    Page<DailyEntity> qryPage(int pageNum, int pageSize, Integer userId, String orderColumn, boolean desc);
}
