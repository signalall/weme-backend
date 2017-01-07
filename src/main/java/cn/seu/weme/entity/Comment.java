package cn.seu.weme.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by LCN on 2016-12-18.
 */
@Entity
@Table(name = "t_comment")
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


    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Comment.class)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Comment.class)
    @JoinColumn(name = "authoruser_id")
    private User authorUser;


    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Comment.class)
    @JoinColumn(name = "to_user_id")
    private User toUser;  //该评论的指向用户


    @Column(nullable = false)
    private int type = 0; //评论的类型  1:to post   2: to activity 3:to comment

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private boolean state = false; //是否阅读

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public User getAuthorUser() {
        return authorUser;
    }

    public void setAuthorUser(User authorUser) {
        this.authorUser = authorUser;
    }

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Activity.class)
    @JoinColumn(name = "activity_id")
    private Activity activity;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comment", targetEntity = CommentImage.class)
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

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
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

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
