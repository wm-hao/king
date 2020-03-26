package share.king.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MoneyUtil {

    public static int changeY2L(double price) {
        DecimalFormat df = new DecimalFormat("#.00");
        price = Double.parseDouble(df.format(price));
        return (int) (price * 1000);
    }

    public static String changeL2Y(int price) {
        return BigDecimal.valueOf(price).divide(new BigDecimal(1000)).toString();
    }
}
