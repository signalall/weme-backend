package cn.seu.weme.controller;

import cn.seu.weme.common.result.ResultInfo;
import cn.seu.weme.common.result.ResultUtil;
import cn.seu.weme.common.utils.MessageSourceHelper;
import cn.seu.weme.service.UserService;
import cn.seu.weme.dto.UserVo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

/**
 * Created by LCN on 2016-12-17.
 */
@RestController
@RequestMapping(value = "/users")
public class UserControllerV2 {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private MessageSourceHelper messageSourceHelper;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResultInfo getUserList() {
        return userService.getAllUsers();
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResultInfo postUser(@RequestBody UserVo userVo) {
        return userService.addUser(userVo);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResultInfo getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResultInfo putUser(@PathVariable Long id, @RequestBody UserVo userVo) {
        userVo.setId(id);
        return userService.updateUserV2(userVo);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResultInfo deleteUser(@PathVariable Long id) {
        return userService.deletUserById(id);
    }


    @RequestMapping(value = "/attendActivity", method = RequestMethod.POST)
    public ResultInfo attendActivity(@RequestBody JSONObject jsonObject) {
        Long userId = jsonObject.getLong("userId");
        Long activitiId = jsonObject.getLong("activityId");
        return userService.attendActityV2(userId, activitiId);
    }


    @RequestMapping(value = "/likeActivity", method = RequestMethod.POST)
    public ResultInfo likeActivity(@RequestBody JSONObject jsonObject) {
        Long userId = jsonObject.getLong("userId");
        Long activitiId = jsonObject.getLong("activityId");
        return userService.likeActity(userId, activitiId);
    }


    @RequestMapping(value = "/followUser", method = RequestMethod.POST)
    public ResultInfo followUser(@RequestBody JSONObject jsonObject) {
        Long followerId = jsonObject.getLong("followerId");
        Long followedId = jsonObject.getLong("followedId");
        return userService.followUser(followerId, followedId);
    }

    @RequestMapping(value = "/getFollowerUsers/{userId}", method = RequestMethod.GET)
    public ResultInfo getFollowerUsers(@PathVariable("userId") Long userId) {
        return userService.getFollowUsers(userId);
    }

    @RequestMapping(value = "/getFollowedUsers/{userId}", method = RequestMethod.GET)
    public ResultInfo getFollowedUsers(@PathVariable("userId") Long userId) {
        return userService.getFollowedUsers(userId);
    }

    @RequestMapping(value = "/registerphone", method = RequestMethod.POST)
    public ResultInfo register(@RequestBody JSONObject jsonObject) {
        String phone = jsonObject.getString("phone");
        String password = jsonObject.getString("password");
        String code = jsonObject.getString("code");
        return userService.registerV2(phone, code, password);
    }


    @RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
    public ResultInfo resetpassword(@RequestBody JSONObject jsonObject) {
        String phone = jsonObject.getString("phone");
        String code = jsonObject.getString("code");
        String password = jsonObject.getString("password");
        return userService.resetPasswordV2(password, phone, code);
    }


    @RequestMapping(value = "/resetpassword_v2", method = RequestMethod.POST)
    public ResultInfo resetpasswordV2() {
        // TODO: 2017-1-2
        return null;
    }

    @RequestMapping(value = "/sendsmscode", method = RequestMethod.POST)
    public ResultInfo sendSmsCode(@RequestBody JSONObject jsonObject) {
        String phone = jsonObject.getString("phone");
        int type = jsonObject.getInt("type");
        // TODO: 2017-1-2
        return null;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultInfo login(@RequestBody JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("passoword");

        // TODO: 2017-1-2
        return null;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResultInfo test() {
        Locale locale = LocaleContextHolder.getLocale();

//        Locale locale1= RequestContextUtils.getLocale(request);


        String msg = messageSource.getMessage("100", null, locale);
        return ResultUtil.createSuccess(messageSourceHelper.getMessage("100"));

    }

}
