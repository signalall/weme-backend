package cn.seu.weme.dto;

/**
 * Created by LCN on 2016-12-18.
 */
public class PostVo {

    private Long id;

    private String title;
    private String body;

    private Integer likenumbers;
    private Integer commentnumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getLikenumbers() {
        return likenumbers;
    }

    public void setLikenumbers(Integer likenumbers) {
        this.likenumbers = likenumbers;
    }

    public Integer getCommentnumber() {
        return commentnumber;
    }

    public void setCommentnumber(Integer commentnumber) {
        this.commentnumber = commentnumber;
    }
}
