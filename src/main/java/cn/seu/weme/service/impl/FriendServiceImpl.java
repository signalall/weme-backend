package cn.seu.weme.service.impl;

import cn.seu.weme.common.result.ResponseInfo;
import cn.seu.weme.dao.FollowRelationDao;
import cn.seu.weme.dao.LikeUserRelationDao;
import cn.seu.weme.dao.UserDao;
import cn.seu.weme.dao.UserVisitRelationDao;
import cn.seu.weme.entity.FollowRelation;
import cn.seu.weme.entity.LikeUserRelation;
import cn.seu.weme.entity.User;
import cn.seu.weme.entity.UserVisitRelation;
import cn.seu.weme.service.FriendService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import java.util.*;

/**
 * Created by LCN on 2017-1-5.
 */
@Service
@Transactional
public class FriendServiceImpl implements FriendService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserVisitRelationDao userVisitRelationDao;

    @Autowired
    private LikeUserRelationDao likeUserRelationDao;

    @Autowired
    private FollowRelationDao followRelationDao;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public ResponseInfo visit(String token, Long userId) {
        User visiter = userDao.findByToken(token);
        User visited = userDao.findOne(userId);
        UserVisitRelation userVisitRelation = new UserVisitRelation(visiter, visited);
        userVisitRelationDao.save(userVisitRelation);
        visited.setLookCount(visited.getLookCount() + 1);
        userDao.save(visited);


        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setState("successful");
        responseInfo.setReason("");

        return responseInfo;
    }

    @Override
    public ResponseInfo getVisitInfo(Long userId) {
        User user = userDao.findOne(userId);
        int totalCount = user.getVisitedRelations().size();
        Query query = entityManager.createQuery("select count(uvr) from UserVisitRelation as uvr WHERE " +
                "uvr.visited.id=?1 and uvr.timestamp between :startTime and :endTime");
        DateTime nowTime = new DateTime();
        query.setParameter(1, userId);
        query.setParameter("startTime", nowTime.withTimeAtStartOfDay().toDate(), TemporalType.DATE);
        query.setParameter("endTime", nowTime.millisOfDay().withMaximumValue().toDate(), TemporalType.DATE);

        Long todayCount = (Long) query.getSingleResult();

        Map<String, Object> result = new HashMap<>();
        result.put("total", totalCount);
        result.put("today", todayCount);

        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setState("successful");
        responseInfo.setReason("");
        responseInfo.setResult(result);

        return responseInfo;
    }

    @Override
    public ResponseInfo follow(String token, Long userId) {
        User follower = userDao.findByToken(token);
        User followed = userDao.findOne(userId);
        FollowRelation followRelation = new FollowRelation(follower, followed);
        followRelationDao.save(followRelation);

        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setState("successful");
        responseInfo.setReason("");

        return responseInfo;
    }

    @Override
    public ResponseInfo unFollow(String token, Long userId) {
        ResponseInfo responseInfo = new ResponseInfo();
        User follower = userDao.findByToken(token);
        User followed = userDao.findOne(userId);
        FollowRelation followRelation = followRelationDao.findByFollowerAndFollowed(follower.getId(), followed.getId());
        if (followRelation == null) {
            responseInfo.setReason("e");
            responseInfo.setState("fail");
            return responseInfo;
        }

        followRelationDao.delete(followRelation.getId());

        responseInfo.setReason("");
        responseInfo.setState("successful");
        return responseInfo;
    }

    @Override
    public ResponseInfo getFollowView(String token, int page, int direction) {
        User user = userDao.findByToken(token);
        List<Map> result = getFollowedRelationPageAble(user.getId(), page, direction);
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setReason("");
        responseInfo.setState("successful");
        responseInfo.setResult(result);
        return responseInfo;
    }


    private List<Map> getFollowedRelationPageAble(Long userId, int page, int direct) {
        int pageSize = 10;
        int index = (page - 1) * 10;
        Query query;
        if (direct == 1) {
            query = entityManager.createQuery("select fr from  FollowRelation as fr where fr.follower.id = ?1 order by fr.timestamp");
        } else {
            query = entityManager.createQuery("select fr from  FollowRelation as fr where fr.followed.id = ?1 order by fr.timestamp");
        }
        query.setParameter(1, userId);
        List<Map> result = new ArrayList<>();
        List<FollowRelation> followRelations = query.setMaxResults(pageSize).setFirstResult(index).getResultList();
        for (FollowRelation followRelation : followRelations) {
            Map<String, Object> data = new HashMap<>();
            User user;
            if (direct == 1) {
                user = followRelation.getFollowed();
            } else {
                user = followRelation.getFollower();
            }

            data.put("id", user.getId());
            data.put("name", user.getName());
            data.put("gender", user.getGender());
            data.put("school", user.getSchool());
            data.put("timestamp", user.getTimestamp());
            result.add(data);
        }
        entityManager.clear();

        return result;
    }


    @Override
    public ResponseInfo searchUser(String token, String text) {
        User user = userDao.findByToken(token);
        Query query = entityManager.createQuery("select u from  User as u where u.name like :text");
        query.setParameter("text", "%" + text + "%");
        List<User> users = query.setMaxResults(10).getResultList();
        List<Map> result = new ArrayList<>();
        for (User user1 : users) {
            Map<String, Object> data = new HashMap<>();
            data.put("id", user1.getId());
            data.put("name", user1.getName());
            data.put("gender", user1.getGender());
            data.put("school", user1.getSchool());
            result.add(data);
        }

        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setState("successful");
        responseInfo.setReason("");
        responseInfo.setResult(result);
        return responseInfo;
    }

    @Override
    public ResponseInfo getRecommendUser(String token, Long userId) {
        // TODO: 2017-1-5
        return null;
    }

    @Override
    public ResponseInfo getReCommendUsers(String token) {
        User user = userDao.findByToken(token);

        return null;
    }

    @Override
    public ResponseInfo likeUserCard(String token, Long userId) {
        User liker = userDao.findByToken(token);
        User liked = userDao.findOne(userId);
        LikeUserRelation likeUserRelation = new LikeUserRelation(liker, liked);
        likeUserRelationDao.save(likeUserRelation);

        ResponseInfo responseInfo = new ResponseInfo();

        responseInfo.setReason("");
        responseInfo.setState("successful");
        return responseInfo;
    }

    @Override
    public ResponseInfo unLikeUserCard(String token, Long userId) {
        User liker = userDao.findByToken(token);
        User liked = userDao.findOne(userId);
        LikeUserRelation likeUserRelation = likeUserRelationDao.findByLikerAndLiked(liker.getId(), liked.getId());

        if (likeUserRelation != null) {
            likeUserRelationDao.delete(likeUserRelation.getId());
        }

        ResponseInfo responseInfo = new ResponseInfo();

        responseInfo.setReason("");
        responseInfo.setState("successful");
        return responseInfo;
    }

    @Override
    public ResponseInfo getLikeUserNumber(String token) {

        User user = userDao.findByToken(token);
        int likeNumber = user.getLikedRelations().size();

        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setLikenumber(likeNumber);
        responseInfo.setReason("");
        responseInfo.setState("successful");
        return responseInfo;
    }
}
