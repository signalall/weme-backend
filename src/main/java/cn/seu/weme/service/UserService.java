package cn.seu.weme.service;

import cn.seu.weme.common.result.ResponseInfo;
import cn.seu.weme.common.result.ResultInfo;
import cn.seu.weme.dto.PersonImageVo;
import cn.seu.weme.dto.UserVo;

/**
 * Created by LCN on 2016-12-17.
 */
public interface UserService {

    public ResultInfo addUser(UserVo userVo);

    public ResultInfo getUserById(Long id);

    public ResultInfo updateUser(UserVo userVo);

    public ResultInfo deletUserById(Long id);

    public ResultInfo getAllUsers();

    public ResultInfo attendActity(Long userId, Long activityId);

    public ResultInfo attendActivity2(Long userId, Long activityId);

    public ResultInfo likeActity(Long userId, Long activityId);

    public ResultInfo followUser(Long followerId, Long followedId);

    public ResultInfo getFollowUsers(Long userId);

    public ResultInfo getFollowedUsers(Long userId);

    public ResultInfo uploadImage(Long userId, PersonImageVo personImageVo);

    //注册用户
    public ResultInfo registerV2(String phone, String code, String password);

    public ResponseInfo register(String phone, String code, String password);

    public ResultInfo resetPasswordV2(String newPassword, String phone, String code);

    public ResponseInfo resetPassword(String newPassword, String phone, String code);

    public ResultInfo loginV2(String username, String password);

    public ResponseInfo login(String username, String password);

    public ResponseInfo sendSmsCode(String phone, int type);


}
