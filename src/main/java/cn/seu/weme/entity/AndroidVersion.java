package cn.seu.weme.entity;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import org.hibernate.annotations.CreationTimestamp;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Visen (zhvisen@gmail.com) on 2017/1/5.
 */
@Entity
@Table(name="t_android_version")
public class AndroidVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer v1;

    private Integer v2;

    private Integer v3;

    private String wemeurl;

    @Column(columnDefinition = "Boolean default false")
    private boolean disable;

    public Integer getV1() {
        return v1;
    }

    public void setV1(Integer v1) {
        this.v1 = v1;
    }

    public Integer getV2() {
        return v2;
    }

    public void setV2(Integer v2) {
        this.v2 = v2;
    }

    public Integer getV3() {
        return v3;
    }

    public void setV3(Integer v3) {
        this.v3 = v3;
    }

    public String getWemeurl() {
        return wemeurl;
    }

    public void setWemeurl(String wemeurl) {
        this.wemeurl = wemeurl;
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

    @CreationTimestamp
    private Date timestamp;


}
