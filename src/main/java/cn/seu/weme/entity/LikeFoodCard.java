package cn.seu.weme.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by LCN on 2017-1-3.
 */
@Entity
@Table(name = "t_user_like_foodcard",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "foodcard_id"}))
public class LikeFoodCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity  = FoodCard.class)
    @JoinColumn(name = "foodcard_id")
    private FoodCard foodCard;


    @Column
    @org.hibernate.annotations.CreationTimestamp
    private Date timestamp;

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

    public FoodCard getFoodCard() {
        return foodCard;
    }

    public void setFoodCard(FoodCard foodCard) {
        this.foodCard = foodCard;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
