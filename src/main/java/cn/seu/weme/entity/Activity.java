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
@Table(name = "t_activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 32)
    private String top;

    @Column(length = 32)
    private String title;

    @Column(length = 32)
    private String time;  //活动时间

    @Column(length = 32)
    private String location;

    @Column(length = 32)
    private String number; //8~10

    private Integer signnumber;

    @Column(length = 32)
    private boolean state;

    private String sponser;

    @Column(columnDefinition = "Boolean default false")
    private boolean disable = false;


    @Column(length = 32)
    private String remark;


    @Column(columnDefinition = "Boolean default false")
    private boolean whetherImage = false;

    private String advertise;

    @Lob
    private String detail;


    @Column(length = 32)
    private String label;

    @Column(columnDefinition = "Boolean default false")
    private boolean passFlag = false;

    @CreationTimestamp
    private Date timestamp;


    @ManyToOne()
    @JoinColumn(name = "author_id")
    private User authorUser; //发起活动者


    @OneToMany(cascade = CascadeType.ALL, targetEntity = UserAttendActivityRelation.class, mappedBy = "user",
            fetch = FetchType.LAZY)
    @LazyCollection(
            LazyCollectionOption.EXTRA
    )
    private List<UserAttendActivityRelation> userAttendActivityRelations = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, targetEntity = UserLikeActivityRelation.class, mappedBy = "user",
            fetch = FetchType.LAZY)
    @LazyCollection(
            LazyCollectionOption.EXTRA
    )
    private List<UserLikeActivityRelation> userLikeActivityRelations = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activity", targetEntity = ActivityImage.class)
    private Set<ActivityImage> avtivityImages = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getSignnumber() {
        return signnumber;
    }

    public void setSignnumber(Integer signnumber) {
        this.signnumber = signnumber;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getSponser() {
        return sponser;
    }

    public void setSponser(String sponser) {
        this.sponser = sponser;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isWhetherImage() {
        return whetherImage;
    }

    public void setWhetherImage(boolean whetherImage) {
        this.whetherImage = whetherImage;
    }

    public String getAdvertise() {
        return advertise;
    }

    public void setAdvertise(String advertise) {
        this.advertise = advertise;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isPassFlag() {
        return passFlag;
    }

    public void setPassFlag(boolean passFlag) {
        this.passFlag = passFlag;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public User getAuthorUser() {
        return authorUser;
    }

    public void setAuthorUser(User authorUser) {
        this.authorUser = authorUser;
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

    public Set<ActivityImage> getAvtivityImages() {
        return avtivityImages;
    }

    public void setAvtivityImages(Set<ActivityImage> avtivityImages) {
        this.avtivityImages = avtivityImages;
    }
}
