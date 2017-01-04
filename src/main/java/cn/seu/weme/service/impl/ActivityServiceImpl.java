package cn.seu.weme.service.impl;

import cn.seu.weme.common.result.ResultInfo;
import cn.seu.weme.common.result.ResultUtil;
import cn.seu.weme.dao.ActivityDao;
import cn.seu.weme.dao.UserDao;
import cn.seu.weme.entity.Activity;
import cn.seu.weme.entity.User;
import cn.seu.weme.service.ActivityService;
import cn.seu.weme.dto.old.ActivityVo;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

/**
 * Created by LCN on 2016-12-18.
 */

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDao activityDao;

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
        BeanUtils.copyProperties(activityVo, activity);
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
        Long userId = userDao.findByToken(token).getId();
        int pageSize = 10;
        int index = (page - 1) * 10;
        Query query = entityManager.createQuery("select a from Activity as a join a.likeUsers as u where u.id = ?1");
        query.setParameter(1, userId);

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
    public Map getAttendActivities(String token, int page) {
        Long userId = userDao.findByToken(token).getId();
        int pageSize = 10;
        int index = (page - 1) * 10;
        Query query = entityManager.createQuery("select a from Activity as a join a.attendUsers as u where u.id = ?1");
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
    public Map getPublishActivities(String token, int page) {
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
//        Long userId = userDao.findByToken(token).getId();
//        Activity activity = activityDao.findOne(activityId);
//        ActivityVo activityVo = mapper.map(activity, ActivityVo.class);
//        if (activity.getAuthorUser() != null) {
//            activityVo.setSchool(activity.getAuthorUser().getSchool());
//            activityVo.setGender(activity.getAuthorUser().getGender());
//        }
//        if (activity.getPassflag().equals(1)) {
//            activityVo.setPassState("已通过");
//        } else if (activity.getPassflag().equals(2)) {
//            activityVo.setPassState("未通过");
//        } else {
//            activityVo.setPassState("审核中");
//        }
//        //todo poster 图片
//
//        activityVo.setSignnumber(activity.getAttendUsers().size() + "");
//
//        Map<String, Object> result = new HashMap<>();
//        result.put("result", activityVo);
//        result.put("state", "successful");
//        result.put("pages", 0);
//        result.put("reason", "");

        return null;
    }

    @Override
    public Map getActivityStatistic(String token, Long activityId) {
//        Activity activity = activityDao.findOne(activityId);
//
//        Query query = entityManager.createNativeQuery("select count(a) from Activity as a WHERE a.id = ?1 WHERE TIMESTAMP BETWEEN ?2 AND ?3");
//        query.setParameter(1, activityId);
//
//
//        int registeredTotal = query.executeUpdate();
//        int likedTotal = activityDao.findOne(activityId).getLikeUsers().size();
//
//
//        Map<String, Object> data = new HashMap<>();
//
//        data.put("registeredTotal", registeredTotal);
//        data.put("likedTotal", likedTotal);
//        data.put("activity", activity.getTitle());
//
//        Map<String, Object> result = new HashMap<>();
//        result.put("result", data);
//        result.put("state", "successful");
//        result.put("reason", "");
        return null;
    }

    private ActivityVo activityToVo(Activity activity, Long userId) {
//        ActivityVo activityVo = mapper.map(activity, ActivityVo.class);
//        if (activity.getAuthorUser() != null) {
//            activityVo.setSchool(activity.getAuthorUser().getSchool());
//            activityVo.setGender(activity.getAuthorUser().getGender());
//        }
//        activityVo.setSignnumber(activity.getAttendUsers().size() + "");
//
//        if (userDao.isAttendActivity(userId, activity.getId()) == 0) {
//            activityVo.setSignstate("no");
//        } else {
//            activityVo.setSignstate("yes");
//        }
        return null;
    }


    private ActivityVo activityToVo2(Activity activity, Long userId) {
//        ActivityVo activityVo = mapper.map(activity, ActivityVo.class);
//        if (activity.getAuthorUser() != null) {
//            activityVo.setSchool(activity.getAuthorUser().getSchool());
//            activityVo.setGender(activity.getAuthorUser().getGender());
//        }
//        activityVo.setSignnumber(activity.getAttendUsers().size() + "");
//
//        if (activity.getPassflag().equals(1)) {
//            activityVo.setPassState("已通过");
//        } else if (activity.getPassflag().equals(2)) {
//            activityVo.setPassState("未通过");
//        } else {
//            activityVo.setPassState("审核中");
//        }

        return null;
    }
}
