package cn.seu.weme.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by LCN on 2017-1-3.
 */
@Entity
@Table(name = "t_foodcard")
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

    private boolean passFlage;

    @Column(columnDefinition = "Boolean default false")
    private boolean disable = false;
    @CreationTimestamp
    private Date timestamp;


    @OneToMany(cascade = CascadeType.ALL, targetEntity = LikeFoodCard.class,mappedBy = "foodCard")
    @LazyCollection(
            LazyCollectionOption.EXTRA
    )
    private List<LikeFoodCard> likeFoodCards;


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

    public List<LikeFoodCard> getLikeFoodCards() {
        return likeFoodCards;
    }

    public void setLikeFoodCards(List<LikeFoodCard> likeFoodCards) {
        this.likeFoodCards = likeFoodCards;
    }
}
