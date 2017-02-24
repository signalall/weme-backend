package cn.seu.weme.dao;

import cn.seu.weme.BaseTest;
import cn.seu.weme.entity.Activity;
import cn.seu.weme.entity.Comment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by LCN on 2017-1-4.
 */
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class CommentTest extends BaseTest {

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private CommentDao commentDao;


    //    @Test
    public void testCommentToActivity() {
        Activity activity = activityDao.findOne(1L);
        Comment comment = new Comment();
        comment.setActivity(activity);
        comment.setContent("test comment");

        commentDao.save(comment);
    }
}
