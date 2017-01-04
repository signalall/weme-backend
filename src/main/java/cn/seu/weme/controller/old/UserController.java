package cn.seu.weme.controller.old;

import cn.seu.weme.common.result.ResponseInfo;
import cn.seu.weme.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LCN on 2017-1-3.
 */
@RestController
@RequestMapping(value = "/check_page")
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/sendsmscode", method = RequestMethod.POST)
    public ResponseInfo sendSmsCode(@RequestBody JSONObject jsonObject) {
        String phone = jsonObject.getString("phone");
        int type = jsonObject.getInt("type");
        return userService.sendSmsCode(phone, type);
    }


    @RequestMapping(value = "/registerphone", method = RequestMethod.POST)
    public ResponseInfo register(@RequestBody JSONObject jsonObject) {
        String phone = jsonObject.getString("phone");
        String password = jsonObject.getString("password");
        String code = jsonObject.getString("code");
        return userService.register(phone, code, password);
    }


    @RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
    public ResponseInfo resetpassword(@RequestBody JSONObject jsonObject) {
        String phone = jsonObject.getString("phone");
        String code = jsonObject.getString("code");
        String password = jsonObject.getString("password");
        return userService.resetPassword(password, phone, code);
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseInfo login(@RequestBody JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        return userService.login(username, password);
    }


}
