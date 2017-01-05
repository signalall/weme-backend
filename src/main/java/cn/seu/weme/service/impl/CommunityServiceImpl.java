package cn.seu.weme.service.impl;

import cn.seu.weme.common.result.ResponseInfo;
import cn.seu.weme.dao.PostDao;
import cn.seu.weme.dao.UserDao;
import cn.seu.weme.dto.PostVo;
import cn.seu.weme.entity.Post;
import cn.seu.weme.entity.User;
import cn.seu.weme.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by LCN on 2017-1-5.
 */
@Service
@Transactional
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PostDao postDao;

    @Override
    public ResponseInfo deletePost(String token, Long postId) {
        ResponseInfo responseInfo = new ResponseInfo();

        User user = userDao.findByToken(token);
        Post post = postDao.findOne(postId);
        if (post == null || !user.getId().equals(post.getPublishUser().getId())) {
            responseInfo.setState("fail");
            responseInfo.setReason("invalid");
            return responseInfo;
        }
        post.setDisable(true);
        postDao.save(post);

        responseInfo.setState("successful");
        responseInfo.setReason("");
        return responseInfo;
    }

    @Override
    public ResponseInfo publishPost(String token, PostVo postVo) {
        return null;
    }

    @Override
    public ResponseInfo commentToPost(String token, Long postId, String body) {
        return null;
    }

    @Override
    public ResponseInfo commentToComment(String token, Long CommentId, String body) {
        return null;
    }

    @Override
    public ResponseInfo likePost(String token, Long postId) {
        return null;
    }

    @Override
    public ResponseInfo likeComment(String token, Long commentId) {
        return null;
    }
}
