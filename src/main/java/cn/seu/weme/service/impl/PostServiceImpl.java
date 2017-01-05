package cn.seu.weme.service.impl;

import cn.seu.weme.common.result.ResultInfo;
import cn.seu.weme.common.result.ResultUtil;
import cn.seu.weme.common.utils.MyBeanUtils;
import cn.seu.weme.dao.PostDao;
import cn.seu.weme.dto.PostVo;
import cn.seu.weme.entity.Post;
import cn.seu.weme.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by LCN on 2016-12-18.
 */
@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Autowired
    private ModelMapper mapper;

    @Override
    public ResultInfo addPost(PostVo postVo) {
        Post post = mapper.map(postVo,Post.class);
        postDao.save(post);
        return ResultUtil.createSuccess("添加文章成功");
    }

    @Override
    public ResultInfo getPostById(Long id) {
        Post post = postDao.findOne(id);
        PostVo postVo = mapper.map(post,PostVo.class);
        return ResultUtil.createSuccess("文章信息",postVo);
    }

    @Override
    public ResultInfo updatePost(PostVo postVo) {
        Post post = postDao.findOne(postVo.getId());
        MyBeanUtils.copyProperties(postVo,post);

        postDao.save(post);
        return ResultUtil.createSuccess("更新文章成功");
    }

    @Override
    public ResultInfo deletePostById(Long id) {
        postDao.delete(id);
        return ResultUtil.createSuccess("删除文章成功");
    }
}
