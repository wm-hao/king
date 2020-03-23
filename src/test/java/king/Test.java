package king;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import share.king.MainApplication;
import share.king.dao.ShareEntityMapper;
import share.king.entity.ShareEntity;
import share.king.service.TestService;

@SpringBootTest(classes = MainApplication.class)
@RunWith(SpringRunner.class)
public class Test {


    @Autowired
    private TestService testService;

    @org.junit.Test
    public void test() throws Exception {
        ShareEntity shareEntity = new ShareEntity();
        shareEntity.setBuyId(1);
        shareEntity.setName("正邦科技");
        shareEntity.setBuyTime("2019200");
        shareEntity.setBuyCount(100);
        testService.getShareEntityMapper().insert(shareEntity);
    }
}
