package cn.seu.weme.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by LCN on 2017-1-3.
 */
@Entity
@Table(name = "t_avatar_voice")
public class AvatarVoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 32)
    private String name;

    @Column(length = 32)
    private String gender;

    private String avatarUrl;
    private String voiceUrl;

    private boolean cardFlag = false;
    private boolean disable = false;

    private Integer voiceNumber;
    private Integer avatarNumber;

    @CreationTimestamp
    private Date timestamp;


    @OneToOne(fetch = FetchType.LAZY, targetEntity = User.class, mappedBy = "avatarVoice")
    private User user;


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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getVoiceUrl() {
        return voiceUrl;
    }

    public void setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }

    public Boolean getCardFlag() {
        return cardFlag;
    }

    public void setCardFlag(Boolean cardFlag) {
        this.cardFlag = cardFlag;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public Integer getVoiceNumber() {
        return voiceNumber;
    }

    public void setVoiceNumber(Integer voiceNumber) {
        this.voiceNumber = voiceNumber;
    }

    public Integer getAvatarNumber() {
        return avatarNumber;
    }

    public void setAvatarNumber(Integer avatarNumber) {
        this.avatarNumber = avatarNumber;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
