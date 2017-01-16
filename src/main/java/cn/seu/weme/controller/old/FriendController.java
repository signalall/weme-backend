package cn.seu.weme.controller.old;

import cn.seu.weme.common.result.ResponseInfo;
import cn.seu.weme.service.CheckUserService;
import cn.seu.weme.service.FriendService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LCN on 2017-1-3.
 */
@RestController
//@RequestMapping(value = "/friends_route")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @Autowired
    private CheckUserService checkUserService;

    @RequestMapping(value = "/visit", method = RequestMethod.POST)
    public ResponseInfo visit(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return failResponse();
        }
        Long userId = jsonObject.getLong("id");
        return friendService.visit(token, userId);
    }


    @RequestMapping(value = "/visitinfo", method = RequestMethod.POST)
    public ResponseInfo visitInfo(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return failResponse();
        }
        Long userId = jsonObject.getLong("id");
        return friendService.getVisitInfo(userId);
    }


    @RequestMapping(value = "/follow", method = RequestMethod.POST)
    public ResponseInfo follow(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return failResponse();
        }
        Long userId = jsonObject.getLong("id");
        return friendService.follow(token, userId);
    }


    @RequestMapping(value = "/unfollow", method = RequestMethod.POST)
    public ResponseInfo unfollow(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return failResponse();
        }
        Long userId = jsonObject.getLong("id");
        return friendService.unFollow(token, userId);
    }


    @RequestMapping(value = "/followview", method = RequestMethod.POST)
    public ResponseInfo followView(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return failResponse();
        }
        int page = jsonObject.getInt("page");
        String direction = jsonObject.getString("direction");
        int direct = 0;
        if (direction.equals("followers"))
            direct = 0;
        else {
            direct = 1;
        }
        return friendService.getFollowView(token, page, direct);
    }


    @RequestMapping(value = "/searchuser", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseInfo searchUser(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return failResponse();
        }
        String text = jsonObject.getString("text");
        return friendService.searchUser(token, text);
    }

    @RequestMapping(value = "/getrecommenduser", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseInfo getReCommendUser(@RequestBody JSONObject jsonObject) {

        return null;
    }


    @RequestMapping(value = "/getrecommendusers", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseInfo getReCommendUsers(@RequestBody JSONObject jsonObject) {

        return null;
    }

    @RequestMapping(value = "/likeusercard", method = RequestMethod.POST)
    public ResponseInfo likeUserCard(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        Long userId = jsonObject.getLong("userid");
        return friendService.likeUserCard(token, userId);
    }


    @RequestMapping(value = "/unlikeusercard", method = RequestMethod.POST)
    public ResponseInfo unLikeUserCard(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return failResponse();
        }
        Long userId = jsonObject.getLong("userid");

        return friendService.unLikeUserCard(token, userId);
    }


    @RequestMapping(value = "/getlikeusernumber", method = RequestMethod.POST)
    public ResponseInfo getLikeUserNumber(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return failResponse();
        }
        return friendService.getLikeUserNumber(token);
    }


    private ResponseInfo failResponse() {
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setState("fail");
        responseInfo.setReason("用户不存在");
        return responseInfo;
    }
}
