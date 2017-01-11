package cn.seu.weme.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Visen (zhvisen@gmail.com) on 2017/1/11.
 */
@Entity
@Table(name = "t_community_poster_image")
public class CommunityPosterImage extends  BaseImage{

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private  Integer rank;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
