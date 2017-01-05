package cn.seu.weme.service.impl;

import cn.seu.weme.common.result.ResponseInfo;
import cn.seu.weme.service.MessageService;

/**
 * Created by LCN on 2017-1-5.
 */
public class MessageServiceImpl implements MessageService{
    @Override
    public ResponseInfo getUnReadCommentNum(String token) {
        return null;
    }

    @Override
    public ResponseInfo readComment(String token, Long commentId) {
        return null;
    }

    @Override
    public ResponseInfo getAllUnReadComment(String token) {
        return null;
    }

    @Override
    public ResponseInfo sendMessage(String token, Long userId, String text) {
        return null;
    }

    @Override
    public ResponseInfo readMessage(String token, Long messageId) {
        return null;
    }

    @Override
    public ResponseInfo getSendUserList(String token) {
        return null;
    }

    @Override
    public ResponseInfo getMessageDetailList(String token, Long sendId, int page) {
        return null;
    }

    @Override
    public ResponseInfo getUnReadMessageNum(String token) {
        return null;
    }
}
