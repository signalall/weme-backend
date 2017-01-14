package cn.seu.weme.controller.old;

import cn.seu.weme.service.CheckUserService;
import cn.seu.weme.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LCN on 2017-1-3.
 */
@RestController
//@RequestMapping(value = "/getprofile_route")
public class UserInfoController {

    @Autowired
    private UserService userService;

    @Autowired
    private CheckUserService checkUserService;

    @RequestMapping(value = "/gettagsbyid", method = RequestMethod.POST)
    public Map getTags(@RequestBody JSONObject jsonObject) {
        Map<String, Object> result = new HashMap<>();
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return failResponse();
        }
        Long userId = jsonObject.getLong("userid");
        return userService.getTag(userId);
    }

    @RequestMapping(value = "/settags", method = RequestMethod.POST)
    public Map setTags(@RequestBody JSONObject jsonObject) {
        Map<String, Object> result = new HashMap<>();
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return failResponse();
        }
        String tagJson = jsonObject.getString("tags");
        return userService.setTag(token, tagJson);
    }


    @RequestMapping(value = "/getprofile", method = RequestMethod.POST)
    public Map getUserInfo(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return failResponse();
        }
        return userService.getProfile(token);
    }


    @RequestMapping(value = "/getpersonalimages", method = RequestMethod.POST)
    public Map getPersonImages(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return failResponse();
        }
        Long userId = jsonObject.getLong("userid");
        Long previousImageId = jsonObject.getLong("previous_id");
        return userService.getPersonImages(userId, previousImageId);
    }

    @RequestMapping(value = "/getprofilebyid", method = {RequestMethod.POST, RequestMethod.GET})
    public Map getProfileById(@RequestBody JSONObject jsonObject) {

        String token = jsonObject.getString("token");
        Long userId = jsonObject.getLong("id");

        if (token == null || !checkUserService.validateUser(token)) {
            return failResponse();
        }

        return userService.getProfileById(token, userId);
    }


    @RequestMapping(value = "/getprofilebyidphone", method = {RequestMethod.POST, RequestMethod.GET})
    public Map getProfileByIdPhone(@RequestBody JSONObject jsonObject) {

        String token = jsonObject.getString("token");
        Long userId = jsonObject.getLong("id");
        if (token == null || !checkUserService.validateUser(token)) {
            return failResponse();
        }

        return userService.getProfileByIdPhone(token, userId);
    }

    private Map failResponse() {
        Map<String, Object> result = new HashMap<>();
        result.put("state", "fail");
        result.put("reason", "用户不存在");
        return result;
    }


}
