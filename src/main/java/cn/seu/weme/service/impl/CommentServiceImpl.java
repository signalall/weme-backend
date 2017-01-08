package cn.seu.weme.service.impl;

import cn.seu.weme.common.result.ResultInfo;
import cn.seu.weme.common.result.ResultUtil;
import cn.seu.weme.common.utils.MyBeanUtils;
import cn.seu.weme.dao.CommentDao;
import cn.seu.weme.dao.PostDao;
import cn.seu.weme.dao.UserDao;
import cn.seu.weme.dto.CommentVo;
import cn.seu.weme.dto.UserVo;
import cn.seu.weme.entity.Comment;
import cn.seu.weme.entity.Post;
import cn.seu.weme.entity.User;
import cn.seu.weme.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.SecondaryTable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by LCN on 2016-12-18.
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private PostDao postDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ModelMapper modelMapper;


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ResultInfo addOne(CommentVo data) {
        Comment comment = modelMapper.map(data, Comment.class);
        commentDao.save(comment);
        return ResultUtil.createSuccess("添加成功");
    }

    @Override
    public ResultInfo getOneById(Long id) {
        Comment comment = commentDao.findOne(id);
        CommentVo commentVo = modelMapper.map(comment, CommentVo.class);
        return ResultUtil.createSuccess("评论信息", commentVo);
    }

    @Override
    public ResultInfo updateOne(CommentVo data) {
        Comment comment = commentDao.findOne(data.getId());
        MyBeanUtils.copyProperties(data, comment);
        commentDao.save(comment);
        return ResultUtil.createSuccess("更新评论成功");
    }

    @Override
    public ResultInfo deleteOneById(Long id) {
        commentDao.delete(id);
        return ResultUtil.createSuccess("评论删除成功");
    }

    @Override
    public ResultInfo getAllData() {
        List<Comment> comments = (List<Comment>) commentDao.findAll();
        List<CommentVo> commentVos = new ArrayList<>();

        comments.forEach(comment -> commentVos.add(modelMapper.map(comment, CommentVo.class)));

        return ResultUtil.createSuccess("所有评论", commentVos);
    }

    @Override
    public ResultInfo addCommentToPost(Long postId, CommentVo commentVo) {
        Post post = postDao.findOne(postId);
        Comment comment = modelMapper.map(commentVo, Comment.class);
        comment.setPost(post);
        commentDao.save(comment);
        return ResultUtil.createSuccess("添加评论成功");
    }

    @Override
    public ResultInfo userLikeComment(Long userId, Long commentId) {
        Query query = entityManager.createNativeQuery("insert into like_user_comment (user_id,comment_id) VALUES (?1,?2)");
        query.setParameter(1, userId);
        query.setParameter(2, commentId);
        query.executeUpdate();
        return ResultUtil.createSuccess("评论点赞成功");
    }

    @Override
    public ResultInfo getlikeUsers(Long commentId) {
//        Set<User> users = commentDao.findOne(commentId).getLikeUser();
//
//        Set<UserVo> userVos = new HashSet<>();
//        users.forEach(args-> userVos.add(modelMapper.map(args,UserVo.class)));
//        return ResultUtil.createSuccess("点赞用户",userVos);
        return null;
    }
}
