package share.king.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "daily")
public class DailyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "text")
    private String idea;

    @Column(columnDefinition = "timestamp")
    private Date createDate;

    @Column(columnDefinition = "timestamp")
    private Date optDate;

    private Integer state;

    @Column(length = 6)
    private String month;

    private Integer userId;

    public Integer getId() {
        return id;
    }


    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getOptDate() {
        return optDate;
    }

    public void setOptDate(Date optDate) {
        this.optDate = optDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "DailyEntity{" +
                "id=" + id +
                ", idea='" + idea + '\'' +
                ", createDate=" + createDate +
                ", optDate=" + optDate +
                ", state=" + state +
                ", month='" + month + '\'' +
                ", userId=" + userId +
                '}';
    }
}
