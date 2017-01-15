package cn.seu.weme.controller.old;

import cn.seu.weme.common.result.ResponseInfo;
import cn.seu.weme.dto.PostVo;
import cn.seu.weme.service.CheckUserService;
import cn.seu.weme.service.CommunityService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by LCN on 2017-1-3.
 */
@RestController
//@RequestMapping(value = "/community_route")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @Autowired
    private CheckUserService checkUserService;


    @RequestMapping(value = "/deletepost", method = RequestMethod.POST)
    public ResponseInfo deleteUser(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return checkUserService.failResponse();
        }

        Long postId = jsonObject.getLong("postid");

        return communityService.deletePost(token, postId);
    }

    @RequestMapping(value = "/publishpost", method = RequestMethod.POST)
    public ResponseInfo publishPost(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return checkUserService.failResponse();
        }
        String title = jsonObject.getString("title");
        String body = jsonObject.getString("body");
        Long topicId = jsonObject.getLong("topicid");

        PostVo postVo = new PostVo();
        postVo.setTitle(title);
        postVo.setBody(body);
        postVo.setTopicId(topicId);
        return communityService.publishPost(token, postVo);
    }


    @RequestMapping(value = "/commenttopost", method = RequestMethod.POST)
    public ResponseInfo commentToPost(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return checkUserService.failResponse();
        }
        Long postId = jsonObject.getLong("postid");
        String body = jsonObject.getString("body");
        return communityService.commentToPost(token, postId, body);
    }


    @RequestMapping(value = "/commenttocomment", method = RequestMethod.POST)
    public ResponseInfo commentToComment(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return checkUserService.failResponse();
        }
        Long commentId = jsonObject.getLong("destcommentid");
        String body = jsonObject.getString("body");


        return communityService.commentToComment(token, commentId, body);
    }

    @RequestMapping(value = "/likepost", method = RequestMethod.POST)
    public ResponseInfo likePost(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return checkUserService.failResponse();
        }
        Long postId = jsonObject.getLong("postid");
        return communityService.likePost(token, postId);
    }


    @RequestMapping(value = "/likecomment", method = RequestMethod.POST)
    public ResponseInfo likeComment(@RequestBody JSONObject jsonObject) {

        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return checkUserService.failResponse();
        }
        Long commentId = jsonObject.getLong("commentid");

        return communityService.likeComment(token, commentId);
    }


    //置顶图片连接
    @RequestMapping(value = "/topofficial", method = RequestMethod.POST)
    public ResponseInfo topOfficial(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return checkUserService.failResponse();
        }
        return null;
    }

    @RequestMapping(value = "/gettopiclist", method = RequestMethod.POST)
    public ResponseInfo getTopicList(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return checkUserService.failResponse();
        }
        return null;
    }


    @RequestMapping(value = "/gettopicslogan", method = RequestMethod.POST)
    public ResponseInfo getTopicSlogan(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return checkUserService.failResponse();
        }
        Long topicId = jsonObject.getLong("topicid");
        return communityService.getTopicSlogan(token, topicId);
    }

    @RequestMapping(value = "/getpostlist", method = RequestMethod.POST)
    public ResponseInfo getPostList(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return checkUserService.failResponse();
        }
        int page = jsonObject.getInt("page");
        Long topicId = jsonObject.getLong("topicId");
        return communityService.getTopicPostList(token, topicId, page);
    }

    @RequestMapping(value = "/getpostdetail", method = RequestMethod.POST)
    public ResponseInfo getPostDetail(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return checkUserService.failResponse();
        }

        Long postId = jsonObject.getLong("postid");
        return communityService.getPostDetail(token, postId);
    }


    @RequestMapping(value = "/getusertimeline", method = RequestMethod.POST)
    public ResponseInfo getUserTimeLine(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return checkUserService.failResponse();
        }
        int page = jsonObject.getInt("page");
        Long userId = jsonObject.getLong("userid");
        return communityService.getUserTimeline(token, userId, page);
    }

    @RequestMapping(value = "/getuserimages", method = RequestMethod.POST)
    public ResponseInfo getUserImages(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return checkUserService.failResponse();
        }
        int page = jsonObject.getInt("page");
        Long userId = jsonObject.getLong("userid");

        return communityService.getUserImages(token, userId, page);
    }


    @RequestMapping(value = "/getpostcomment", method = RequestMethod.POST)
    public ResponseInfo getPostComments(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return checkUserService.failResponse();
        }
        int page = jsonObject.getInt("page");
        Long postId = jsonObject.getLong("postid");
        return communityService.getPostComments(token, postId, page);
    }

    @RequestMapping(value = "/getpostlikeusers", method = RequestMethod.POST)
    public ResponseInfo getPostLikeUsers(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return checkUserService.failResponse();
        }
        int page = jsonObject.getInt("page");
        Long postId = jsonObject.getLong("postid");

        return communityService.getPostLikeUsers(token, postId, page);
    }


    @RequestMapping(value = "/getcommentbycommentid", method = RequestMethod.POST)
    public ResponseInfo getCommmentByCommentId(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return checkUserService.failResponse();
        }
        Long commentId = jsonObject.getLong("commentid");
        return communityService.getCommentByCommentId(token, commentId, 0);
    }


}
