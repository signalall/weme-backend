package cn.seu.weme.entity;

import javax.persistence.*;

/**
 * Created by LCN on 2017-1-4.
 */
@Entity
@Table(name = "t_message_image")
public class MessageImage extends BaseImage {

    @ManyToOne
    @JoinColumn(name = "message_id")
    private Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
