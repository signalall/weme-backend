package cn.seu.weme.entity;

import javax.persistence.*;

/**
 * Created by LCN on 2017-1-4.
 */
@Entity
@Table(name = "t_message_image")
public class MessageImage extends BaseImage {

    @Column(unique = true)
    private Long messageId;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }
}
