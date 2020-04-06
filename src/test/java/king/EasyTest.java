package king;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import share.king.entity.TradeRecord;
import share.king.exception.BaseException;
import share.king.util.Common;
import share.king.util.ExcelUtil;
import share.king.util.TimeUtil;
import share.king.util.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class EasyTest {

    private static transient Log log = LogFactory.getLog(EasyTest.class);

    @org.junit.Test
    public void testMd5() {
        System.out.println(Utils.getMD5("b0fa3d1bc72650df3624c2761a626332"));
    }

    @Test
    public void createVerifyCode() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Utils.createVerificationCode(5));
        }
    }

    @Test
    public void testDecimal() {
        DecimalFormat decimalFormat = new DecimalFormat("##%");
        int a = 2;
        int b = 30;
        double r = (2 * 1.0) / (30 * 1.0);
        log.error(r);
        log.error(decimalFormat.format(r));
    }

    @org.junit.Test
    public void testExcel() throws BaseException {
        List<TradeRecord> tradeRecords = ExcelUtil.parseExcel2TradeRecord("D:/all2.xlsx");
        for (TradeRecord record : tradeRecords) {
            log.error(record.getName() + ";buyTime=" + TimeUtil.getStringByFormat(record.getBuyTime(), TimeUtil.yyyyMMddHH_mm_ss) + ";buyPrice=" + record.getBuyPrice() + ";sellPrice=" + record.getSellPrice() + ";sellTime=" + TimeUtil.getStringByFormat(record.getSellTime(), TimeUtil.yyyyMMddHH_mm_ss));
        }
    }

    @Test
    public void test() throws IOException {
        log.error(System.getProperty("user.dir") + File.separator + Common.UPLOAD_DIR + File.separator);
        log.error(new File("D:/all2.xlsx").getAbsolutePath());
    }


    @Test
    public void testArray() {
        List<String> list = new ArrayList<>();
        List<Integer> muns = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("商品" + i);
            muns.add(i);
        }
        log.error(JSON.toJSONString(list));
        log.error(JSON.toJSONString(muns));

    }
}
