package cn.seu.weme.controller.old;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by LCN on 2017-1-3.
 */
@RestController
@RequestMapping(value = "/community_route")
public class CommunityController {

    @RequestMapping(value = "/deletepost", method = RequestMethod.POST)
    public Map deleteUser() {
        return null;
    }

    @RequestMapping(value = "/publishpost", method = RequestMethod.POST)
    public Map publishPost() {
        return null;
    }


    @RequestMapping(value = "/commenttopost", method = RequestMethod.POST)
    public Map commentToPost() {
        return null;
    }


    @RequestMapping(value = "/commenttocomment", method = RequestMethod.POST)
    public Map commentToComment() {
        return null;
    }

    @RequestMapping(value = "/likepost", method = RequestMethod.POST)
    public Map likePost() {
        return null;
    }


    @RequestMapping(value = "/likecomment", method = RequestMethod.POST)
    public Map likeComment() {
        return null;
    }


    @RequestMapping(value = "/topofficial", method = RequestMethod.POST)
    public Map topOfficial() {
        return null;
    }

    @RequestMapping(value = "/gettopiclist", method = RequestMethod.POST)
    public Map getTopicList() {
        return null;
    }


    @RequestMapping(value = "/gettopicslogan", method = RequestMethod.POST)
    public Map getTopicSlogan() {
        return null;
    }

    @RequestMapping(value = "/getpostlist", method = RequestMethod.POST)
    public Map getPostList() {
        return null;
    }

    @RequestMapping(value = "/getpostdetail", method = RequestMethod.POST)
    public Map getPostDetail() {
        return null;
    }


    @RequestMapping(value = "/getusertimeline", method = RequestMethod.POST)
    public Map getUserTimeLine() {
        return null;
    }

    @RequestMapping(value = "/getuserimages", method = RequestMethod.POST)
    public Map getUserImages() {
        return null;
    }


    @RequestMapping(value = "/getpostcomment", method = RequestMethod.POST)
    public Map getPostComments() {
        return null;
    }

    @RequestMapping(value = "/getpostlikeusers", method = RequestMethod.POST)
    public Map getPostLikeUsers() {
        return null;
    }


    @RequestMapping(value = "/getcommentbycommentid", method = RequestMethod.POST)
    public Map getCommmentByCommentId() {
        return null;
    }


}
