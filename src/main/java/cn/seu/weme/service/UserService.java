package cn.seu.weme.service;

import cn.seu.weme.common.result.ResponseInfo;
import cn.seu.weme.common.result.ResultInfo;
import cn.seu.weme.dto.PersonImageVo;
import cn.seu.weme.dto.SchoolInfoVo;
import cn.seu.weme.dto.UserInfoVo;
import cn.seu.weme.dto.UserVo;
import cn.seu.weme.dto.old.ActivityVo;

import java.util.Map;

/**
 * Created by LCN on 2016-12-17.
 */
public interface UserService {

    public ResultInfo addUser(UserVo userVo);

    public ResultInfo getUserById(Long id);

    public ResultInfo updateUserV2(UserVo userVo);

    public ResultInfo deletUserById(Long id);

    public ResultInfo getAllUsers();

    public ResultInfo attendActityV2(Long userId, Long activityId);

    public ResponseInfo attendActivity(String token, Long activityId);

    public ResponseInfo unAttendActivity(String token, Long activityId);

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

    public ResponseInfo updateUser(UserVo userVo);

    public ResponseInfo editSchoolInfo(SchoolInfoVo schoolInfoVo);

    public ResponseInfo editPersonInfo(UserInfoVo userInfoVo);

    public ResponseInfo editPreferenceInfo(String token, String hobby, String preference);

    public ResponseInfo editCardSetting(String token, String cardflag);

    public Map publishActivity(ActivityVo activityVo);

    public Map likeActivity(String token, Long activityId);

    public Map unLikeActivity(String token, Long activityId);

    public Map getTag(Long userId);

    public Map setTag(String token, String tag);

    public Map getPersonImages(String token, Long imageId);

    public Map getProfile(String token);

    public Map getProfileById(String token, Long userId);

    public Map getProfileByIdPhone(String token, Long userId);

    //关注某人
    public ResponseInfo followUser(String token, Long followedUserId);
}
