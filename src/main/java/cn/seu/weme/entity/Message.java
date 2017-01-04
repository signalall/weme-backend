package cn.seu.weme.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by LCN on 2016-12-21.
 */
@Entity
@Table(name = "t_message")
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


    @Column(columnDefinition = "Boolean default false")
    private boolean state = false;


    @CreationTimestamp
    private Date timestamp;


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



    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
