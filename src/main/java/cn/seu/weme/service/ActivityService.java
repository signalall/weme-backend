package cn.seu.weme.service;

import cn.seu.weme.common.result.ResponseInfo;
import cn.seu.weme.common.result.ResultInfo;
import cn.seu.weme.dto.old.ActivityVo;
import springfox.documentation.service.ApiListing;

import java.util.List;
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

    public Map searchactivity(String token, String text);


    public Map getLikeActivities(String token, int page);

    public Map getAttendActivities(String token, int page);

    public Map getPublishActivities(String token, int page);

    public Map getPublishActivityDetail(String token, Long activityId);

    public Map getActivityStatistic(String token, Long activityId);

    public ResponseInfo validateActivityUser(String token, Long activityId, Long userId);

    public ResponseInfo getActivityAttendUsers(String token, Long activityId, int page);

    /////add

    public Map setPassActivity(String token, List<Long>activitylist);

    public Map setNoPassActivity(String token,List<Long> activitylist);




    public ResponseInfo setPassUser(String token, Long activityId, List<Long> userIds);


    public ResponseInfo deletePassUser(String token, Long activityId, List<Long> userIds);


    public ResponseInfo commentToActivity(String token, Long activityId, String body);

    public ResponseInfo commentToActivityComent(String token, Long commentId, String body);


    public ResponseInfo likeAcivityComment(String token, Long commentId);


}
