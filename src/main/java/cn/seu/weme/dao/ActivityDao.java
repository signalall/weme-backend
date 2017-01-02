package cn.seu.weme.dao;

import cn.seu.weme.entity.Activity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by LCN on 2016-12-17.
 */
public interface ActivityDao extends CrudRepository<Activity,Long> {
}
