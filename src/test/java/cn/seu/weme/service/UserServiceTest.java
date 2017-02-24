package cn.seu.weme.service;

import cn.seu.weme.BaseTest;
import cn.seu.weme.dao.FollowRelationDao;
import cn.seu.weme.dao.UserDao;
import cn.seu.weme.dao.UserVisitRelationDao;
import cn.seu.weme.entity.FollowRelation;
import cn.seu.weme.entity.User;
import cn.seu.weme.entity.UserVisitRelation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by LCN on 2017-1-4.
 */
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private FollowRelationDao followRelationDao;

    @Autowired
    private UserVisitRelationDao userVisitRelationDao;

    //    @Test
    public void testAddUser() {
//        User user1 = new User();
//        user1.setPassword("123");
//        user1.setSalt("123");
//        userDao.save(user1);
//
//
//        User user2 = new User();
//        user2.setPassword("123");
//        user2.setSalt("123");
//        userDao.save(user2);

    }

    //    @Test
    public void testFollowUser() {
        User follower = userDao.findOne(1L);
        User followed = userDao.findOne(2L);
        FollowRelation followRelation = new FollowRelation(follower, followed);
        followRelationDao.save(followRelation);
    }


    //    @Test
    public void testVisitUser() {
        User visiter = userDao.findOne(1L);
        User visited = userDao.findOne(2L);
        UserVisitRelation userVisitRelation = new UserVisitRelation(visiter, visited);
        userVisitRelationDao.save(userVisitRelation);
    }


    //    @Test
    public void testGetVisitedUser(){
        User visiter = userDao.findOne(1L);
//        List<UserVisitRelation> visterRelations = visiter.getVisterRelations();
//        List<UserVisitRelation> visitedRelations = visiter.getVisitedRelation();
        System.out.println();
    }
}
