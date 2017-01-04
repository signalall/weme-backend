package cn.seu.weme.service;

import cn.seu.weme.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by LCN on 2017-1-4.
 */
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class CommentServiceTest extends BaseTest {

    @Autowired
    private ActivityService activityService;

    @Test
    public void testCommentToActivityComment(){
        activityService.commentToActivityComent("",1L,"test");
    }
}
