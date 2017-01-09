package cn.seu.weme.service.impl;

import cn.seu.weme.common.result.ResultInfo;
import cn.seu.weme.common.result.ResultUtil;
import cn.seu.weme.common.utils.MyBeanUtils;
import cn.seu.weme.dao.PostDao;
import cn.seu.weme.dto.PostVo;
import cn.seu.weme.entity.Post;
import cn.seu.weme.entity.PostImage;
import cn.seu.weme.entity.User;
import cn.seu.weme.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    ///// 生成网页模板  后面需要改进
    /////
    @Override
    public Map share(Long id)
    {
        Post post =postDao.findOne(id);
        User user = post.getPublishUser();
        String avatar = "http://218.244.147.240:80/avatar/"+user.getId().toString();
        String name =user.getName();
        String school = user.getSchool();
        String department = user.getDepartment();
        String title = post.getTitle();
        String body = post.getBody();
        String gender = user.getGender();
        String topicid = post.getTopic().getId().toString();
        List<PostImage>postImage = post.getPostImages();
        List<String> imageList =new ArrayList<>();
        List<String> thumbnailList = new ArrayList<>();
        for (PostImage tempPostImage:postImage)
        {
            Long postimageid = tempPostImage.getId();
            String url ="http://218.244.147.240:80/community/postattachs/"+topicid+"-"+id.toString()+"-"+postimageid;
            String urlthum = "http://218.244.147.240:80/community/postattachs/"+topicid+"-"+id.toString()+"-"+postimageid+"_thumbnail.jpg";
            imageList.add(url);
            thumbnailList.add(urlthum);
        }

        return null;

    }


}
