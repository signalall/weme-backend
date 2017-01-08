package cn.seu.weme.service.impl;

import cn.seu.weme.common.result.ResponseInfo;
import cn.seu.weme.dao.*;
import cn.seu.weme.dto.PostVo;
import cn.seu.weme.entity.*;
import cn.seu.weme.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private UserLikePostRelationDao userLikePostRelationDao;

    @Autowired
    private UserLikeCommentRelationDao userLikeCommentRelationDao;

    @PersistenceContext
    private EntityManager entityManager;

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

        User user = userDao.findByToken(token);
        Post post = new Post();
        Topic topic = topicDao.findOne(postVo.getTopicId());

        post.setTopic(topic);
        post.setTitle(postVo.getTitle());
        post.setBody(postVo.getBody());
        post.setPublishUser(user);
        postDao.save(post);

        ResponseInfo responseInfo = new ResponseInfo();

        responseInfo.setState("successful");
        responseInfo.setReason("");
        responseInfo.setId(post.getId());

        return responseInfo;
    }

    @Override
    public ResponseInfo commentToPost(String token, Long postId, String body) {
        User user = userDao.findByToken(token);
        Post post = postDao.findOne(postId);
        Comment comment = new Comment();

        comment.setAuthorUser(user);
        comment.setPost(post);
        comment.setContent(body);
        commentDao.save(comment);

        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setState("successful");
        responseInfo.setReason("");
        responseInfo.setId(comment.getId());
        return responseInfo;
    }

    @Override
    public ResponseInfo commentToComment(String token, Long commentId, String body) {
        User user = userDao.findByToken(token);
        Comment beComment = commentDao.findOne(commentId);

        Comment comment = new Comment();
        comment.setAuthorUser(user);
        comment.setComment(beComment);
        comment.setContent(body);
        commentDao.save(comment);

        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setState("successful");
        responseInfo.setReason("");
        responseInfo.setId(comment.getId());
        return responseInfo;
    }

    @Override
    public ResponseInfo likePost(String token, Long postId) {
        User user = userDao.findByToken(token);
        Post post = postDao.findOne(postId);
        UserLikePostRelation userLikePostRelation = new UserLikePostRelation();
        userLikePostRelation.setUser(user);
        userLikePostRelation.setPost(post);
        userLikePostRelationDao.save(userLikePostRelation);

        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setState("successful");
        responseInfo.setReason("");
        return responseInfo;
    }

    @Override
    public ResponseInfo likeComment(String token, Long commentId) {
        User user = userDao.findByToken(token);
        Comment comment = commentDao.findOne(commentId);

        UserLikeCommentRelation userLikeCommentRelation = new UserLikeCommentRelation(user, comment);

        userLikeCommentRelationDao.save(userLikeCommentRelation);

        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setState("successful");
        responseInfo.setReason("");
        return responseInfo;
    }

    @Override
    public ResponseInfo getTopicSlogan(String token, Long topicId) {
        User user = userDao.findByToken(token);
        Topic topic = topicDao.findOne(topicId);

        Map<String, Object> result = new HashMap<>();
        result.put("id", topic.getId());
        result.put("theme", topic.getTheme());
        result.put("slogan", topic.getSlogan());
        result.put("imageurl", "");// TODO: 2017-1-7
        result.put("note", topic.getNote());
        result.put("number", topic.getNumber());// TODO: 2017-1-7

        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setState("successful");
        responseInfo.setReason("");
        responseInfo.setResult(result);
        return responseInfo;
    }

    @Override
    public ResponseInfo getTopicPostList(String token, Long topicId, int page) {
        User user = userDao.findByToken(token);

        int pageSize = 10;
        int index = (page - 1) * 10;
        Query query = entityManager.createQuery("from Post p where p.topic.id = ?1 order by p.timestamp");
        query.setParameter(1, topicId);
        List<Post> posts = query.setMaxResults(pageSize).setFirstResult(index).getResultList();

        List<Map> result = new ArrayList<>();


        for (Post post : posts) {
            User author = post.getPublishUser();
            Map<String, Object> data = new HashMap<>();
            data.put("postid", post.getId());
            data.put("userid", author.getId());
            data.put("name", author.getName());
            data.put("school", author.getSchool());
            data.put("gender", author.getGender());
            data.put("timestamp", post.getTimestamp());
            data.put("title", post.getTitle());
            data.put("body", post.getBody());
            data.put("likenumber", post.getUserLikePostRelations().size());
            data.put("commentnumber", post.getComments().size());
            data.put("imageurl", "");
            data.put("thumbnail", "");
            data.put("certification", author.getCertification());

            result.add(data);
        }


        entityManager.clear();

        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setState("successful");
        responseInfo.setReason("");
        responseInfo.setResult(result);
        return responseInfo;
    }

    @Override
    public ResponseInfo getPostDetail(String token, Long postId) {

        Post post = postDao.findOne(postId);
        User author = post.getPublishUser();
        Map<String, Object> data = new HashMap<>();
        data.put("postid", post.getId());
        data.put("userid", author.getId());
        data.put("name", author.getName());
        data.put("school", author.getSchool());
        data.put("gender", author.getGender());
        data.put("timestamp", post.getTimestamp());
        data.put("title", post.getTitle());
        data.put("body", post.getBody());
        data.put("likenumber", post.getUserLikePostRelations().size());
        data.put("commentnumber", post.getComments().size());
        data.put("imageurl", "");
        data.put("thumbnail", "");
        data.put("certification", author.getCertification());

        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setState("successful");
        responseInfo.setReason("");
        responseInfo.setResult(data);
        return responseInfo;
    }

    @Override
    public ResponseInfo getUserTimeline(String token, Long userId, int page) {

        User user = userDao.findOne(userId);


        int pageSize = 10;
        int index = (page - 1) * 10;
        Query query = entityManager.createQuery("from Post p where p.publishUser.id = ?1 and p.disable = false order by p.timestamp");
        query.setParameter(1, userId);
        List<Post> posts = query.setMaxResults(pageSize).setFirstResult(index).getResultList();

        List<Map> result = new ArrayList<>();

        for (Post post : posts) {
            User author = post.getPublishUser();
            Map<String, Object> data = new HashMap<>();
            data.put("postid", post.getId());
            data.put("title", post.getTitle());
            data.put("body", post.getBody());
            data.put("topic", post.getTopic().getTheme());
            data.put("image", "");
            result.add(data);
        }


        entityManager.clear();

        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setState("successful");
        responseInfo.setReason("");
        responseInfo.setResult(result);
        return responseInfo;
    }


    //get user posted images
    @Override
    public ResponseInfo getUserImages(String token, Long userId, int page) {

        User user = userDao.findOne(userId);

        int pageSize = 4;
        int index = (page - 1) * pageSize;
        Query query = entityManager.createQuery("from Post p where p.publishUser.id = ?1 and p.disable = false order by p.timestamp");
        query.setParameter(1, userId);
        List<Post> posts = query.setMaxResults(pageSize).setFirstResult(index).getResultList();

        List<Map> result = new ArrayList<>();

        for (Post post : posts) {
            User author = post.getPublishUser();
            Map<String, Object> data = new HashMap<>();
            data.put("postid", post.getId());
            data.put("title", post.getTitle());
            data.put("body", post.getBody());
            data.put("topic", post.getTopic().getTheme());
            data.put("image", "");
            data.put("thumbnail", "");
            result.add(data);
        }
        entityManager.clear();

        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setState("successful");
        responseInfo.setReason("");
        responseInfo.setResult(result);
        return responseInfo;
    }

    @Override
    public ResponseInfo getPostComments(String token, Long postId, int page) {

        int pageSize = 10;
        int index = (page - 1) * pageSize;
        Query query = entityManager.createQuery("from Comment c where c.post.id = ?1  order by c.timestamp");
        query.setParameter(1, postId);
        List<Comment> comments = query.setMaxResults(pageSize).setFirstResult(index).getResultList();
        List<Map> result = new ArrayList<>();

        for (Comment comment : comments) {
            Map<String, Object> data = new HashMap<>();
            data.put("id", comment.getId());
            data.put("image", "");
            data.put("thumbnail", "");
            data.put("userid", "");
            data.put("name", "");
            data.put("school", "");
            data.put("gender", "");
            data.put("timestamp", "");
            data.put("body", "");
            data.put("likenumber", "");
            data.put("commentnumber", "");
            data.put("reply", "");
            data.put("flag", "");
            data.put("certification", "");
            result.add(data);
        }
        entityManager.clear();

        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setState("successful");
        responseInfo.setReason("");
        responseInfo.setResult(result);
        return responseInfo;
    }

    @Override
    public ResponseInfo getPostLikeUsers(String token, Long postId, int page) {

        int pageSize = 10;
        int index = (page - 1) * pageSize;
        Query query = entityManager.createQuery("from UserLikePostRelation ulr where ulr.post.id = ?1  order by ulr.timestamp");
        query.setParameter(1, postId);
        List<UserLikePostRelation> userLikePostRelations = query.setMaxResults(pageSize).setFirstResult(index).getResultList();
        List<Map> result = new ArrayList<>();
        for (UserLikePostRelation userLikePostRelation : userLikePostRelations) {
            User user = userLikePostRelation.getUser();
            Map<String, Object> data = new HashMap<>();
            data.put("id", user.getId());
            data.put("name", user.getName());
            data.put("school", user.getSchool());
            data.put("gender", user.getGender());
            result.add(data);
        }
        entityManager.clear();

        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setState("successful");
        responseInfo.setReason("");
        responseInfo.setResult(result);
        return responseInfo;
    }

    @Override
    public ResponseInfo getCommentByCommentId(String token, Long commentId, int page) {

        Comment comment = commentDao.findOne(commentId);
        Map<String, Object> data = new HashMap<>();

        data.put("id", "");
        data.put("userid", "");
        data.put("image", "");
        data.put("thumbnail", "");
        data.put("name", "");
        data.put("school", "");
        data.put("gender", "");
        data.put("timestamp", "");

        data.put("body", "");
        data.put("likenumber", "");
        data.put("commentnumber", "");
        data.put("reply", "");
        data.put("flag", "");


        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setState("successful");
        responseInfo.setReason("");
        responseInfo.setResult(data);
        return responseInfo;
    }
}
