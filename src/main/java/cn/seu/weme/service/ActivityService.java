package cn.seu.weme.service;

import cn.seu.weme.common.result.ResultInfo;
import cn.seu.weme.dto.old.ActivityVo;

import java.util.Map;

/**
 * Created by LCN on 2016-12-18.
 */
public interface ActivityService {

    public ResultInfo addActivity(ActivityVo activityVo);

    public ResultInfo updateActivity(ActivityVo activityVo);

    public ResultInfo getActivityById(Long id);

    public ResultInfo getAllActities();

    public ResultInfo deleteActivityById(Long id);

    public void createActivities();


    public Map getActivitiesInfo(String token, int page);

    public Map getActivityDetail(String token, Long activityId);
}
