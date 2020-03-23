package share.king.entity;

public class ShareEntity {
    private Integer id;

    private String name;

    private String code;

    private String createDate;

    private String buyTime;

    private Integer buyPrice;

    private Integer buyId;

    private String sellTime;

    private Integer sellPrice;

    private Integer openPrice;

    private Integer closePrice;

    private Integer highPrice;

    private Integer lowPrice;

    private String remark;

    private String idea;

    private String dayTrend;

    private Integer buyCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime == null ? null : buyTime.trim();
    }

    public Integer getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Integer buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Integer getBuyId() {
        return buyId;
    }

    public void setBuyId(Integer buyId) {
        this.buyId = buyId;
    }

    public String getSellTime() {
        return sellTime;
    }

    public void setSellTime(String sellTime) {
        this.sellTime = sellTime == null ? null : sellTime.trim();
    }

    public Integer getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Integer sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Integer getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(Integer openPrice) {
        this.openPrice = openPrice;
    }

    public Integer getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(Integer closePrice) {
        this.closePrice = closePrice;
    }

    public Integer getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Integer highPrice) {
        this.highPrice = highPrice;
    }

    public Integer getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Integer lowPrice) {
        this.lowPrice = lowPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea == null ? null : idea.trim();
    }

    public String getDayTrend() {
        return dayTrend;
    }

    public void setDayTrend(String dayTrend) {
        this.dayTrend = dayTrend == null ? null : dayTrend.trim();
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }

    @Override
    public String toString() {
        return "ShareEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", createDate='" + createDate + '\'' +
                ", buyTime='" + buyTime + '\'' +
                ", buyPrice=" + buyPrice +
                ", buyId=" + buyId +
                ", sellTime='" + sellTime + '\'' +
                ", sellPrice=" + sellPrice +
                ", openPrice=" + openPrice +
                ", closePrice=" + closePrice +
                ", highPrice=" + highPrice +
                ", lowPrice=" + lowPrice +
                ", remark='" + remark + '\'' +
                ", idea='" + idea + '\'' +
                ", dayTrend='" + dayTrend + '\'' +
                ", buyCount='" + buyCount + '\'' +
                '}';
    }
}