package cn.seu.weme;

import cn.seu.weme.dao.AddressDao;
import cn.seu.weme.dao.PersonDao;
import cn.seu.weme.entity.Address;
import cn.seu.weme.entity.Person;
import cn.seu.weme.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by LCN on 2016-12-19.
 */

@Transactional
public class EntityManagerTest extends BaseTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private PersonDao personDao;


    @Test
    public void test1() {
        User user = new User(80, "lucannan");
        entityManager.persist(user);
    }

}
