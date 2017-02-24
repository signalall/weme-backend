package cn.seu.weme;

import cn.seu.weme.common.utils.CryptoUtils;
import cn.seu.weme.common.utils.JWTUtils;
import cn.seu.weme.dao.UserDao;
import cn.seu.weme.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class UserTokenTest extends BaseTest {

    @Autowired
    private UserDao userDao;


    public void generateTokenAndPassword() {
        List<User> users = (List<User>) userDao.findAll();
        for (User user : users) {
            String token = JWTUtils.generateToken(user.getPassword());
            String salt = CryptoUtils.getSalt();
            String hashedPassword = CryptoUtils.getHash(user.getPassword(), salt);
            user.setToken(token);
            user.setPassword(hashedPassword);
            user.setSalt(salt);
            userDao.save(user);
        }
    }


    public void generateToken() {
        List<User> users = (List<User>) userDao.findAll();
        for (User user : users) {
            String token = JWTUtils.generateToken(user.getSalt());
            user.setToken(token);
            userDao.save(user);
        }
    }
}
