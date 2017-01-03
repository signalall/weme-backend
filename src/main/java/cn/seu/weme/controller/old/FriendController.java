package cn.seu.weme.controller.old;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by LCN on 2017-1-3.
 */
@RestController
@RequestMapping(value = "/friends_route")
public class FriendController {

    @RequestMapping(value = "/visit", method = RequestMethod.POST)
    public Map visit() {

        return null;
    }


    @RequestMapping(value = "/visitinfo", method = RequestMethod.POST)
    public Map visitInfo() {

        return null;
    }


    @RequestMapping(value = "/follow", method = RequestMethod.POST)
    public Map follow() {

        return null;
    }


    @RequestMapping(value = "/unfollow", method = RequestMethod.POST)
    public Map unfollow() {

        return null;
    }


    @RequestMapping(value = "/followview", method = RequestMethod.POST)
    public Map followView() {

        return null;
    }


    @RequestMapping(value = "/searchuser", method = {RequestMethod.POST, RequestMethod.GET})
    public Map searchUser() {

        return null;
    }

    @RequestMapping(value = "/getrecommenduser", method = {RequestMethod.POST, RequestMethod.GET})
    public Map getReCommendUser() {
        return null;
    }


    @RequestMapping(value = "/getrecommendusers", method = {RequestMethod.POST, RequestMethod.GET})
    public Map getReCommendUsers() {
        return null;
    }

    @RequestMapping(value = "/likeusercard", method = RequestMethod.POST)
    public Map likeUserCard() {

        return null;
    }


    @RequestMapping(value = "/unlikeusercard", method = RequestMethod.POST)
    public Map unLikeUserCard() {

        return null;
    }


    @RequestMapping(value = "/getlikeusernumber", method = RequestMethod.POST)
    public Map getLikeUserNumber() {

        return null;
    }
}
