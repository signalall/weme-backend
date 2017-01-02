package cn.seu.weme.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by LCN on 2016-12-18.
 */
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinTable(
            name = "like_user_comment",
            joinColumns = @JoinColumn(name = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> likeUser = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "comment",targetEntity = CommentImage.class)
    private Set<CommentImage> commentImages = new HashSet<>();


    public Set<CommentImage> getCommentImages() {
        return commentImages;
    }

    public void setCommentImages(Set<CommentImage> commentImages) {
        this.commentImages = commentImages;
    }

    public Set<User> getLikeUser() {
        return likeUser;
    }

    public void setLikeUser(Set<User> likeUser) {
        this.likeUser = likeUser;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
