package cn.seu.weme.service.impl;

import cn.seu.weme.common.result.ResponseInfo;
import cn.seu.weme.dao.CommentDao;
import cn.seu.weme.dao.MessageDao;
import cn.seu.weme.dao.UserDao;
import cn.seu.weme.entity.Comment;
import cn.seu.weme.entity.Message;
import cn.seu.weme.entity.MessageImage;
import cn.seu.weme.entity.User;
import cn.seu.weme.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.mapreduce.MapReduceCounts;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    //获得两个方向的用户列表
    @Override
    public ResponseInfo getSendUserList(String token) {
        Long userId = userDao.findByToken(token).getId();
        List<Message> sendTos = messageDao.getSendTo(userId);
        List<Message> sendFroms = messageDao.getSendFrom(userId);

        List<Map> result = new ArrayList<>();


        for (Message message : sendFroms) {
            Map<String, Object> data = new HashMap<>();
            User sendFrom = message.getSendFrom();
            if (!message.getState()) {
                data.put("unreadnum", 1);
            } else {
                data.put("unreadnum", 0);
            }
            data.put("SendId", sendFrom.getId());
            data.put("name", sendFrom.getName());
            data.put("gender", sendFrom.getGender());
            data.put("school", sendFrom.getSchool());
            data.put("text", message.getText());
            data.put("lasttime", message.getTimestamp());

            if (sendFrom.getCertification()) {
                data.put("certification", "1");
            } else {
                data.put("certification", "0");
            }
            result.add(data);
        }


        for (Message message : sendTos) {
            Map<String, Object> data = new HashMap<>();
            User sendTo = message.getSendTo();
            data.put("unreadnum", 0);
            data.put("SendId", sendTo.getId());
            data.put("name", sendTo.getName());
            data.put("gender", sendTo.getGender());
            data.put("school", sendTo.getSchool());
            data.put("text", message.getText());
            data.put("lasttime", message.getTimestamp());
            if (sendTo.getCertification()) {
                data.put("certification", "1");
            } else {
                data.put("certification", "0");
            }
            result.add(data);
        }


        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setState("successful");
        responseInfo.setReason("");
        responseInfo.setResult(result);
        return responseInfo;
    }

    @Override
    public ResponseInfo getMessageDetailList(String token, Long sendId, int page) {
        Pageable pageable = new PageRequest(page - 1, 10, Sort.Direction.DESC, "timestamp");
        List<Message> messages = messageDao.getMessageBiDirection(sendId, pageable);
        List<Map> result = new ArrayList<>();
        for (Message message : messages) {
            Map<String, Object> data = new HashMap<>();
            User sendFrom = message.getSendFrom();
            data.put("messageid", message.getId());
            data.put("text", message.getText());

            List<String> imageUrl = new ArrayList<>();
            if (message.isHasImage()) {
                List<MessageImage> images = messageDao.getMessageImages(message.getId());
                for (MessageImage image : images) {
                    imageUrl.add(image.getUrl());
                }
            }

            data.put("image", imageUrl);
            data.put("vedio", message.getTimestamp());
            data.put("time", message.getId());
            if (message.getState()) {
                data.put("readstate", "1");
            } else {
                data.put("readstate", "0");
            }
            data.put("SendId", sendFrom.getId());
            data.put("name", sendFrom.getName());
            data.put("school", sendFrom.getSchool());

            result.add(data);
        }


        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setState("successful");
        responseInfo.setReason("");
        responseInfo.setResult(result);
        return responseInfo;
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
        Long userId = userDao.findByToken(token).getId();

        List<Comment> comments = commentDao.getAllUnReadComments(userId);

        ResponseInfo responseInfo = new ResponseInfo();
        List<Map> result = new ArrayList<>();

        for (Comment comment : comments) {
            Map<String, Object> data = new HashMap<>();
            Map<String, Object> data2 = new HashMap<>();
            Map<String, Object> data3 = new HashMap<>();
            data.put("type", "community");
            data2.put("comment", comment.getContent());
            data2.put("timestamp", comment.getTimestamp());
            data2.put("postid", comment.getPost() == null ? -1 : comment.getPost().getId());
            data2.put("commentid", comment.getId());

            User user = comment.getAuthorUser();
            data3.put("id", user.getId());
            data3.put("name", user.getName());
            data3.put("gender", user.getGender());
            data3.put("school", user.getSchool());
            if (user.getCertification()) {
                data3.put("certification", "1");
            } else {
                data3.put("certification", "0");
            }

            data2.put("author", data3);
            data.put("content", data2);

            result.add(data);
        }
        responseInfo.setState("successful");
        responseInfo.setData(result);
        responseInfo.setReason("");

        return responseInfo;
    }
}
