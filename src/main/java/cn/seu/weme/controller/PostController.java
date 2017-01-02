package cn.seu.weme.controller;

import cn.seu.weme.common.result.ResultInfo;
import cn.seu.weme.dto.PostVo;
import cn.seu.weme.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by LCN on 2016-12-18.
 */

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    public ResultInfo getPostList() {
        return null;
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResultInfo postPost(@RequestBody PostVo postVo) {
        return postService.addPost(postVo);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResultInfo getPost(@PathVariable Long id) {
        return postService.getPostById(id);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResultInfo putPost(@PathVariable Long id, @RequestBody PostVo postVo) {
        postVo.setId(id);
        return postService.updatePost(postVo);
    }

}
