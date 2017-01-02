package cn.seu.weme.dao;

import cn.seu.weme.entity.Person;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by LCN on 2016-12-19.
 */
@CacheConfig(cacheNames = "persons")
public interface PersonDao extends PagingAndSortingRepository<Person, Long> {

    public List<Person> getUserByName(String name);


    @Query(value = "select person from Person as person where person.age=:age and person.name = :name ")
    public List<Person> jqqltest(@Param("age") Integer age, @Param("name") String name);


    @Query(value = "select * from person where name =:name order by age desc ", nativeQuery = true)
    public List<Person> testSql(@Param("name") String username);


    @org.springframework.cache.annotation.Cacheable
    Page<Person> findPersonByName(String name, Pageable pageable);
}
