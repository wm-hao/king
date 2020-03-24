package king;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import share.king.MainApplication;
import share.king.entity.ShareEntity;
import share.king.service.interfaces.IShareSV;

import java.util.List;

@SpringBootTest(classes = MainApplication.class)
@RunWith(SpringRunner.class)
public class Test {


    @Autowired
    private IShareSV shareSV;

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
}
