package cn.seu.weme.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;

/**
 * Created by LCN on 2016-12-18.
 */
@Entity
@Table(name = "t_post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    @Lob
    private String body;

    @Column(columnDefinition = "Boolean default false")
    private Boolean disable = false;

    private Integer top;

    @CreationTimestamp
    private Date timestamp;


    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "publish_user_id")
    private User publishUser;


    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "post",
            targetEntity = Comment.class)
    @LazyCollection(
            LazyCollectionOption.EXTRA
    )
    private List<Comment> comments = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user",
            targetEntity = UserLikePostRelation.class)
    @LazyCollection(
            LazyCollectionOption.EXTRA
    )
    private List<UserLikePostRelation> userLikePostRelations = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post", targetEntity = PostImage.class)
    private List<PostImage> postImages = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public User getPublishUser() {
        return publishUser;
    }

    public void setPublishUser(User publishUser) {
        this.publishUser = publishUser;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<UserLikePostRelation> getUserLikePostRelations() {
        return userLikePostRelations;
    }

    public void setUserLikePostRelations(List<UserLikePostRelation> userLikePostRelations) {
        this.userLikePostRelations = userLikePostRelations;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public List<PostImage> getPostImages() {
        return postImages;
    }

    public void setPostImages(List<PostImage> postImages) {
        this.postImages = postImages;
    }
}
