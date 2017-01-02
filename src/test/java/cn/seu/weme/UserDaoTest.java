package cn.seu.weme;

import cn.seu.weme.dao.PersonDao;
import cn.seu.weme.entity.Person;
import cn.seu.weme.entity.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by LCN on 2016-12-19.
 */
@Transactional
public class UserDaoTest extends BaseTest {

    @Autowired
    private PersonDao personDao;

    private Logger logger = LoggerFactory.getLogger(UserDaoTest.class);


    @Test
    public void testGetUserByName() {
        List<Person> persons = personDao.getUserByName("lucannan123");
        persons.forEach(args->logger.info(args.toString()));
    }


    @Test
    public void testJpql(){
        List<Person> persons = personDao.jqqltest(23,"lucannan123");
        persons.forEach(args->logger.info(args.toString()));
    }


    @Test
    public void testSql(){
        List<Person> persons = personDao.testSql("lucannan123");
        persons.forEach(args->logger.info(args.toString()));
    }



    @Test
    public void createUsers(){
        for (int i = 0; i < 100; i++) {
            Person person = new Person();
            person.setAge(i);
            person.setName("lucannan" + i);
            personDao.save(person);
        }
    }



    @Test
    public void testPage(){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(1, 10, sort);
        Page<Person> persons = personDao.findPersonByName("lucannan72",pageable);

        persons.forEach(System.out::println);
    }
}
