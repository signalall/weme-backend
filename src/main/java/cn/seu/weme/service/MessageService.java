package cn.seu.weme.service;

import cn.seu.weme.common.result.ResponseInfo;

/**
 * Created by LCN on 2017-1-5.
 */
public interface MessageService {

    public ResponseInfo getUnReadCommentNum(String token);

    //设置comment已读
    public ResponseInfo readComment(String token, Long commentId);

    public ResponseInfo systemNotification(String token);


    public ResponseInfo getAllUnReadComment(String token);

    public ResponseInfo sendMessage(String token, Long userId, String text);


    public ResponseInfo readMessage(String token, Long messageId);


    public ResponseInfo getSendUserList(String token);

    public ResponseInfo getMessageDetailList(String token, Long sendId, int page);


    public ResponseInfo getUnReadMessageNum(String token);



}
