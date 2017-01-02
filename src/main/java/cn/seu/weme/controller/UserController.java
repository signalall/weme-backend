package cn.seu.weme.controller;

import cn.seu.weme.common.result.ResultInfo;
import cn.seu.weme.service.UserService;
import cn.seu.weme.dto.UserVo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by LCN on 2016-12-17.
 */
@RestController
@RequestMapping(value="/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public ResultInfo getUserList() {
        return userService.getAllUsers();
    }


    @RequestMapping(value="/", method=RequestMethod.POST)
    public ResultInfo postUser(@RequestBody UserVo userVo) {
        return userService.addUser(userVo);
    }



    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResultInfo getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }


    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResultInfo putUser(@PathVariable Long id, @RequestBody UserVo userVo) {
        userVo.setId(id);
        return userService.updateUser(userVo);
    }


    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResultInfo deleteUser(@PathVariable Long id) {
        return userService.deletUserById(id);
    }


    @RequestMapping(value="/attendActivity", method=RequestMethod.POST)
    public ResultInfo attendActivity(@RequestBody JSONObject jsonObject){
        Long userId = jsonObject.getLong("userId");
        Long activitiId = jsonObject.getLong("activityId");
        return userService.attendActity(userId,activitiId);
    }


    @RequestMapping(value="/likeActivity", method=RequestMethod.POST)
    public ResultInfo likeActivity(@RequestBody JSONObject jsonObject){
        Long userId = jsonObject.getLong("userId");
        Long activitiId = jsonObject.getLong("activityId");
        return userService.likeActity(userId,activitiId);
    }


    @RequestMapping(value="/followUser", method=RequestMethod.POST)
    public ResultInfo followUser(@RequestBody JSONObject jsonObject){
        Long followerId = jsonObject.getLong("followerId");
        Long followedId = jsonObject.getLong("followedId");
        return userService.followUser(followerId,followedId);
    }

    @RequestMapping(value="/getFollowerUsers/{userId}", method=RequestMethod.GET)
    public ResultInfo getFollowerUsers(@PathVariable("userId") Long userId){
        return userService.getFollowUsers(userId);
    }

    @RequestMapping(value="/getFollowedUsers/{userId}", method=RequestMethod.GET)
    public ResultInfo getFollowedUsers(@PathVariable("userId") Long userId){
        return userService.getFollowedUsers(userId);
    }
}
