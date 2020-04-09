package share.king.dto;

public class TradeRecordQry extends QryParams {

    private String profit;

    private String code;
    private String name;
    private String alias;
    private String dataType;



    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
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


    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        return "TradeRecordQry{" + super.toString() +
                ", profit='" + profit + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                ", dataType='" + dataType + '\'' +
                '}';
    }
}
