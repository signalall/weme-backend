package cn.seu.weme.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by LCN on 2017-1-4.
 */
@Entity
@Table(name = "t_user_visit_relation")
public class UserVisitRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "visiter_id")
    private User visiter;


    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "visited_id")
    private User visited;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date timestamp;


    public UserVisitRelation(User visiter, User visited) {
        this.visiter = visiter;
        this.visited = visited;
    }


    public UserVisitRelation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getVisiter() {
        return visiter;
    }

    public void setVisiter(User visiter) {
        this.visiter = visiter;
    }

    public User getVisited() {
        return visited;
    }

    public void setVisited(User visited) {
        this.visited = visited;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
