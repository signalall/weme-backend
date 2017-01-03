package cn.seu.weme.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by LCN on 2016-12-17.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer age;
    @Column(length = 30, nullable = true)
    private String username;
    private String password;
    private String salt;
    private String token;
    private String school;
    private String degree;
    private String department;
    private String enrollment;
    private String name;
    private String gender;
    private String phone;
    private String birthday;
    private String wechat;
    private String qq;
    private String hometown;
    private String hobby;
    private String preference;
    private Integer weme;
    private Boolean certification;
    private Date timestamp;
    private String tags;

    @Basic(fetch = FetchType.LAZY)
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "attendUsers")
    @Fetch(FetchMode.SELECT)
    @LazyCollection(
            LazyCollectionOption.EXTRA
    )
    private Set<Activity> attendActivities = new HashSet<>();

    @Basic(fetch = FetchType.LAZY)
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "likeUsers")
    @LazyCollection(
            LazyCollectionOption.EXTRA
    )
    private Set<Activity> likeActivities = new HashSet<>();


    @OneToMany(mappedBy = "authorUser", cascade = CascadeType.ALL)
    private Set<Activity> sponsorActivities = new HashSet<>();


    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "followeds")
    private Set<User> followers = new HashSet<>();//自己关注的人


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "t_follower_followed",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "followed_id"))
    @LazyCollection(
            LazyCollectionOption.EXTRA
    )
    private Set<User> followeds = new HashSet<>();//关注自己的人


    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "likeusers")
    private Set<Post> posts = new HashSet<>();

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "user", targetEntity = PersonalImage.class)
    private Set<PersonalImage> personalImages = new HashSet<>();


    public Set<PersonalImage> getPersonalImages() {
        return personalImages;
    }

    public void setPersonalImages(Set<PersonalImage> personalImages) {
        this.personalImages = personalImages;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


    public Set<Activity> getAttendActivities() {
        return attendActivities;
    }

    public void setAttendActivities(Set<Activity> attendActivities) {
        this.attendActivities = attendActivities;
    }

    public Set<Activity> getLikeActivities() {
        return likeActivities;
    }

    public void setLikeActivities(Set<Activity> likeActivities) {
        this.likeActivities = likeActivities;
    }

    public Set<Activity> getSponsorActivities() {
        return sponsorActivities;
    }

    public void setSponsorActivities(Set<Activity> sponsorActivities) {
        this.sponsorActivities = sponsorActivities;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    public Set<User> getFolloweds() {
        return followeds;
    }

    public void setFolloweds(Set<User> followeds) {
        this.followeds = followeds;
    }


    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
