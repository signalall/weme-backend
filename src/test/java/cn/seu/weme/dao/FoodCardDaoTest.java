package cn.seu.weme.dao;

import cn.seu.weme.BaseTest;
import cn.seu.weme.entity.FoodCard;
import cn.seu.weme.entity.LikeFoodCard;
import cn.seu.weme.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by LCN on 2017-1-3.
 */
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class FoodCardDaoTest extends BaseTest {

    @Autowired
    private FoodCardDao foodCardDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private LikeFoodCardDao likeFoodCardDao;

    //    @Test
    public void addFoodCard() {
        FoodCard foodCard = new FoodCard();
        foodCard.setComment("comment test");
        foodCardDao.save(foodCard);
    }

    //    @Test
    public void likeFoodCard() {
        User user = userDao.findOne(113L);
        FoodCard foodCard = foodCardDao.findOne(2L);
        LikeFoodCard likeFoodCard = new LikeFoodCard();
        likeFoodCard.setUser(user);
        likeFoodCard.setFoodCard(foodCard);
        likeFoodCardDao.save(likeFoodCard);
    }

    //    @Test
    public void testGetLikeUser() {
        FoodCard foodCard = foodCardDao.findOne(2L);
        foodCard.getLikeFoodCards();
    }
}
