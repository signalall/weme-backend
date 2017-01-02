package cn.seu.weme.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by LCN on 2016-12-21.
 */
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="sendfrom_id")
    private User sendFrom;

    @ManyToOne
    @JoinColumn(name="sendto_id")
    private User sendTo;

    private String text;//不支持图片类型

    private Boolean state;

    private Date sendTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSendFrom() {
        return sendFrom;
    }

    public void setSendFrom(User sendFrom) {
        this.sendFrom = sendFrom;
    }

    public User getSendTo() {
        return sendTo;
    }

    public void setSendTo(User sendTo) {
        this.sendTo = sendTo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
