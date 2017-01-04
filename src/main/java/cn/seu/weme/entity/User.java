package cn.seu.weme.entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.*;

/**
 * Created by LCN on 2016-12-17.
 */
@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer age;
    @Column(length = 32, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String salt;
    private String token;
    @Column(length = 32)
    private String school;
    @Column(length = 32)
    private String degree;
    @Column(length = 32)
    private String department;
    @Column(length = 32)
    private String enrollment;
    private String name;
    @Column(length = 32)
    private String gender;
    @Column(length = 32, unique = true)
    private String phone;
    @Column(length = 32)
    private String birthday;
    @Column(length = 32)
    private String wechat;
    @Column(length = 32)
    private String qq;
    @Column(length = 32)
    private String hometown;
    private String hobby;
    private String preference;

    @Column(columnDefinition = "INT default 100")
    private Integer weme = 100;
    @Column(columnDefinition = "Boolean default false")
    private boolean certification = false;

    @CreationTimestamp
    private Date timestamp;

    @Lob
    private String tags;


    @OneToMany(cascade = CascadeType.ALL, targetEntity = FollowRelation.class, mappedBy = "follower",
            fetch = FetchType.LAZY)
    @LazyCollection(
            LazyCollectionOption.EXTRA
    )
    private List<FollowRelation> followerRelations = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, targetEntity = FollowRelation.class, mappedBy = "followed",
            fetch = FetchType.LAZY)
    @LazyCollection(
            LazyCollectionOption.EXTRA
    )
    private List<FollowRelation> followedRelations = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, targetEntity = UserVisitRelation.class, mappedBy = "visiter",
            fetch = FetchType.LAZY)
    @LazyCollection(
            LazyCollectionOption.EXTRA
    )
    private List<UserVisitRelation> visterRelations = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, targetEntity = UserVisitRelation.class, mappedBy = "visited",
            fetch = FetchType.LAZY)
    @LazyCollection(
            LazyCollectionOption.EXTRA
    )
    private List<UserVisitRelation> visitedRelations = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, targetEntity = LikeUserRelation.class, mappedBy = "liker",
            fetch = FetchType.LAZY)
    @LazyCollection(
            LazyCollectionOption.EXTRA
    )
    private List<LikeUserRelation> likerRelations = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, targetEntity = LikeUserRelation.class, mappedBy = "liked",
            fetch = FetchType.LAZY)
    @LazyCollection(
            LazyCollectionOption.EXTRA
    )
    private List<LikeUserRelation> likedRelations = new ArrayList<>();


    //用户发表的帖子
    @OneToMany(cascade = CascadeType.REMOVE, targetEntity = Post.class, mappedBy = "publishUser",
            fetch = FetchType.LAZY)
    @LazyCollection(
            LazyCollectionOption.EXTRA
    )
    private List<Post> publishedPosts = new ArrayList<>();


    //用户喜欢的文章
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post",
            targetEntity = UserLikePostRelation.class)
    @LazyCollection(
            LazyCollectionOption.EXTRA
    )
    private List<UserLikePostRelation> userLikePostRelations = new ArrayList<>();


    @OneToOne(fetch = FetchType.LAZY, targetEntity = AvatarVoice.class, cascade = CascadeType.ALL)
    private AvatarVoice avatarVoice;


    //用户参加的活动
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user",
            targetEntity = UserAttendActivityRelation.class)
    @LazyCollection(
            LazyCollectionOption.EXTRA
    )
    private List<UserAttendActivityRelation> userAttendActivityRelations = new ArrayList<>();


    //用户喜欢的活动
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user",
            targetEntity = UserLikeActivityRelation.class)
    @LazyCollection(
            LazyCollectionOption.EXTRA
    )
    private List<UserLikeActivityRelation> userLikeActivityRelations = new ArrayList<>();


    //user delete 不影响活动本身
    @OneToMany(mappedBy = "authorUser")
    private List<Activity> sponsorActivities = new ArrayList<>();


    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "user", targetEntity = UserImage.class)
    private Set<UserImage> userImages = new HashSet<>();


    public User() {
    }

    public User(String phone, String password, String salt, String token) {
        this.phone = phone;
        this.password = password;
        this.salt = salt;
        this.token = token;
    }

    public User(Integer age, String username) {
        this.age = age;
        this.username = username;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public Integer getWeme() {
        return weme;
    }

    public void setWeme(Integer weme) {
        this.weme = weme;
    }

    public Boolean getCertification() {
        return certification;
    }

    public void setCertification(Boolean certification) {
        this.certification = certification;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<FollowRelation> getFollowerRelations() {
        return followerRelations;
    }

    public void setFollowerRelations(List<FollowRelation> followerRelations) {
        this.followerRelations = followerRelations;
    }

    public List<FollowRelation> getFollowedRelations() {
        return followedRelations;
    }

    public void setFollowedRelations(List<FollowRelation> followedRelations) {
        this.followedRelations = followedRelations;
    }

    public List<UserVisitRelation> getVisterRelations() {
        return visterRelations;
    }

    public void setVisterRelations(List<UserVisitRelation> visterRelations) {
        this.visterRelations = visterRelations;
    }

    public List<UserVisitRelation> getVisitedRelations() {
        return visitedRelations;
    }

    public void setVisitedRelations(List<UserVisitRelation> visitedRelations) {
        this.visitedRelations = visitedRelations;
    }

    public List<LikeUserRelation> getLikerRelations() {
        return likerRelations;
    }

    public void setLikerRelations(List<LikeUserRelation> likerRelations) {
        this.likerRelations = likerRelations;
    }

    public List<LikeUserRelation> getLikedRelations() {
        return likedRelations;
    }

    public void setLikedRelations(List<LikeUserRelation> likedRelations) {
        this.likedRelations = likedRelations;
    }

    public List<Post> getPublishedPosts() {
        return publishedPosts;
    }

    public void setPublishedPosts(List<Post> publishedPosts) {
        this.publishedPosts = publishedPosts;
    }

    public List<UserLikePostRelation> getUserLikePostRelations() {
        return userLikePostRelations;
    }

    public void setUserLikePostRelations(List<UserLikePostRelation> userLikePostRelations) {
        this.userLikePostRelations = userLikePostRelations;
    }

    public AvatarVoice getAvatarVoice() {
        return avatarVoice;
    }

    public void setAvatarVoice(AvatarVoice avatarVoice) {
        this.avatarVoice = avatarVoice;
    }

    public List<UserAttendActivityRelation> getUserAttendActivityRelations() {
        return userAttendActivityRelations;
    }

    public void setUserAttendActivityRelations(List<UserAttendActivityRelation> userAttendActivityRelations) {
        this.userAttendActivityRelations = userAttendActivityRelations;
    }

    public List<UserLikeActivityRelation> getUserLikeActivityRelations() {
        return userLikeActivityRelations;
    }

    public void setUserLikeActivityRelations(List<UserLikeActivityRelation> userLikeActivityRelations) {
        this.userLikeActivityRelations = userLikeActivityRelations;
    }

    public List<Activity> getSponsorActivities() {
        return sponsorActivities;
    }

    public void setSponsorActivities(List<Activity> sponsorActivities) {
        this.sponsorActivities = sponsorActivities;
    }

    public Set<UserImage> getUserImages() {
        return userImages;
    }

    public void setUserImages(Set<UserImage> userImages) {
        this.userImages = userImages;
    }
}
