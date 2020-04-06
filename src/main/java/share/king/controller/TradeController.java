package share.king.controller;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import share.king.dto.Response;
import share.king.dto.TradeRecordQry;
import share.king.dto.trade.StatisticsDayBuy;
import share.king.entity.TradeRecord;
import share.king.service.interfaces.ITradeRecordSV;
import share.king.util.Common;
import share.king.util.ExcelUtil;
import share.king.util.GateWayUtil;
import share.king.util.TimeUtil;

import java.io.File;
import java.sql.Timestamp;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/share")
public class TradeController {
    private static transient Log log = LogFactory.getLog(TradeController.class);
    private static final int PAGE_SIZE_MAX = 50;
    private static final int PAGE_SIZE_MIN = 1;
    private static final int PAGE_NUM_MIN = 1;
    private static final int TOP_10 = 10;

    @Autowired
    private ITradeRecordSV tradeRecordSV;

    @Value("${upload.dir}")
    private String dir;

    @GetMapping("list")
    public List<TradeRecord> list() {
        return tradeRecordSV.selectAll();
    }

    @PostMapping("insert")
    public Response insert(@RequestBody TradeRecord tradeRecord) {
        log.info("插入SHARE:" + tradeRecord);
        if (tradeRecordSV.insert(tradeRecord) == Common.StatusCode.SUCCESS.getCode()) {
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
        PageInfo<TradeRecord> pageInfo = tradeRecordSV.selectByCondition(tradeRecordQry.getUserId(), pageNum, pageSize, startDate, endDate, tradeRecordQry.getProfit(), tradeRecordQry.getAsc());
        Response response = GateWayUtil.returnSuccessResponse("查询成功");
        response.setRows(pageInfo.getList());
        response.setTotal(pageInfo.getTotal());
        return response;
    }

    @PostMapping("update")
    public Response update(@RequestBody TradeRecord tradeRecord) {
        if (tradeRecordSV.updateByPrimaryKeySelective(tradeRecord) == Common.StatusCode.SUCCESS.getCode()) {
            return GateWayUtil.returnSuccessResponse("更新成功");
        }
        return GateWayUtil.returnFailResponse("更新失败");
    }

    @PostMapping("delete")
    public Response delete(@RequestBody TradeRecord tradeRecord) {
        if (tradeRecordSV.deleteByPrimaryKey(tradeRecord.getId()) == Common.StatusCode.SUCCESS.getCode()) {
            return GateWayUtil.returnSuccessResponse("删除成功");
        }
        return GateWayUtil.returnFailResponse("删除失败");
    }

    @PostMapping("insertByFile")
    @ResponseBody
    public Response insertByFile(@RequestParam("file") MultipartFile file, @RequestParam("userId") int userId) {
        log.error("文件上传出入开始:");
        if (file.isEmpty()) {
            return GateWayUtil.returnFailResponse("上传文件为空");
        }
        String fileName = file.getOriginalFilename();
        String filePath = dir + File.separator;
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            List<TradeRecord> tradeRecords = ExcelUtil.parseExcel2TradeRecord(dest.getAbsolutePath());
            tradeRecordSV.insertBatch(tradeRecords, userId);
            log.info("上传成功");
        } catch (Exception e) {
            log.error("通过表格插入交易记录失败:");
            log.error(e.getMessage(), e);
            return GateWayUtil.returnFailResponse(e.getMessage());
        }
        return GateWayUtil.returnSuccessResponse("通过表格文件导入交易记录成功");
    }

    @GetMapping("frequency")
    public Response tradeFrequency(@RequestParam("userId") Integer userId) {
        if (userId == null) {
            return GateWayUtil.returnFailResponse("入参不能为空");
        }
        List<StatisticsDayBuy> statisticsDayBuys = tradeRecordSV.getTotalGroupByDate(userId);
        Map<String, Long> days = new TreeMap<>();
        Map<String, Long> years = new TreeMap<>();
        Map<String, Long> months = new TreeMap<>();
        Response response = GateWayUtil.returnSuccessResponse("查询成功");
        List rows = new ArrayList();
        response.setRows(rows);
        for (StatisticsDayBuy buy : statisticsDayBuys) {
            if (!days.containsKey(buy.getDay())) {
                days.put(buy.getDay(), 0L);
            }
            days.put(buy.getDay(), days.get(buy.getDay()) + buy.getTotal());
            if (!months.containsKey(buy.getMonth())) {
                months.put(buy.getMonth(), 0L);
            }
            months.put(buy.getMonth(), months.get(buy.getMonth()) + buy.getTotal());
            if (!years.containsKey(buy.getYear())) {
                years.put(buy.getYear(), 0L);
            }
            years.put(buy.getYear(), years.get(buy.getYear()) + buy.getTotal());
        }
        List dayCategory = new ArrayList<>();
        List monthCategory = new ArrayList<>();
        List yearCategory = new ArrayList<>();
        List dayData = new ArrayList<>();
        List monthData = new ArrayList<>();
        List yearData = new ArrayList<>();
        for (String key : days.keySet()) {
            List tmpList = new ArrayList();
            tmpList.add(key);
            tmpList.add(days.get(key));
            dayData.add(tmpList);
            dayCategory.add(key);
        }
        for (String key : months.keySet()) {
            List tmpList = new ArrayList();
            tmpList.add(key);
            tmpList.add(months.get(key));
            monthData.add(tmpList);
            monthCategory.add(key);
        }
        for (String key : years.keySet()) {
            List tmpList = new ArrayList();
            tmpList.add(key);
            tmpList.add(years.get(key));
            yearData.add(tmpList);
            yearCategory.add(key);
        }
        rows.add(dayCategory);
        rows.add(dayData);
        rows.add(monthCategory);
        rows.add(monthData);
        rows.add(yearCategory);
        rows.add(yearData);
        return response;
    }

    @GetMapping("top")
    public Response top(@RequestParam("userId") Integer userId) {
        if (userId == null) {
            return GateWayUtil.returnFailResponse("入参不能为空");
        }
        PageInfo<StatisticsDayBuy> top10CountPage = tradeRecordSV.getTotalByBuyCount(1, 10, userId);
        PageInfo<StatisticsDayBuy> top10 = tradeRecordSV.getTopBottomProfit(1, 10, userId, "Y");
        List<StatisticsDayBuy> top10List = top10.getList();
        PageInfo<StatisticsDayBuy> bottom10 = tradeRecordSV.getTopBottomProfit(1, 10, userId, null);
        List<StatisticsDayBuy> profitStateList = tradeRecordSV.getProfitCompare(userId);
        List<StatisticsDayBuy> bottom10List = bottom10.getList();
        List<String> top10Name = new ArrayList<>();
        List<Double> top10Profit = new ArrayList<>();
        List<String> bottom10Name = new ArrayList<>();
        List<Double> bottom10Profit = new ArrayList<>();
        List<String> top10CountName = new ArrayList<>();
        List<Integer> top10Count = new ArrayList<>();
        Collections.reverse(top10List);
        for (StatisticsDayBuy record : top10List) {
            top10Name.add(record.getName() + record.getCode());
            top10Profit.add(record.getProfit());
        }
        Collections.reverse(bottom10List);
        for (StatisticsDayBuy buy : bottom10List) {
            bottom10Name.add(buy.getName() + buy.getCode());
            bottom10Profit.add(buy.getProfit());
        }
        List<StatisticsDayBuy> topCount = top10CountPage.getList();
        Collections.reverse(topCount);
        for (StatisticsDayBuy buy : topCount) {
            top10CountName.add(buy.getName() + buy.getCode());
            top10Count.add(buy.getTotal());
        }
        List<Integer> profit = new ArrayList<>();
        int yes = 0;
        int normal = 0;
        int no = 0;
        for (StatisticsDayBuy buy : profitStateList) {
            if (buy.getProfitState() == 1) {
                yes = buy.getTotal();
            } else if (buy.getProfitState() == 0) {
                normal = buy.getTotal();
            } else if (buy.getProfitState() == -1) {
                no = buy.getTotal();
            }
        }
        profit.add(yes);
        profit.add(normal);
        profit.add(no);
        Response response = GateWayUtil.returnSuccessResponse("查询成功");
        List rows = new ArrayList();
        response.setRows(rows);
        rows.add(top10Name);
        rows.add(top10Profit);
        rows.add(bottom10Name);
        rows.add(bottom10Profit);
        rows.add(top10CountName);
        rows.add(top10Count);
        rows.add(profit);
        return response;
    }
}
