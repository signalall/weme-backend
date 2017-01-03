package cn.seu.weme.service.impl;

import cn.seu.weme.common.result.ResponseInfo;
import cn.seu.weme.common.result.ResultInfo;
import cn.seu.weme.common.result.ResultUtil;
import cn.seu.weme.common.utils.*;
import cn.seu.weme.dao.ActivityDao;
import cn.seu.weme.dao.CheckMsgDao;
import cn.seu.weme.dao.PersonImageDao;
import cn.seu.weme.dao.UserDao;
import cn.seu.weme.dto.*;
import cn.seu.weme.entity.*;
import cn.seu.weme.service.UserService;
import com.google.common.base.Strings;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.modelmapper.ModelMapper;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

/**
 * Created by LCN on 2016-12-17.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private PersonImageDao personImageDao;

    @Autowired
    private CheckMsgDao checkMsgDao;

    @Autowired
    private AvatarVoiceDao avatarVoiceDao;

    @Autowired
    private ModelMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MessageSourceHelper messageSourceHelper;

    @Override
    public ResultInfo attendActity(Long userId, Long activityId) {
        User user = userDao.findOne(userId);
        Activity activity = activityDao.findOne(activityId);
        activity.getAttendUsers().add(user);
        activityDao.save(activity);
        return ResultUtil.createSuccess("参加活动成功");
    }


    @Override
    public ResultInfo attendActivity2(Long userId, Long activityId) {

        userDao.attendActivity(userId, activityId);
        return ResultUtil.createSuccess("参加活动成功");
    }

    @Override
    public ResultInfo likeActity(Long userId, Long activityId) {
        User user = userDao.findOne(userId);
        Activity activity = activityDao.findOne(activityId);
        activity.getLikeUsers().add(user);
        activityDao.save(activity);
        return ResultUtil.createSuccess("喜欢活动成功");
    }

    @Override
    public ResultInfo followUser(Long followerId, Long followedId) {
        javax.persistence.Query query = entityManager.createNativeQuery("insert into t_follower_followed(follower_id,followed_id) VALUES(?1,?2)");
        query.setParameter(1, followerId);
        query.setParameter(2, followedId);
        query.executeUpdate();
        return ResultUtil.createSuccess("关注成功");
    }

    @Override
    public ResultInfo getFollowUsers(Long userId) {
        Set<User> users = userDao.findOne(userId).getFolloweds();
        Set<UserVo> userVos = new HashSet<>();
        users.forEach(args -> userVos.add(mapper.map(args, UserVo.class)));
        return ResultUtil.createSuccess("关注自己的人", userVos);
    }

    @Override
    public ResultInfo getFollowedUsers(Long userId) {
        Set<User> users = userDao.findOne(userId).getFollowers();
        Set<UserVo> userVos = new HashSet<>();
        users.forEach(args -> userVos.add(mapper.map(args, UserVo.class)));
        return ResultUtil.createSuccess("自己的关注者", userVos);
    }

    @Override
    public ResultInfo uploadImage(Long userId, PersonImageVo personImageVo) {
        User user = userDao.findOne(userId);
        PersonalImage personalImage = mapper.map(personImageVo, PersonalImage.class);
        personalImage.setUser(user);

        personImageDao.save(personalImage);
        return ResultUtil.createSuccess("保存图片成功");
    }


    @Override
    public ResultInfo registerV2(String phone, String code, String password) {

        if (Strings.isNullOrEmpty(phone) || Strings.isNullOrEmpty(code) || Strings.isNullOrEmpty(password)) {
            return ResultUtil.createFail(messageSourceHelper.getMessage("100"));
        }

        User user = userDao.findByPhone(phone);
        if (user != null) {
            return ResultUtil.createFail(messageSourceHelper.getMessage("101"));
        }

        CheckMsg checkMsg = checkMsgDao.findByPhone(phone);
        if (checkMsg == null || !checkMsg.getCode().equals(code)) {
            return ResultUtil.createFail(messageSourceHelper.getMessage("102"));
        }

        if (Minutes.minutesBetween(new DateTime(), new DateTime(checkMsg.getTimeStamp())).getMinutes() > 5) {
            return ResultUtil.createFail("验证码超时!");
        }


        String token = TokenProcessor.generateToken(phone + code + password);
        String salt = CryptoUtils.getSalt();
        String hashedPassword = CryptoUtils.getHash(password, salt);
        User newUser = new User(phone, hashedPassword, salt, token);
        userDao.save(newUser);

        Map data = new HashMap<>();
        data.put("token", token);

        return ResultUtil.createSuccess(messageSourceHelper.getMessage("103"), data);
    }

    @Override
    public ResponseInfo register(String phone, String code, String password) {
        ResponseInfo responseInfo = new ResponseInfo();
        if (Strings.isNullOrEmpty(phone) || Strings.isNullOrEmpty(code) || Strings.isNullOrEmpty(password)) {
            responseInfo.setStatus("fail");
            responseInfo.setReason("参数错误");
            return responseInfo;
        }

        if (userDao.findByPhone(phone) != null) {
            responseInfo.setReason("该手机号已经注册");
            responseInfo.setStatus("fail");
            return responseInfo;
        }

        if (!checkMsgCode(phone, code)) {
            responseInfo.setStatus("fail");
            responseInfo.setReason("验证码无效");
            return responseInfo;
        }

        String token = TokenProcessor.generateToken(phone + code + password);
        String salt = CryptoUtils.getSalt();
        String hashedPassword = CryptoUtils.getHash(password, salt);
        User user = new User(phone, hashedPassword, salt, token);
        user.setUsername(phone);
        userDao.save(user);
        responseInfo.setStatus("successful");
        responseInfo.setReason("");
        responseInfo.setToken(user.getToken());
        responseInfo.setId(user.getId());
        return responseInfo;
    }

    @Override
    public ResultInfo resetPasswordV2(String newPassword, String phone, String code) {
        User user = userDao.findByPhone(phone);
        if (user == null) {
            return ResultUtil.createFail("该用户还未注册!");
        }
        CheckMsg checkMsg = checkMsgDao.findByPhone(phone);
        if (checkMsg == null || !checkMsg.getCode().equals(code)) {
            return ResultUtil.createFail("验证码错误!");
        }

        if (Minutes.minutesBetween(new DateTime(), new DateTime(checkMsg.getTimeStamp())).getMinutes() > 5) {
            return ResultUtil.createFail("验证码超时!");
        }

        String token = TokenProcessor.generateToken(phone + code + newPassword);

        String salt = CryptoUtils.getSalt();
        String hashedPassword = CryptoUtils.getHash(newPassword, salt);
        user.setToken(token);
        user.setPassword(hashedPassword);
        user.setSalt(salt);
        userDao.save(user);
        return ResultUtil.createFail("重置密码成功!");
    }

    @Override
    public ResponseInfo resetPassword(String newPassword, String phone, String code) {
        ResponseInfo responseInfo = new ResponseInfo();
        User user = userDao.findByPhone(phone);
        if (user == null) {
            responseInfo.setStatus("fail");
            responseInfo.setReason("该手机号尚未被注册");
            return responseInfo;
        }

        if (!checkMsgCode(phone, code)) {
            responseInfo.setStatus("fail");
            responseInfo.setReason("验证码无效");
            return responseInfo;
        }
        responseInfo.setStatus("successful");
        responseInfo.setReason("");
        responseInfo.setToken(user.getToken());
        responseInfo.setId(user.getId());
        return responseInfo;
    }

    @Override
    public ResultInfo loginV2(String username, String password) {
        //用户名保证唯一
        User user = userDao.findByUsername(username);
        if (user == null) {
            return ResultUtil.createFail("用户名密码错误");
        }

        if (!CryptoUtils.verify(user.getPassword(), password, user.getSalt())) {
            return ResultUtil.createFail("用户名密码错误");
        }

        String token = user.getToken();

        return ResultUtil.createSuccess("用户登录成功!", token);
    }

    @Override
    public ResponseInfo login(String username, String password) {
        ResponseInfo responseInfo = new ResponseInfo();
        User user = userDao.findByUsername(username);
        if (user == null) {
            responseInfo.setStatus("fail");
            responseInfo.setReason("用户名密码错误");
            return responseInfo;
        }
        if (!CryptoUtils.verify(user.getPassword(), password, user.getSalt())) {
            responseInfo.setStatus("fail");
            responseInfo.setReason("用户名密码错误");
            return responseInfo;
        }


        responseInfo.setStatus("successful");
        responseInfo.setReason("");
        responseInfo.setId(user.getId());
        responseInfo.setToken(user.getToken());
        responseInfo.setGender(user.getGender());
        return responseInfo;
    }

    @Override
    public ResponseInfo sendSmsCode(String phone, int type) {
        ResponseInfo responseInfo = new ResponseInfo();
        boolean success = false;

        if (userDao.findByPhone(phone) == null) {
            responseInfo.setStatus("fail");
            responseInfo.setReason("invalid");
            return responseInfo;
        }

        String code = RandUtils.getRandomString(6);
        success = SmsUtils.sendSmsCodeByType(phone, code, type);

        if (!success) {
            responseInfo.setStatus("fail");
            responseInfo.setReason("验证码发送失败");
            return responseInfo;
        }

        CheckMsg checkMsg = checkMsgDao.findByPhone(phone);
        if (checkMsg == null) {
            checkMsg = new CheckMsg(phone, code);
        } else {
            checkMsg.setCode(code);
            checkMsg.setTimeStamp(new Date());
        }
        checkMsgDao.save(checkMsg);
        responseInfo.setStatus("successful");
        responseInfo.setReason("");
        return responseInfo;
    }


    @Override
    public ResultInfo addUser(UserVo userVo) {
        User user = mapper.map(userVo, User.class);
        userDao.save(user);
        return ResultUtil.createSuccess("创建用户成功");
    }

    @Override
    public ResultInfo getUserById(Long id) {
        User user = userDao.findOne(id);
        if (user == null) {
            return ResultUtil.createFail("没有此用户");
        }
        UserVo userVo = mapper.map(user, UserVo.class);
        return ResultUtil.createSuccess("用户信息", userVo);
    }

    @Override
    public ResultInfo updateUserV2(UserVo userVo) {
        User user = userDao.findOne(userVo.getId());
        if (user == null) {
            return ResultUtil.createFail("没有此用户");
        }
        BeanUtils.copyProperties(userVo, user);
        userDao.save(user);
        return ResultUtil.createSuccess("更新用户成功");
    }


    @Override
    public ResponseInfo updateUser(UserVo userVo) {
        ResponseInfo responseInfo = new ResponseInfo();
        User user = userDao.findByToken(userVo.getToken());
        if (user == null) {
            responseInfo.setStatus("fail");
            responseInfo.setReason("invalid access");
            return responseInfo;
        }

        if (user.getCertification()) {
            if (user.getSchool() != userVo.getSchool() ||
                    user.getDepartment() != userVo.getDepartment() ||
                    user.getDegree() != userVo.getDegree()) {
                responseInfo.setStatus("fail");
                responseInfo.setReason("已认证用户不能修改学校信息");
                return responseInfo;
            }
        }
        BeanUtils.copyProperties(userVo, user);
        userDao.save(user);
        responseInfo.setStatus("successful");
        responseInfo.setReason("");
        return responseInfo;
    }

    @Override
    public ResponseInfo editSchoolInfo(SchoolInfoVo schoolInfoVo) {
        User user = userDao.findByToken(schoolInfoVo.getToken());
        BeanUtils.copyProperties(schoolInfoVo, user);
        userDao.save(user);

        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setStatus("successful");
        responseInfo.setReason("");
        return responseInfo;
    }

    @Override
    public ResponseInfo editPersonInfo(UserInfoVo userInfoVo) {
        User user = userDao.findByToken(userInfoVo.getToken());
        BeanUtils.copyProperties(userInfoVo, user);
        userDao.save(user);

        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setStatus("successful");
        responseInfo.setReason("");
        return responseInfo;
    }

    @Override
    public ResponseInfo editPreferenceInfo(String token, String hobby, String preference) {
        User user = userDao.findByToken(token);
        user.setHobby(hobby);
        user.setPreference(preference);
        userDao.save(user);

        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setStatus("successful");
        responseInfo.setReason("");
        return responseInfo;
    }

    @Override
    public ResponseInfo editCardSetting(String token, String cardflag) {
        ResponseInfo responseInfo = new ResponseInfo();
        User user = userDao.findByToken(token);
        AvatarVoice avatarVoice = user.getAvatarVoice();

        if (avatarVoice == null) {
            responseInfo.setStatus("fail");
            responseInfo.setReason("error");
            return responseInfo;
        }

        switch (cardflag) {
            case "0":
                avatarVoice.setCardFlag(false);
                avatarVoiceDao.save(avatarVoice);
                break;
            case "1":
                avatarVoice.setCardFlag(true);
                avatarVoiceDao.save(avatarVoice);
                break;
            default:
                responseInfo.setStatus("fail");
                responseInfo.setReason("wrong cardflag");
                return responseInfo;
        }

        responseInfo.setStatus("successful");
        responseInfo.setReason("");
        return responseInfo;
    }

    @Override
    public ResultInfo deletUserById(Long id) {
        User user = userDao.findOne(id);
        if (user == null) {
            return ResultUtil.createFail("没有此用户");
        }
        userDao.delete(id);
        return ResultUtil.createSuccess("删除用户成功");
    }

    @Override
    public ResultInfo getAllUsers() {
        List<User> users = (List<User>) userDao.findAll();
        List<UserVo> userVos = new ArrayList<>();
        for (User user : users) {
            userVos.add(mapper.map(user, UserVo.class));
        }
        return ResultUtil.createSuccess("所有用户", userVos);
    }


    private boolean checkMsgCode(String phone, String code) {
        CheckMsg checkMsg = checkMsgDao.findByPhone(phone);
        if (checkMsg == null || !checkMsg.getCode().equals(code)) {
            return false;
        }

        if (Minutes.minutesBetween(new DateTime(checkMsg.getTimeStamp()), new DateTime()).getMinutes() > 5) {
            return false;
        }
        return true;
    }
}
