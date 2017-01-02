package cn.seu.weme.dao;

import cn.seu.weme.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by LCN on 2016-12-17.
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    List<Customer> findByFirstName(String firstName);

    @Query("from Customer u where u.firstName=:name")
    Customer findUser(@Param("name") String name);
}
