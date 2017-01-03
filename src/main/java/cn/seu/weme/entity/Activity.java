package cn.seu.weme.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;

/**
 * Created by LCN on 2016-12-17.
 */
@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String top;
    private String title;
    private String time;  //活动时间
    private String location;
    private String number; //8~10
    private Integer signnumber;
    private boolean state;
    private String sponser;
    private Boolean disable;
    private String remark;
    private Boolean whetherimage;
    private String advertise;
    private String detail;
    private String label;
    private String passflag;
    private Integer likenumbers;
    private Date timestamp;


    @ManyToOne()
    @JoinColumn(name = "author_id")
    private User authorUser; //发起活动者


    public Activity(String top, String title) {
        this.top = top;
        this.title = title;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "t_attend_user_activity",
            joinColumns = @JoinColumn(name = "activity_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @Fetch(FetchMode.SELECT)
    @LazyCollection(
            LazyCollectionOption.EXTRA
    )
    private Set<User> attendUsers = new HashSet<>();


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "t_like_user_activity",
            joinColumns = @JoinColumn(name = "activity_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> likeUsers = new HashSet<>();


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activity", targetEntity = ActivityImage.class)
    private Set<ActivityImage> avtivityImages = new HashSet<>();


    public Set<ActivityImage> getAvtivityImages() {
        return avtivityImages;
    }

    public void setAvtivityImages(Set<ActivityImage> avtivityImages) {
        this.avtivityImages = avtivityImages;
    }

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

    public boolean getState() {
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

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getWhetherimage() {
        return whetherimage;
    }

    public void setWhetherimage(Boolean whetherimage) {
        this.whetherimage = whetherimage;
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

    public String getPassflag() {
        return passflag;
    }

    public void setPassflag(String passflag) {
        this.passflag = passflag;
    }

    public Integer getLikenumbers() {
        return likenumbers;
    }

    public void setLikenumbers(Integer likenumbers) {
        this.likenumbers = likenumbers;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }


    public Activity() {
    }

    public User getAuthorUser() {
        return authorUser;
    }

    public void setAuthorUser(User authorUser) {
        this.authorUser = authorUser;
    }

    public Set<User> getAttendUsers() {
        return attendUsers;
    }

    public void setAttendUsers(Set<User> attendUsers) {
        this.attendUsers = attendUsers;
    }

    public Set<User> getLikeUsers() {
        return likeUsers;
    }

    public void setLikeUsers(Set<User> likeUsers) {
        this.likeUsers = likeUsers;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                '}';
    }


    //    private List<Comment> comments;
}
