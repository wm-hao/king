package share.king.dto.trade;

public class StatisticsDayBuy {
    private int total;
    private String day;
    private String weekDayDesc;
    private int weekDay;
    private String year;
    private String month;
    private String name;
    private String code;
    private double profit;
    private int profitState;
    private double keep;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getWeekDayDesc() {
        return weekDayDesc;
    }

    public void setWeekDayDesc(String weekDayDesc) {
        this.weekDayDesc = weekDayDesc;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getKeep() {
        return keep;
    }

    public void setKeep(double keep) {
        this.keep = keep;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public int getProfitState() {
        return profitState;
    }

    public void setProfitState(int profitState) {
        this.profitState = profitState;
    }

    @Override
    public String toString() {
        return "StatisticsDayBuy{" +
                "total=" + total +
                ", day='" + day + '\'' +
                ", weekDayDesc='" + weekDayDesc + '\'' +
                ", weekDay=" + weekDay +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", profit=" + profit +
                ", profitState=" + profitState +
                ", keep=" + keep +
                '}';
    }
}
