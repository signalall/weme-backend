package cn.seu.weme.service.impl;

import cn.seu.weme.common.result.ResponseInfo;
import cn.seu.weme.dao.CommentDao;
import cn.seu.weme.dao.MessageDao;
import cn.seu.weme.dao.UserDao;
import cn.seu.weme.entity.Comment;
import cn.seu.weme.entity.Message;
import cn.seu.weme.entity.User;
import cn.seu.weme.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by LCN on 2017-1-5.
 */
@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private CommentDao commentDao;

    @Override
    public ResponseInfo getUnReadCommentNum(String token) {
        ResponseInfo responseInfo = new ResponseInfo();
        User user = userDao.findByToken(token);
        int number = 0;
        number += messageDao.getUnReadNumByUserId(user.getId());

        number += commentDao.getUnReadPostCommentNum(user.getId());

        number += commentDao.getUnReadCommentNum(user.getId());

        responseInfo.setState("successful");
        responseInfo.setReason("");
        responseInfo.setNumber(number);


        return responseInfo;
    }

    @Override
    public ResponseInfo readComment(String token, Long commentId) {
        ResponseInfo responseInfo = new ResponseInfo();
        User user = userDao.findByToken(token);
        Comment comment = commentDao.findOne(commentId);
        if (!comment.getToUser().getId().equals(user.getId())) {

            responseInfo.setState("fail");
            responseInfo.setReason("");
            return responseInfo;
        }
        commentDao.readComment(commentId);
        responseInfo.setState("successful");
        responseInfo.setReason("");
        return responseInfo;
    }

    @Override
    public ResponseInfo getAllUnReadComment(String token) {
        ResponseInfo responseInfo = new ResponseInfo();
        User user = userDao.findByToken(token);
        // TODO: 2017-1-7


        responseInfo.setState("successful");
        responseInfo.setReason("");
        return responseInfo;
    }

    @Override
    public ResponseInfo sendMessage(String token, Long userId, String text) {
        User sender = userDao.findByToken(token);
        User toUser = userDao.findOne(userId);

        Message message = new Message();
        message.setSendFrom(sender);
        message.setSendTo(toUser);
        message.setText(text);
        messageDao.save(message);
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setState("successful");
        responseInfo.setReason("");
        responseInfo.setId(message.getId());

        return responseInfo;
    }

    @Override
    public ResponseInfo readMessage(String token, Long messageId) {

        messageDao.readMessage(messageId);
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setState("successful");
        responseInfo.setReason("");
        return responseInfo;
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
        User user = userDao.findByToken(token);
        int num = 0;
        num += messageDao.getUnReadNumByUserId(user.getId());


        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setState("successful");
        responseInfo.setReason("");
        responseInfo.setNumber(num);
        return responseInfo;
    }

    @Override
    public ResponseInfo systemNotification(String token) {
        User user = userDao.findByToken(token);

        return null;
    }
}
