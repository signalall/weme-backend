package cn.seu.weme;

import cn.seu.weme.dao.CustomerRepository;
import cn.seu.weme.entity.Customer;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by LCN on 2016-12-17.
 */
public class CustomerRepositoryTests extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(CustomerRepositoryTests.class);


    @Autowired
    private CustomerRepository customers;

    @Test
    public void testFindByLastName() {
        Customer customer = new Customer("first", "last");
        customers.save(customer);

        List<Customer> findByLastName = customers.findByLastName(customer.getLastName());


        for (Customer customer1 : findByLastName){
            log.info(customer1.toString());
        }

        Customer customer1 = customers.findUser("first");

        log.info("test" + customer1.toString());

    }
}