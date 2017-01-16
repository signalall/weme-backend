package cn.seu.weme.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by LCN on 2017-1-4.
 */
@Entity
@Table(name = "t_report")
public class Report {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "authoruser_id")
    private User authorUser;

    @Lob
    private String body;

    private int type = 0; //1: user 2:post 3:activity 4：comment

    @JoinColumn(name = "be_report_id")
    private Long beReportedId;


    @CreationTimestamp
    private Date timestamp;


    




    private  Integer typeid;

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }


    public User getAuthorUser() {
        return authorUser;
    }

    public void setAuthorUser(User authorUser) {
        this.authorUser = authorUser;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getBeReportedId() {
        return beReportedId;
    }

    public void setBeReportedId(Long beReportedId) {
        this.beReportedId = beReportedId;
    }
}