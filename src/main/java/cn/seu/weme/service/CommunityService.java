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

    ResponseInfo commentToComment(String token, Long CommentId, String body);

    ResponseInfo likePost(String token, Long postId);

    ResponseInfo likeComment(String token, Long commentId);



}
