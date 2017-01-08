package cn.seu.weme.dto.old;

/**
 * Created by LCN on 2017-1-6.
 */
public class UploadFileVo {

    private String token;
    private int type;
    private int number;
    private Long messageId;
    private Long postId;
    private Long topicOfficialId;
    private Long commentId;
    private Long activityId;
    private String path;

    private Long topicId;
    private Long foodCardId;

    public Long getFoodCardId() {
        return foodCardId;
    }

    public void setFoodCardId(Long foodCardId) {
        this.foodCardId = foodCardId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getTopicOfficialId() {
        return topicOfficialId;
    }

    public void setTopicOfficialId(Long topicOfficialId) {
        this.topicOfficialId = topicOfficialId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
