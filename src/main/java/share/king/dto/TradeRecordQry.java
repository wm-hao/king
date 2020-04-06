package share.king.dto;

public class TradeRecordQry extends QryParams {
    private String startDate;
    private String endDate;
    private String profit;
    private String asc;
    private Integer userId;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public String getAsc() {
        return asc;
    }

    public void setAsc(String asc) {
        this.asc = asc;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "TradeRecordQry{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", profit='" + profit + '\'' +
                ", asc='" + asc + '\'' +
                ", userId=" + userId +
                '}';
    }
}
