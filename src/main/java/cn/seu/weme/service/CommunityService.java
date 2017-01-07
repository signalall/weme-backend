package cn.seu.weme.service;

import cn.seu.weme.common.result.ResponseInfo;
import cn.seu.weme.dto.PostVo;

/**
 * Created by LCN on 2017-1-5.
 */
public interface CommunityService {

    ResponseInfo deletePost(String token, Long postId);

    ResponseInfo publishPost(String token, PostVo postVo);

    ResponseInfo commentToPost(String token, Long postId, String body);

    ResponseInfo commentToComment(String token, Long commentId, String body);

    ResponseInfo likePost(String token, Long postId);

    ResponseInfo likeComment(String token, Long commentId);

    ResponseInfo getTopicSlogan(String token, Long topicId);

    ResponseInfo getTopicPostList(String token, Long topicId, int page);

    ResponseInfo getPostDetail(String token, Long postId);

    ResponseInfo getUserTimeline(String token,Long userId,int page);

    ResponseInfo getUserImages(String token,Long userId,int page);

    ResponseInfo getPostComments(String token,Long postId,int page);

    ResponseInfo getPostLikeUsers(String token,Long postId,int page);

    //根据commentid得到这个comment的所有评论
    ResponseInfo getCommentByCommentId(String token,Long commentId,int page);



}
