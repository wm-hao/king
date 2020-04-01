package king;

import com.github.pagehelper.PageInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import share.king.MainApplication;
import share.king.entity.TradeRecordEntity;
import share.king.entity.UserEntity;
import share.king.service.interfaces.IMailSV;
import share.king.service.interfaces.ITradeRecordSV;
import share.king.service.interfaces.IUserSV;
import share.king.util.RedisUtil;
import share.king.util.TimeUtil;
import share.king.util.TokenUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        TradeRecordEntity TradeRecordEntity = new TradeRecordEntity();
        TradeRecordEntity.setName("太白金星");
        TradeRecordEntity.setBuyTime(new Date());
        TradeRecordEntity.setBuyCount(100);
        tradeRecordSV.insert(TradeRecordEntity);
    }

    @org.junit.Test
    public void qry() throws Exception {
        List<TradeRecordEntity> list = tradeRecordSV.selectAll();
        for (TradeRecordEntity TradeRecordEntity : list) {
            System.out.println(TradeRecordEntity);
        }
    }

    @org.junit.Test
    public void saveShare() throws Exception {
        List<TradeRecordEntity> list = tradeRecordSV.selectAll();
        TradeRecordEntity TradeRecordEntity = list.get(0);
        for (int i = 0; i < 12; i++) {
            TradeRecordEntity.setId(null);
            TradeRecordEntity.setName(TradeRecordEntity.getName() + i);
            tradeRecordSV.insert(TradeRecordEntity);
        }
    }

    @org.junit.Test
    public void testExist() throws Exception {
        UserEntity userEntity = userSV.findByUserName("newUser");
        System.out.println(userEntity);
        userEntity = userSV.findByUserName("aca");
        System.out.println(userEntity);
    }

    @org.junit.Test
    public void testInsertUser() {
        UserEntity userEntity = new UserEntity("admin", "1c63129ae9db9c60c3e8aa94d3e00495");
        userEntity.setEmail("448826602@qq.com");
        userSV.insert(userEntity);
        System.out.println("密码:" + userEntity.getPassword());
    }

    @org.junit.Test
    public void testValidate() {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("ec6ef230f1828039ee794566b9c58adc");
        userEntity.setUserName("newUser");
        System.out.println("验证结果" + userSV.validateUserInfo(userEntity));
    }

    @org.junit.Test
    public void testToken() {
        UserEntity userEntity = new UserEntity();
        userEntity = userSV.findByUserName("zhuhh");
        String token = TokenUtil.createJWT(-1, userEntity);
        log.error("token=" + token);
        log.error("从token获取用户名:" + TokenUtil.getUserName(token));
        log.error("验证:" + TokenUtil.verify(token, userEntity));
    }

    @org.junit.Test
    public void testRedis() {
        log.error(redisUtil.get("key") == null);
    }

    @org.junit.Test
    public void testPage() {
        PageInfo<TradeRecordEntity> pageInfo = tradeRecordSV.selectByPage(0, 1);
        log.error(pageInfo.getList().size());
        log.error(pageInfo.getTotal());
    }


    @org.junit.Test
    public void selectByCondition() {
        Timestamp startDate = new Timestamp(TimeUtil.getTimestampByFormat("20200330", "yyyyMMdd").getTime());
        PageInfo<TradeRecordEntity> pageInfo = tradeRecordSV.selectByCondition(10, 20, null, null, "", null);
        List<TradeRecordEntity> tradeRecordEntities = pageInfo.getList();
        log.error("长度:" + tradeRecordEntities.size());
        for (TradeRecordEntity tradeRecordEntity : tradeRecordEntities) {
            log.error(tradeRecordEntity.getName());
        }
    }

    @org.junit.Test
    public void updateTradeSelective() {
        TradeRecordEntity entity = new TradeRecordEntity();
        TradeRecordEntity entity1 = new TradeRecordEntity();
        entity.setName("cs15");
        entity.setId(15);
        entity1.setName("cs16");
        entity1.setId(16);
        List<TradeRecordEntity> list = new ArrayList<>();
        list.add(entity);
        list.add(entity1);
        log.error("结果" + tradeRecordSV.updateBatch(list));
    }
}
