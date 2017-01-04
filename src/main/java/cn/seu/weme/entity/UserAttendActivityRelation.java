package cn.seu.weme.entity;

/**
 * Created by LCN on 2017-1-4.
 */

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_user_attend_activity_relation",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "activity_id"}))
public class UserAttendActivityRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Activity.class)
    @JoinColumn(name = "activity_id")
    private Activity activity;

    private int state = 0;


    @CreationTimestamp
    private Date timestamp;

    public UserAttendActivityRelation() {
    }

    public UserAttendActivityRelation(User user, Activity activity) {
        this.user = user;
        this.activity = activity;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
