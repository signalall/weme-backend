package cn.seu.weme.service;

import cn.seu.weme.common.result.ResultInfo;
import cn.seu.weme.dto.CommentVo;

/**
 * Created by LCN on 2016-12-18.
 */
public interface CommentService extends CrudService<CommentVo> {

    public ResultInfo addCommentToPost(Long postId, CommentVo commentVo);

    public ResultInfo userLikeComment(Long userId, Long commentId);

    public ResultInfo getlikeUsers(Long commentId);
}
