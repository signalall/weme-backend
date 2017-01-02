package cn.seu.weme;

import cn.seu.weme.dao.UserDao;
import cn.seu.weme.entity.Activity;
import cn.seu.weme.entity.User;
import cn.seu.weme.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by LCN on 2016-12-17.
 */

public class UserServiceTest extends BaseTest{

    private final Logger loger = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Test
    @Transactional
    public void testAttendActivities() {
        long starTime = System.currentTimeMillis();
        for (int i = 21; i < 30; i++) {
            userService.attendActity(1L, (long) i);
        }
        long endTime = System.currentTimeMillis();
        long time = endTime - starTime;
        loger.info(String.valueOf(time));
    }
}
