package king;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import share.king.MainApplication;
import share.king.entity.ShareEntity;
import share.king.entity.UserEntity;
import share.king.service.interfaces.IShareSV;
import share.king.service.interfaces.IUserSV;
import share.king.util.Common;
import share.king.util.TimeUtil;
import share.king.util.Utils;

import java.util.Date;
import java.util.List;

@SpringBootTest(classes = MainApplication.class)
@RunWith(SpringRunner.class)
public class Test {


    @Autowired
    private IShareSV shareSV;
    @Autowired
    private IUserSV userSV;

    @org.junit.Test
    public void test() throws Exception {
        ShareEntity shareEntity = new ShareEntity();
        shareEntity.setBuyId(1);
        shareEntity.setName("太白金星");
        shareEntity.setBuyTime("20192002");
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
    public void testExist() throws Exception {
        UserEntity userEntity = userSV.findByUserName("zhuhh");
        System.out.println(userEntity);
        userEntity = userSV.findByUserName("aca");
        System.out.println(userEntity);
    }

    @org.junit.Test
    public void testInsertUser() {
        UserEntity userEntity = new UserEntity("zhuhh", "U2FsdGVkX18caxDKk6codyUdHXKFZOPL1TyOEf4n2Y8=");
        userSV.insert(userEntity);
        System.out.println("密码:" + userEntity.getPassword());
    }

    @org.junit.Test
    public void testValidate() {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(Utils.getMD5("U2FsdGVkX18caxDKk6codyUdHXKFZOPL1TyOEf4n2Y8="));
        userEntity.setUserName("zhuhh");
        System.out.println("验证结果" + userSV.validateUserInfo(userEntity));
//        userEntity.setPassword("p2");
//        System.out.println("验证结果" + userSV.validateUserInfo(userEntity));
    }

}
