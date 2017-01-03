package cn.seu.weme.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by LCN on 2017-1-3.
 */
@Entity
public class FoodCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = User.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "author_id")
    private User author;

    private String imageUrl;

    private String location;
    private String longitude;
    private String latitude;
    private String price;
    private String comment;
    private String passflag;
    private boolean disable;
    private Date timestamp;
    private int likeNumber;


    @OneToMany(cascade = CascadeType.ALL, targetEntity = LikeFoodCard.class,mappedBy = "foodCard")
    private List<LikeFoodCard> likeFoodCard;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPassflag() {
        return passflag;
    }

    public void setPassflag(String passflag) {
        this.passflag = passflag;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }


    public List<LikeFoodCard> getLikeFoodCard() {
        return likeFoodCard;
    }

    public void setLikeFoodCard(List<LikeFoodCard> likeFoodCard) {
        this.likeFoodCard = likeFoodCard;
    }
}
