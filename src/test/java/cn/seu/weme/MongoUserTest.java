package cn.seu.weme;

import cn.seu.weme.dao.MongoUserDao;
import cn.seu.weme.entity.MongoUser;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;

/**
 * Created by LCN on 2016-12-22.
 */
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class MongoUserTest extends BaseTest {

    @Autowired
    private MongoUserDao mongoUserDao;


    //    @Test
    public void test() throws Exception {

        mongoUserDao.save(new MongoUser(1L, "didi", 30));
        mongoUserDao.save(new MongoUser(2L, "mama", 40));
        mongoUserDao.save(new MongoUser(3L, "kaka", 50));
        Assert.assertEquals(3, mongoUserDao.findAll().size());

        MongoUser u = mongoUserDao.findOne(1L);
        mongoUserDao.delete(u);
        Assert.assertEquals(2, mongoUserDao.findAll().size());

        u = mongoUserDao.findByUsername("mama");
        mongoUserDao.delete(u);
        Assert.assertEquals(1, mongoUserDao.findAll().size());

    }
}
