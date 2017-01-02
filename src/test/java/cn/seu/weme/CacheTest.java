package cn.seu.weme;

import cn.seu.weme.dao.PersonDao;
import cn.seu.weme.entity.Person;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by LCN on 2016-12-19.
 */
public class CacheTest extends BaseTest {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private PersonDao personDao;

    @Test
    public void test01(){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(1, 10, sort);
        Page<Person> persons = personDao.findPersonByName("lucannan72",pageable);
        cacheManager.getCache("persons");
    }

}
