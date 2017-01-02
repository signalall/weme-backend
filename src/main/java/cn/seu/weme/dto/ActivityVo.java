package cn.seu.weme.dto;

/**
 * Created by LCN on 2016-12-18.
 */
public class ActivityVo {
    private Long id;
    private String top;
    private String title;
    private String time;  //活动时间
    private String location;
    private String number; //8~10
    private Integer signnumber;
    private String state;
    private String sponser;
    private Boolean disable;
    private String remark;
    private Boolean whetherimage;
    private String advertise;
    private String detail;
    private String label;
    private String passflag;
    private Integer likenumbers;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
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
}
