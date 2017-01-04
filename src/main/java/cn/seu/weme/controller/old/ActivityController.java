package cn.seu.weme.controller.old;

import cn.seu.weme.common.result.ResponseInfo;
import cn.seu.weme.dto.old.ActivityVo;
import cn.seu.weme.service.ActivityService;
import cn.seu.weme.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by LCN on 2017-1-3.
 */
@RestController
@RequestMapping(value = "/activity_route")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseInfo signUp(@RequestBody JSONObject jsonObject) {
        Long activityId = jsonObject.getLong("activity");
        String token = jsonObject.getString("token");
        return userService.attendActivity(token, activityId);
    }


    @RequestMapping(value = "/deletesignup", method = RequestMethod.POST)
    public ResponseInfo deleteSignUp(@RequestBody JSONObject jsonObject) {
        Long activityId = jsonObject.getLong("activity");
        String token = jsonObject.getString("token");
        return userService.unAttendActivity(token, activityId);
    }


    @RequestMapping(value = "/getactivityinformation", method = RequestMethod.POST)
    public Map getActivityInfo(@RequestBody JSONObject jsonObject) {
        int page = jsonObject.getInt("page");
        String token = jsonObject.getString("token");
        return activityService.getActivitiesInfo(token, page);
    }


    @RequestMapping(value = "/publishactivity", method = RequestMethod.POST)
    public Map publishActivity(@RequestBody ActivityVo activityVo) {
        return userService.publishActivity(activityVo);
    }


    @RequestMapping(value = "/activitytopofficial", method = RequestMethod.POST)
    public Map activtyTopOfficial(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        //TODO
        return null;
    }


    @RequestMapping(value = "/getActivityDetail", method = RequestMethod.POST)
    public Map getActivityDetail(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        Long activityId = jsonObject.getLong("activityid");

        return activityService.getActivityDetail(token, activityId);
    }

    @RequestMapping(value = "/likeactivity", method = RequestMethod.POST)
    public Map likeActivity(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        Long activityId = jsonObject.getLong("activityid");
        return userService.likeActivity(token, activityId);
    }


    @RequestMapping(value = "/unlikeactivity", method = RequestMethod.POST)
    public Map unLikeActivity(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        Long activityId = jsonObject.getLong("activityid");
        return userService.unLikeActivity(token, activityId);
    }


    @RequestMapping(value = "/searchactivity", method = {RequestMethod.POST, RequestMethod.GET})
    public Map searchActivity(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        String text = jsonObject.getString("text");
        return activityService.searchactivity(token, text);
    }


    @RequestMapping(value = "/getlikeactivity", method = RequestMethod.POST)
    public Map getLikeActivity(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        int page = jsonObject.getInt("page");
        return activityService.getLikeActivities(token, page);
    }

    @RequestMapping(value = "/getattentactivity", method = RequestMethod.POST)
    public Map getAttendActivities(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        int page = jsonObject.getInt("page");
        return activityService.getAttendActivities(token, page);
    }

    @RequestMapping(value = "/getpublishactivity", method = RequestMethod.POST)
    public Map getPublishActivity(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        int page = jsonObject.getInt("page");
        return activityService.getPublishActivities(token, page);
    }

    @RequestMapping(value = "/getpublishactivitydetail", method = RequestMethod.POST)
    public Map getPublishActivityDetail(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        Long activityId = jsonObject.getLong("activityid");
        return activityService.getPublishActivityDetail(token, activityId);
    }


    @RequestMapping(value = "/getactivitystatistic", method = RequestMethod.POST)
    public Map getActivityStatistic(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        Long activityId = jsonObject.getLong("activityid");
        return activityService.getActivityStatistic(token, activityId);
    }


    @RequestMapping(value = "/validateactivityuser", method = RequestMethod.POST)
    public ResponseInfo validateActivityUser(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        Long activityId = jsonObject.getLong("activityid");
        Long userId = jsonObject.getLong("userId");
        return activityService.validateActivityUser(token, activityId, userId);
    }


    @RequestMapping(value = "/getactivityattentuser", method = RequestMethod.POST)
    public ResponseInfo getActivityAttendUsers(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        Long activityId = jsonObject.getLong("activityid");
        int page = jsonObject.getInt("page");
        return activityService.getActivityAttendUsers(token, activityId, page);
    }


    @RequestMapping(value = "/setpassuser", method = RequestMethod.POST)
    public ResponseInfo setPassUser(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        Long activityId = jsonObject.getLong("activityid");
        List<Long> userIds = jsonObject.getJSONArray("userlist");

        return activityService.setPassUser(token, activityId, userIds);
    }


    @RequestMapping(value = "/deletepassuser", method = RequestMethod.POST)
    public ResponseInfo deletePassUser(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        Long activityId = jsonObject.getLong("activityid");
        List<Long> userIds = jsonObject.getJSONArray("userlist");

        return activityService.deletePassUser(token, activityId, userIds);
    }

    @RequestMapping(value = "/commenttoactivity", method = RequestMethod.POST)
    public ResponseInfo commentToActivity(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        String body = jsonObject.getString("body");
        Long activityId = jsonObject.getLong("activityid");

        return activityService.commentToActivity(token,activityId,body);
    }


    @RequestMapping(value = "/commenttocommentact", method = RequestMethod.POST)
    public ResponseInfo commentToActivityComment(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        String body = jsonObject.getString("body");
        Long commentId = jsonObject.getLong("destcommentid");

        return activityService.commentToActivityComent(token,commentId,body);
    }



    @RequestMapping(value = "/likecommentact", method = RequestMethod.POST)
    public ResponseInfo likeActivityComment(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        Long commentId = jsonObject.getLong("commentid");
// TODO: 2017-1-4 likenumber
        return activityService.likeAcivityComment(token,commentId);
    }

    @RequestMapping(value = "/getactivitycomment", method = RequestMethod.POST)
    public ResponseInfo getActivityComent(@RequestBody JSONObject jsonObject){
        String token = jsonObject.getString("token");
        Long commentId = jsonObject.getLong("commentid");

        Long endId = jsonObject.getLong("endid");

        // TODO: 2017-1-4  
        return null;
    }



}
