package cn.seu.weme.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by LCN on 2017-1-4.
 */
@Entity
@Table(name = "t_user_like_activity_relation",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "activity_id"}))
public class UserLikeActivityRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Activity.class)
    @JoinColumn(name = "activity_id")
    private Activity activity;


    @CreationTimestamp
    private Date timestamp;


    public UserLikeActivityRelation(User user, Activity activity) {
        this.user = user;
        this.activity = activity;
    }

    public UserLikeActivityRelation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
