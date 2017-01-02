package cn.seu.weme.service.impl;

import cn.seu.weme.common.result.ResultInfo;
import cn.seu.weme.common.result.ResultUtil;
import cn.seu.weme.dao.ActivityDao;
import cn.seu.weme.dao.PersonImageDao;
import cn.seu.weme.dao.UserDao;
import cn.seu.weme.dto.PersonImageVo;
import cn.seu.weme.dto.UserVo;
import cn.seu.weme.entity.Activity;
import cn.seu.weme.entity.PersonalImage;
import cn.seu.weme.entity.User;
import cn.seu.weme.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private ModelMapper mapper;


    @PersistenceContext
    private EntityManager entityManager;

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

        userDao.attenActivity(userId, activityId);
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
        PersonalImage personalImage = mapper.map(personImageVo,PersonalImage.class);
        personalImage.setUser(user);

        personImageDao.save(personalImage);
        return ResultUtil.createSuccess("保存图片成功");
    }

    @Override
    public void testFollowers() {
        User user = userDao.findOne(3L);
        User user1 = userDao.findOne(4L);
        User user2 = userDao.findOne(5L);


        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);


        user.getFolloweds().addAll(users);
        userDao.save(user);
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
    public ResultInfo updateUser(UserVo userVo) {
        User user = userDao.findOne(userVo.getId());
        if (user == null) {
            return ResultUtil.createFail("没有此用户");
        }
        BeanUtils.copyProperties(userVo, user);
        userDao.save(user);
        return ResultUtil.createSuccess("更新用户成功");
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
}
