package king;

import com.github.pagehelper.PageInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import share.king.MainApplication;
import share.king.entity.ShareEntity;
import share.king.entity.UserEntity;
import share.king.service.interfaces.IMailSV;
import share.king.service.interfaces.IShareSV;
import share.king.service.interfaces.IUserSV;
import share.king.util.RedisUtil;
import share.king.util.TokenUtil;

import java.util.Date;
import java.util.List;

@SpringBootTest(classes = MainApplication.class)
@RunWith(SpringRunner.class)
public class Test {

    private static transient Log log = LogFactory.getLog(Test.class);

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private IShareSV shareSV;
    @Autowired
    private IUserSV userSV;
    @Autowired
    IMailSV mailSV;

    @org.junit.Test
    public void test() throws Exception {
        ShareEntity shareEntity = new ShareEntity();
        shareEntity.setBuyId(1);
        shareEntity.setName("太白金星");
        shareEntity.setBuyTime(new Date());
        shareEntity.setBuyCount(100);
        shareSV.insert(shareEntity);
    }

    @org.junit.Test
    public void qry() throws Exception {
        List<ShareEntity> list = shareSV.selectAll();
        for (ShareEntity shareEntity : list) {
            System.out.println(shareEntity);
        }
    }

    @org.junit.Test
    public void saveShare() throws Exception {
        List<ShareEntity> list = shareSV.selectAll();
        ShareEntity shareEntity = list.get(0);
        for (int i = 0; i < 12; i++) {
            shareEntity.setId(null);
            shareEntity.setName(shareEntity.getName() + i);
            shareSV.insert(shareEntity);
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
        UserEntity userEntity = new UserEntity("newUser", "ec6ef230f1828039ee794566b9c58adc");
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
        PageInfo<ShareEntity> pageInfo = shareSV.selectByPage(0, 1);
        log.error(pageInfo.getList().size());
        log.error(pageInfo.getTotal());
    }


}
