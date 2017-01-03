package cn.seu.weme.controller.old;

import cn.seu.weme.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by LCN on 2017-1-3.
 */
@RestController
@RequestMapping(value = "/getprofile_route")
public class UserInfoController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/gettagsbyid", method = RequestMethod.POST)
    public Map getTags(@RequestBody JSONObject jsonObject) {
        return null;
    }
}
