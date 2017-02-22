package cn.seu.weme.controller.old.admin;

import cn.seu.weme.service.ActivityService;
import cn.seu.weme.service.AvatarCardService;
import com.fasterxml.jackson.annotation.JsonInclude;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by LCN on 2017-1-3.
 */
@RestController
@RequestMapping(value = "/adminuser_route")
public class AdminController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private AvatarCardService avatarCardService;


        @RequestMapping(value = "/getallactivity", method = RequestMethod.POST)
        public Map getAllActivities(@RequestBody JSONObject jsonObject) {
            String token =  jsonObject.getString("token");
            int page = jsonObject.getInt("page");
            int number = jsonObject.getInt("number");

            return activityService.getActivitiesInfo(token,page);
    }


    @RequestMapping(value = "/setpassactivity", method = RequestMethod.POST)
    public Map setPassActivity(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        JSONArray jsonArray = JSONArray.fromObject( jsonObject.getString("activitylist"));
        List<Long> longList =(List<Long>) JSONArray.toCollection(jsonArray);
        return activityService.setPassActivity(token,longList);
    }


    @RequestMapping(value = "/setnopassactivity", method = RequestMethod.POST)
    public Map setNoPassActivity(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        JSONArray jsonArray = JSONArray.fromObject( jsonObject.getString("activitylist"));
        List<Long> longList =(List<Long>) JSONArray.toCollection(jsonArray);
        return activityService.setNoPassActivity(token,longList);
    }

    @RequestMapping(value = "/getallusercard", method = RequestMethod.POST)
    public Map getAllUserCard(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        Integer page = jsonObject.getInt("page");
        Integer number = jsonObject.getInt("number");
        return avatarCardService.getAllUserCard(token,page,number);
    }


    @RequestMapping(value = "/getallusercardfilter", method = RequestMethod.POST)
    public Map getAllUserCardFilter(@RequestBody JSONObject jsonObject) {

        String token = jsonObject.getString("token");
        Integer page = jsonObject.getInt("page");
        Integer number = jsonObject.getInt("number");
        return avatarCardService.getAllUserCard(token,page,number);
    }

    @RequestMapping(value = "/getallusercardbygender", method = RequestMethod.POST)
    public Map getAllUserCardByGender(@RequestBody JSONObject jsonObject) {

        String token = jsonObject.getString("token");
        Integer page = jsonObject.getInt("page");
        Integer number = jsonObject.getInt("number");
        String gender = jsonObject.getString("gender");
        return avatarCardService.getAllUserCardByGendder(token,page,number,gender);

    }


    @RequestMapping(value = "/setpassusercard", method = RequestMethod.POST)
    public Map setPassUserCard(@RequestBody JSONObject jsonObject) {

        String token = jsonObject.getString("token");
        JSONArray jsonArray = JSONArray.fromObject( jsonObject.getString("usercardlist"));
        List<Long> longList =(List<Long>) JSONArray.toCollection(jsonArray);
        return avatarCardService.setPassUserCard(token,longList);
    }


    @RequestMapping(value = "/setnopassusercard", method = RequestMethod.POST)
    public Map setNoPassUserCard(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        JSONArray jsonArray = JSONArray.fromObject( jsonObject.getString("usercardlist"));
        List<Long> longList =(List<Long>) JSONArray.toCollection(jsonArray);
        return avatarCardService.setPassUserCard(token,longList);
    }

}
