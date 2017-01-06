package cn.seu.weme.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 海报
 * Created by LCN on 2017-1-6.
 */
@Entity
@Table(name = "t_poster_image")
public class PosterImage extends BaseImage {

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
