package share.king.dto;

public class TradeRecordQry extends QryParams {
    private String startDate;
    private String endDate;
    private String profit;
    private String asc;
    private Integer userId;
    private String code;
    private String name;
    private String alias;
    private int pageNum;
    private int pageSize;
    private String dataType;


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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public int getPageNum() {
        return pageNum;
    }

    @Override
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        return "TradeRecordQry{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", profit='" + profit + '\'' +
                ", asc='" + asc + '\'' +
                ", userId=" + userId +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", dataType='" + dataType + '\'' +
                '}';
    }
}
