package cn.seu.weme.entity;

import javax.persistence.*;

/**
 * Created by LCN on 2016-12-21.
 */
@Entity
@Table(name = "t_user_image")
public class UserImage extends BaseImage {

    @ManyToOne(targetEntity = User.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
