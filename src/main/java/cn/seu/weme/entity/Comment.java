package cn.seu.weme.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.jboss.logging.annotations.Pos;

import javax.persistence.*;
import java.util.*;

/**
 * Created by LCN on 2016-12-18.
 */
@Entity
@Table(name="t_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    private String content;

    @CreationTimestamp
    private Date timestamp;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Post.class)
    @JoinColumn(name = "post_id")
    private Post post;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "comment",targetEntity = CommentImage.class)
    @LazyCollection(
            LazyCollectionOption.EXTRA
    )
    private List<CommentImage> commentImages = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, targetEntity = UserLikeCommentRelation.class, mappedBy = "comment",
            fetch = FetchType.LAZY)
    @LazyCollection(
            LazyCollectionOption.EXTRA
    )
    private List<UserLikeCommentRelation> userLikeCommentRelations = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<CommentImage> getCommentImages() {
        return commentImages;
    }

    public void setCommentImages(List<CommentImage> commentImages) {
        this.commentImages = commentImages;
    }

    public List<UserLikeCommentRelation> getUserLikeCommentRelations() {
        return userLikeCommentRelations;
    }

    public void setUserLikeCommentRelations(List<UserLikeCommentRelation> userLikeCommentRelations) {
        this.userLikeCommentRelations = userLikeCommentRelations;
    }
}
