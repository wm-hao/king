package share.king.comparator;

import share.king.entity.TradeRecord;

import java.util.Comparator;

public class TradeRecordComparator implements Comparator<TradeRecord> {
    @Override
    public int compare(TradeRecord o1, TradeRecord o2) {
        if (o1.getCreateDate().before(o2.getCreateDate())) {
            return -1;
        } else if (o1.getCreateDate().equals(o2.getCreateDate())) {
            return 0;
        } else {
            return 1;
        }
    }
}
