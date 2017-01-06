package cn.seu.weme.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by LCN on 2017-1-6.
 */
@Entity
@Table(name = "t_topic_image")
public class TopicImage extends BaseImage {

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
