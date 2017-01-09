package cn.seu.weme.service;

import cn.seu.weme.common.result.ResultInfo;
import cn.seu.weme.dto.PostVo;

import java.util.Map;

/**
 * Created by LCN on 2016-12-18.
 */
public interface PostService {
    public ResultInfo addPost(PostVo postVo);

    public ResultInfo getPostById(Long id);

    public ResultInfo updatePost(PostVo postVo);

    public ResultInfo deletePostById(Long id);

    public Map share(Long id);

}
