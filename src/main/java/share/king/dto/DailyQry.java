package share.king.dto;

public class DailyQry extends QryParams {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "DailyQry{" +
                "content='" + content + '\'' +
                '}';
    }
}
