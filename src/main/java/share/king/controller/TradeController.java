package share.king.controller;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import share.king.dto.Response;
import share.king.dto.TradeRecordQry;
import share.king.entity.TradeRecordEntity;
import share.king.service.interfaces.ITradeRecordSV;
import share.king.util.Common;
import share.king.util.GateWayUtil;
import share.king.util.TimeUtil;

import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/share")
public class TradeController {
    private static transient Log log = LogFactory.getLog(TradeController.class);
    private static final int PAGE_SIZE_MAX = 50;
    private static final int PAGE_SIZE_MIN = 1;
    private static final int PAGE_NUM_MIN = 1;

    @Autowired
    private ITradeRecordSV tradeRecordSV;

    @GetMapping("list")
    public List<TradeRecordEntity> list() {
        return tradeRecordSV.selectAll();
    }

    @PostMapping("insert")
    public Response insert(@RequestBody TradeRecordEntity TradeRecordEntity) {
        log.info("插入SHARE:" + TradeRecordEntity);
        if (tradeRecordSV.insert(TradeRecordEntity) == Common.StatusCode.SUCCESS.getCode()) {
            return GateWayUtil.returnSuccessResponse("保存成功");
        }
        return GateWayUtil.returnFailResponse("保存失败");
    }

    @PostMapping("list/page")
    public Response page(@RequestBody TradeRecordQry tradeRecordQry) {
        log.error("查询交易记录:" + tradeRecordQry);
        int pageNum = tradeRecordQry.getPageNum();
        int pageSize = tradeRecordQry.getPageSize();
        if (pageNum < PAGE_NUM_MIN) {
            pageNum = PAGE_NUM_MIN;
        }
        if (pageSize < PAGE_SIZE_MIN) {
            pageSize = PAGE_SIZE_MIN;
        }
        if (pageSize > PAGE_SIZE_MAX) {
            pageSize = PAGE_SIZE_MAX;
        }
        Timestamp startDate = null;
        Timestamp endDate = null;
        if (StringUtils.isNoneBlank(tradeRecordQry.getStartDate())) {
            startDate = TimeUtil.getTimestampByFormat(tradeRecordQry.getStartDate(), TimeUtil.yyyyMMdd);
        }
        if (StringUtils.isNoneBlank(tradeRecordQry.getEndDate())) {
            endDate = TimeUtil.getTimestampByFormat(tradeRecordQry.getEndDate(), TimeUtil.yyyyMMdd);
        }
        PageInfo<TradeRecordEntity> pageInfo = tradeRecordSV.selectByCondition(pageNum, pageSize, startDate, endDate, tradeRecordQry.getProfit(), tradeRecordQry.getAsc());
        Response response = GateWayUtil.returnSuccessResponse("查询成功");
        response.setRows(pageInfo.getList());
        response.setTotal(pageInfo.getTotal());
        return response;
    }

    @PostMapping("update")
    public Response update(@RequestBody TradeRecordEntity tradeRecordEntity) {
        if (tradeRecordSV.updateByPrimaryKeySelective(tradeRecordEntity) == Common.StatusCode.SUCCESS.getCode()) {
            return GateWayUtil.returnSuccessResponse("更新成功");
        }
        return GateWayUtil.returnFailResponse("更新失败");
    }

    @PostMapping("delete")
    public Response delete(@RequestBody TradeRecordEntity tradeRecordEntity) {
        if (tradeRecordSV.deleteByPrimaryKey(tradeRecordEntity.getId()) == Common.StatusCode.SUCCESS.getCode()) {
            return GateWayUtil.returnSuccessResponse("删除成功");
        }
        return GateWayUtil.returnFailResponse("删除失败");
    }
}
