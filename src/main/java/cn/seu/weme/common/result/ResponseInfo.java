package cn.seu.weme.common.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by LCN on 2017-1-3.
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ResponseInfo {
    private String state;
    private String reason;
    private Long id;
    private String token;
    private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ResponseInfo() {
    }

    public ResponseInfo(String state, String reason) {
        this.state = state;
        this.reason = reason;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
