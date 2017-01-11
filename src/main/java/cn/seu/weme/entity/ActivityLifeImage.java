package cn.seu.weme.entity;

import javax.persistence.*;

/**
 * Created by Visen (zhvisen@gmail.com) on 2017/1/9.
 */
@Entity
@Table(name ="t_activity_life_image")
public class ActivityLifeImage extends BaseImage{

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    private Long imageid;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getImageid() {
        return imageid;
    }

    public void setImageid(Long imageid) {
        this.imageid = imageid;
    }
}
