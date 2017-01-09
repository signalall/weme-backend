package cn.seu.weme.service.impl;

import cn.seu.weme.common.result.ResponseInfo;
import cn.seu.weme.common.result.ResultInfo;
import cn.seu.weme.common.result.ResultUtil;
import cn.seu.weme.common.utils.MyBeanUtils;
import cn.seu.weme.dao.*;
import cn.seu.weme.dto.old.ActivityVo;
import cn.seu.weme.entity.*;
import cn.seu.weme.service.ActivityService;
import com.sun.xml.internal.ws.api.model.MEP;
import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
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
 * Created by LCN on 2016-12-18.
 */

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private UserAttendActivityRelationDao userAttendActivityRelationDao;

    @Autowired
    private UserLikeCommentRelationDao userLikeCommentRelationDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ModelMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public ResultInfo addActivity(ActivityVo activityVo) {
        Activity activity = mapper.map(activityVo, Activity.class);
        activityDao.save(activity);
        return ResultUtil.createSuccess("添加活动成功");
    }

    @Override
    public ResultInfo updateActivity(ActivityVo activityVo) {
        Activity activity = activityDao.findOne(activityVo.getId());
        if (activity == null) {
            return ResultUtil.createFail("没有该活动");
        }
        MyBeanUtils.copyProperties(activityVo, activity);
        activityDao.save(activity);
        return ResultUtil.createSuccess("更新活动成功");
    }

    @Override
    public ResultInfo getActivityById(Long id) {
        Activity activity = activityDao.findOne(id);
        ActivityVo activityVo = mapper.map(activity, ActivityVo.class);
        return ResultUtil.createSuccess("活动信息", activityVo);
    }

    @Override
    public ResultInfo getAllActities() {
        List<Activity> activities = (List<Activity>) activityDao.findAll();
        List<ActivityVo> activityVos = new ArrayList<>();
        for (Activity activity : activities) {
            activityVos.add(mapper.map(activity, ActivityVo.class));
        }
        return ResultUtil.createSuccess("活动信息", activityVos);
    }

    @Override
    public ResultInfo deleteActivityById(Long id) {
        return null;
    }

    @Override
    public void createActivities() {
        for (int i = 0; i < 10; i++) {
            Activity activity = new Activity();
            activityDao.save(activity);
        }
    }

    @Override
    public Map getActivitiesInfo(String token, int page) {
        Long userId = userDao.findByToken(token).getId();
        int pageSize = 10;
        int index = (page - 1) * 10;
        Query query = entityManager.createQuery("from Activity activity order by activity.timestamp");
        List<Activity> activities = query.setMaxResults(pageSize).setFirstResult(index).getResultList();

        List<ActivityVo> activityVos = new ArrayList<>();

        for (Activity activity : activities) {
            activityVos.add(activityToVo(activity, userId));
        }
        entityManager.clear();

        Map<String, Object> result = new HashMap<>();
        result.put("result", activityVos);
        result.put("state", "successful");
        result.put("pages", 0);
        result.put("reason", "");

        return result;
    }

    @Override
    public Map getActivityDetail(String token, Long activityId) {
        Long userId = userDao.findByToken(token).getId();

        Activity activity = activityDao.findOne(activityId);
        ActivityVo activityVo = mapper.map(activity, ActivityVo.class);
        if (activity.getAuthorUser() != null) {
            activityVo.setSchool(activity.getAuthorUser().getSchool());
            activityVo.setGender(activity.getAuthorUser().getGender());
        }

        if (userDao.isAttendActivity(userId, activity.getId()) == 0) {
            activityVo.setSignstate("no");
        } else {
            activityVo.setSignstate("yes");
        }

        if (userDao.isLikeActivity(userId, activityId) == 0) {
            activityVo.setLikeflag("0");
        } else {
            activityVo.setLikeflag("1");
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", activityVo);
        result.put("state", "successful");
        result.put("reason", "");
        return result;
    }

    @Override
    public Map searchactivity(String token, String text) {
        Long userId = userDao.findByToken(token).getId();

        List<Activity> activities = activityDao.findActivitiesByTitle("%" + text + "%");
        List<ActivityVo> activityVos = new ArrayList<>();

        for (Activity activity : activities) {
            activityVos.add(activityToVo(activity, userId));
        }

        Map<String, Object> result = new HashMap<>();
        result.put("state", "successful");
        result.put("result", activityVos);
        result.put("reason", "");

        return result;
    }

    @Override
    public Map getLikeActivities(String token, int page) {
        User user = userDao.findByToken(token);
        Long userId = user.getId();
        int pageSize = 10;
        int index = (page - 1) * 10;
        List<ActivityVo> activityVos = new ArrayList<>();

        List<UserLikeActivityRelation> userLikeActivityRelations = user.getUserLikeActivityRelations();

        for (UserLikeActivityRelation userLikeActivityRelation : userLikeActivityRelations) {
            activityVos.add(activityToVo(userLikeActivityRelation.getActivity(), userId));

        }
        Map<String, Object> result = new HashMap<>();
        result.put("result", activityVos);
        result.put("state", "successful");
        result.put("pages", 0);
        result.put("reason", "");
        return result;
    }

    @Override
    public Map getAttendActivities(String token, int page) {
        User user = userDao.findByToken(token);
        Long userId = user.getId();
        int pageSize = 10;
        int index = (page - 1) * 10;

        List<UserAttendActivityRelation> userAttendActivityRelations = user.getUserAttendActivityRelations();
        List<ActivityVo> activityVos = new ArrayList<>();

        for (UserAttendActivityRelation userAttendActivityRelation : userAttendActivityRelations) {
            activityVos.add(activityToVo2(userAttendActivityRelation.getActivity(), userId));
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", activityVos);
        result.put("state", "successful");
        result.put("pages", 0);
        result.put("reason", "");
        return result;
    }

    @Override
    public Map getPublishActivities(String token, int page) {
        User user = userDao.findByToken(token);
        Long userId = userDao.findByToken(token).getId();
        int pageSize = 10;
        int index = (page - 1) * 10;

        Query query = entityManager.createQuery("select a from Activity as a join a.authorUser as u where u.id = ?1");
        List<Activity> activities = query.setMaxResults(pageSize).setFirstResult(index).getResultList();
        List<ActivityVo> activityVos = new ArrayList<>();

        for (Activity activity : activities) {
            activityVos.add(activityToVo2(activity, userId));
        }
        entityManager.clear();

        Map<String, Object> result = new HashMap<>();
        result.put("result", activityVos);
        result.put("state", "successful");
        result.put("pages", 0);
        result.put("reason", "");
        return result;
    }

    @Override
    public Map getPublishActivityDetail(String token, Long activityId) {
        Long userId = userDao.findByToken(token).getId();
        Activity activity = activityDao.findOne(activityId);
        ActivityVo activityVo = mapper.map(activity, ActivityVo.class);
        if (activity.getAuthorUser() != null) {
            activityVo.setSchool(activity.getAuthorUser().getSchool());
            activityVo.setGender(activity.getAuthorUser().getGender());
        }
        if (activity.getPassFlag() == 1) {
            activityVo.setPassState("已通过");
        } else if (activity.getPassFlag() == 2) {
            activityVo.setPassState("未通过");
        } else {
            activityVo.setPassState("审核中");
        }
        //todo poster 图片

        activityVo.setSignnumber(activity.getUserAttendActivityRelations().size() + "");

        Map<String, Object> result = new HashMap<>();
        result.put("result", activityVo);
        result.put("state", "successful");
        result.put("pages", 0);
        result.put("reason", "");

        return result;
    }

    @Override
    public Map getActivityStatistic(String token, Long activityId) {
        Activity activity = activityDao.findOne(activityId);

        Query query = entityManager.createNativeQuery("select count(a) from Activity as a WHERE a.id = ?1 " +
                "WHERE a.timestamp BETWEEN ?2 AND ?3");
        DateTime nowTime = new DateTime();
        query.setParameter(1, activityId);
        query.setParameter(2, nowTime.withTimeAtStartOfDay());
        query.setParameter(3, nowTime.millisOfDay().withMaximumValue());


        int registeredTotal = query.executeUpdate();
        int likedTotal = activityDao.findOne(activityId).getUserLikeActivityRelations().size();


        Map<String, Object> data = new HashMap<>();

        data.put("registeredTotal", registeredTotal);
        data.put("likedTotal", likedTotal);
        data.put("activity", activity.getTitle());

        Map<String, Object> result = new HashMap<>();
        result.put("result", data);
        result.put("state", "successful");
        result.put("reason", "");
        return result;
    }

    @Override
    public ResponseInfo validateActivityUser(String token, Long activityId, Long userId) {
        User athorUser = userDao.findByToken(token);
        Activity activity = activityDao.findOne(activityId);
        ResponseInfo responseInfo = new ResponseInfo();
        if (athorUser == null || activity == null || !activity.getAuthorUser().getId().equals(athorUser.getId())) {
            responseInfo.setState("fail");
            responseInfo.setReason("invalid user or activity");
            responseInfo.setResult("");
            return responseInfo;
        }
        UserAttendActivityRelation userAttendActivityRelation = userAttendActivityRelationDao.
                findUserAttendActivityRelation(userId, activityId);
        if (userAttendActivityRelation == null) {
            responseInfo.setState("fail");
            responseInfo.setReason("该用户未报名该活动");
            responseInfo.setResult("");
        } else {
            if (userAttendActivityRelation.getState() == 0) {
                responseInfo.setState("successful");
                responseInfo.setReason("该用户未收到邀请");
                responseInfo.setResult("0");
            } else {
                responseInfo.setState("successful");
                responseInfo.setReason("");
                responseInfo.setResult("1");
            }
        }

        return responseInfo;
    }

    @Override
    public ResponseInfo getActivityAttendUsers(String token, Long activityId, int page) {
        Activity activity = activityDao.findOne(activityId);

        List<Map> data = new ArrayList<>();

        List<UserAttendActivityRelation> userAttendActivityRelations = activity.getUserAttendActivityRelations();

        for (UserAttendActivityRelation userAttendActivityRelation : userAttendActivityRelations) {
            User user = userAttendActivityRelation.getUser();
            Map<String, Object> map = new HashMap<>();
            String flag = userAttendActivityRelation.getState() == 0 ? "0" : "1";
            map.put("id", user.getId());
            map.put("name", user.getName());
            map.put("school", user.getSchool());
            map.put("gender", user.getGender());
            map.put("flag", flag);
            map.put("image", "");
            map.put("thumbnail", "");// TODO: 2017-1-4
            data.add(map);
        }

        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setState("successful");
        responseInfo.setReason("");
        responseInfo.setResult("1");
        responseInfo.setResult(data);

        return responseInfo;
    }

    @Override
    public ResponseInfo setPassUser(String token, Long activityId, List<Long> userIds) {
        User user = userDao.findByToken(token);
        Activity activity = activityDao.findOne(activityId);
        ResponseInfo responseInfo = new ResponseInfo();
        if (!user.getId().equals(activity.getAuthorUser().getId())) {
            responseInfo.setState("fail");
            responseInfo.setReason("非法用户");
            responseInfo.setResult("");
            return responseInfo;
        }


        for (Long id : userIds) {
            UserAttendActivityRelation userattendActivityRelation =
                    userAttendActivityRelationDao.findUserAttendActivityRelation(id, activityId);

            if (userattendActivityRelation != null) {
                userattendActivityRelation.setState(1);
                userAttendActivityRelationDao.save(userattendActivityRelation);
            }

        }

        responseInfo.setState("successful");
        responseInfo.setReason("");
        responseInfo.setResult("");
        return responseInfo;
    }

    @Override
    public ResponseInfo deletePassUser(String token, Long activityId, List<Long> userIds) {
        User user = userDao.findByToken(token);
        Activity activity = activityDao.findOne(activityId);
        ResponseInfo responseInfo = new ResponseInfo();
        if (!user.getId().equals(activity.getAuthorUser().getId())) {
            responseInfo.setState("fail");
            responseInfo.setReason("非法用户");
            responseInfo.setResult("");
            return responseInfo;
        }


        for (Long id : userIds) {
            UserAttendActivityRelation userattendActivityRelation =
                    userAttendActivityRelationDao.findUserAttendActivityRelation(id, activityId);

            if (userattendActivityRelation != null) {
                userattendActivityRelation.setState(0);
                userAttendActivityRelationDao.save(userattendActivityRelation);
            }

        }

        responseInfo.setState("successful");
        responseInfo.setReason("");
        responseInfo.setResult("");
        return responseInfo;
    }

    @Override
    public ResponseInfo commentToActivity(String token, Long activityId, String body) {
        User user = userDao.findByToken(token);
        Activity activity = activityDao.findOne(activityId);
        Comment comment = new Comment();
        comment.setAuthorUser(user);
        comment.setActivity(activity);
        comment.setContent(body);
        commentDao.save(comment);

        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setState("successful");
        responseInfo.setReason("");
        responseInfo.setResult("");
        return responseInfo;
    }

    @Override
    public ResponseInfo commentToActivityComent(String token, Long commentId, String body) {
        User user = userDao.findByToken(token);
        Comment comment = commentDao.findOne(commentId);

        Comment commentNew = new Comment();
        commentNew.setContent(body);
        commentNew.setComment(comment);
        commentNew.setAuthorUser(user);
        commentDao.save(commentNew);

        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setState("successful");
        responseInfo.setReason("");
        responseInfo.setResult("");
        return responseInfo;
    }

    @Override
    public ResponseInfo likeAcivityComment(String token, Long commentId) {
        User user = userDao.findByToken(token);
        Comment comment = commentDao.findOne(commentId);

        UserLikeCommentRelation userLikeCommentRelation = new UserLikeCommentRelation(user, comment);
        userLikeCommentRelationDao.save(userLikeCommentRelation);

        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setState("successful");
        responseInfo.setReason("");
        responseInfo.setResult("");
        return responseInfo;
    }

    private ActivityVo activityToVo(Activity activity, Long userId) {
        ActivityVo activityVo = mapper.map(activity, ActivityVo.class);
        if (activity.getAuthorUser() != null) {
            activityVo.setSchool(activity.getAuthorUser().getSchool());
            activityVo.setGender(activity.getAuthorUser().getGender());
        }
        activityVo.setSignnumber(activity.getUserAttendActivityRelations().size() + "");

        if (userDao.isAttendActivity(userId, activity.getId()) == 0) {
            activityVo.setSignstate("no");
        } else {
            activityVo.setSignstate("yes");
        }
        return null;
    }


    private ActivityVo activityToVo2(Activity activity, Long userId) {
        ActivityVo activityVo = mapper.map(activity, ActivityVo.class);
        if (activity.getAuthorUser() != null) {
            activityVo.setSchool(activity.getAuthorUser().getSchool());
            activityVo.setGender(activity.getAuthorUser().getGender());
        }
        activityVo.setSignnumber(activity.getUserAttendActivityRelations().size() + "");

        if (activity.getPassFlag() == 1) {
            activityVo.setPassState("已通过");
        } else if (activity.getPassFlag() == 2) {
            activityVo.setPassState("未通过");
        } else {
            activityVo.setPassState("审核中");
        }

        return activityVo;
    }
    ////////

    @Override
    public Map setPassActivity(String token,List<Long >activitylist)
    {
        Map<String,Object> result = new HashMap<>();
        User user= userDao.findByToken(token);
        if(user!=null && user.getUsername()=="adminstrator"){

            for(Long tempid:activitylist)
            {
                Activity activity = activityDao.findOne(tempid);
                activity.setPassFlag(1);
                activityDao.save(activity);
            }
            result.put("state","successful");
            result.put("reason","");
            return result;
        }
        else
        {
            result.put("state","fail");
            result.put("reason","非法用户");
            return result;
        }
    }


    //////
    @Override
    public Map setNoPassActivity(String token,List<Long> activitylist)
    {
        Map<String,Object> result = new HashMap<>();

        User user =userDao.findByToken(token);
        if(user!=null && user.getUsername()=="adminstrator"){

            for(Long tempid:activitylist)
            {
                Activity activity = activityDao.findOne(tempid);
                activity.setPassFlag(0);
                activityDao.save(activity);
            }
            result.put("state","successful");
            result.put("reason","");
            return result;
        }
        else
        {
            result.put("state","fail");
            result.put("reason","非法用户");
            return result;
        }


    }

}
