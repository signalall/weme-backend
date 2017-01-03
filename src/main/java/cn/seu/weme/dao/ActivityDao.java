package cn.seu.weme.dao;

import cn.seu.weme.entity.Activity;
import cn.seu.weme.entity.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by LCN on 2016-12-17.
 */
public interface ActivityDao extends CrudRepository<Activity, Long> {
}
