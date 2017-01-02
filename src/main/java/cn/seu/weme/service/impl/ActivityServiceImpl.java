package cn.seu.weme.service.impl;

import cn.seu.weme.common.result.ResultInfo;
import cn.seu.weme.common.result.ResultUtil;
import cn.seu.weme.dao.ActivityDao;
import cn.seu.weme.entity.Activity;
import cn.seu.weme.service.ActivityService;
import cn.seu.weme.dto.ActivityVo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LCN on 2016-12-18.
 */

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private ModelMapper mapper;

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
            activityVos.add(mapper.map(activity,ActivityVo.class));
        }
        return ResultUtil.createSuccess("活动信息",activityVos);
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
}