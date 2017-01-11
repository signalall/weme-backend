package cn.seu.weme.controller.old.admin;

import cn.seu.weme.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Visen (zhvisen@gmail.com) on 2017/1/6.
 */
@RestController
@RequestMapping(value = "/share")
public class ShareController  {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/{postid}",method = RequestMethod.POST)
    public Map share(@PathVariable Long postid)
    {
       return  postService.share(postid);
    }


}
