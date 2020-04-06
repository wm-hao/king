package king;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import share.king.MainApplication;
import share.king.dto.Response;
import share.king.dto.trade.StatisticsDayBuy;
import share.king.entity.TradeRecord;
import share.king.entity.User;
import share.king.exception.BaseException;
import share.king.service.interfaces.IMailSV;
import share.king.service.interfaces.ITradeRecordSV;
import share.king.service.interfaces.IUserSV;
import share.king.util.*;

import java.sql.Timestamp;
import java.util.*;


@SpringBootTest(classes = MainApplication.class)
@RunWith(SpringRunner.class)
public class Test {

    private static transient Log log = LogFactory.getLog(Test.class);

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ITradeRecordSV tradeRecordSV;
    @Autowired
    private IUserSV userSV;
    @Autowired
    IMailSV mailSV;


    @org.junit.Test
    public void test() throws Exception {
        TradeRecord TradeRecord = new TradeRecord();
        TradeRecord.setName("太白金星");
        TradeRecord.setBuyTime(new Date());
        TradeRecord.setBuyCount(100);
        tradeRecordSV.insert(TradeRecord);
    }

    @org.junit.Test
    public void qry() throws Exception {
        List<TradeRecord> list = tradeRecordSV.selectAll();
        for (TradeRecord TradeRecord : list) {
            System.out.println(TradeRecord);
        }
    }

    @org.junit.Test
    public void saveShare() throws Exception {
        List<TradeRecord> list = tradeRecordSV.selectAll();
        TradeRecord TradeRecord = list.get(0);
        for (int i = 0; i < 12; i++) {
            TradeRecord.setId(null);
            TradeRecord.setName(TradeRecord.getName() + i);
            tradeRecordSV.insert(TradeRecord);
        }
    }

    @org.junit.Test
    public void testExist() throws Exception {
        User user = userSV.findByUserName("newUser");
        System.out.println(user);
        user = userSV.findByUserName("aca");
        System.out.println(user);
    }

    @org.junit.Test
    public void testInsertUser() {
        User user = new User();
        user.setUserName("ad");
        user.setEmail("xxx@qq.com");
        user.setPassword("pa");
        userSV.insert(user);
        System.out.println("密码:" + user.getPassword());
    }

    @org.junit.Test
    public void testValidate() {
        User user = new User();
        user.setPassword("ec6ef230f1828039ee794566b9c58adc");
        user.setUserName("newUser");
        System.out.println("验证结果" + userSV.validateUserInfo(user));
    }

    @org.junit.Test
    public void testToken() {
        User user = userSV.findByUserName("zhuhh");
        String token = TokenUtil.createJWT(-1, user);
        log.error("token=" + token);
        log.error("从token获取用户名:" + TokenUtil.getUserName(token));
        log.error("验证:" + TokenUtil.verify(token, user));
    }

    @org.junit.Test
    public void testRedis() {
        log.error(redisUtil.get("key") == null);
    }

    @org.junit.Test
    public void testPage() {
        PageInfo<TradeRecord> pageInfo = tradeRecordSV.selectByPage(0, 1);
        log.error(pageInfo.getList().size());
        log.error(pageInfo.getTotal());
    }


    @org.junit.Test
    public void selectByCondition() {
        Timestamp startDate = new Timestamp(TimeUtil.getTimestampByFormat("20200330", "yyyyMMdd").getTime());
        PageInfo<TradeRecord> pageInfo = tradeRecordSV.selectByCondition(1, 10, 20, null, null, "", null);
        List<TradeRecord> tradeRecordEntities = pageInfo.getList();
        log.error("长度:" + tradeRecordEntities.size());
        for (TradeRecord tradeRecord : tradeRecordEntities) {
            log.error(tradeRecord.getName());
        }
    }

    @org.junit.Test
    public void testInsertTradeBatch() throws BaseException {
        List<TradeRecord> tradeRecords = ExcelUtil.parseExcel2TradeRecord("D:/all2.xlsx");
        log.error("结果:" + tradeRecordSV.insertBatch(tradeRecords, 1));
    }

    @org.junit.Test
    public void testStatistics() {
        List<StatisticsDayBuy> statisticsDayBuys = tradeRecordSV.getTotalGroupByDate(1);
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
        List count = new ArrayList<>();
        List count4 = new ArrayList<>();
        for (String key : days.keySet()) {
            List tmpList = new ArrayList();

            tmpList.add(key);
            tmpList.add(days.get(key));
            count.add(tmpList);
        }
        List count1 = new ArrayList<>();
        for (String key : months.keySet()) {
            List tmpList = new ArrayList();
            tmpList.add(key);
            tmpList.add(months.get(key));
            count1.add(tmpList);
            count4.add(key);
        }
        List count2 = new ArrayList<>();
        for (String key : years.keySet()) {
            List tmpList = new ArrayList();
            tmpList.add(key);
            tmpList.add(years.get(key));
            count2.add(tmpList);
        }
        rows.add(count);
        rows.add(count1);
        rows.add(count2);
        rows.add(count4);
        log.error(JSON.toJSONString(response));
    }

    @org.junit.Test
    public void testTop() {
        PageInfo<StatisticsDayBuy> pageInfo = tradeRecordSV.getTopBottomProfit(1, 10, 1, "Y");
        List<StatisticsDayBuy> buys = pageInfo.getList();
        log.error(buys.size());
        List<StatisticsDayBuy> compare = tradeRecordSV.getProfitCompare(1);
        for (StatisticsDayBuy buy : compare) {
            log.error(buy);
        }
    }

}
