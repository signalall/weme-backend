package cn.seu.weme.controller;

import cn.seu.weme.common.result.ResultInfo;
import cn.seu.weme.dto.CommentVo;
import cn.seu.weme.service.CommentService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by LCN on 2016-12-18.
 */
@RestController
@RequestMapping(value = "/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @RequestMapping(value = "/addCommentToPost/{postId}", method = RequestMethod.POST)
    public ResultInfo addCommentToPost(@PathVariable("postId") Long postId, @RequestBody CommentVo commentVo) {
        return commentService.addCommentToPost(postId, commentVo);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResultInfo deleteUser(@PathVariable Long id) {
        return commentService.deleteOneById(id);
    }


    @RequestMapping(value = "/userLikeComment", method = RequestMethod.POST)
    public ResultInfo userLikeComment(@RequestBody JSONObject jsonObject) {
        Long userId = jsonObject.getLong("userId");
        Long commentId = jsonObject.getLong("commentId");
        return commentService.userLikeComment(userId, commentId);
    }



    @RequestMapping(value = "/getCommentLikeUsers/{commentId}", method = RequestMethod.GET)
    public ResultInfo getCommentLikeUsers(@PathVariable("commentId") Long id) {
        return commentService.getlikeUsers(id);
    }
}
