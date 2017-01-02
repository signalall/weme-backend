package cn.seu.weme.dao;

import cn.seu.weme.entity.Topic;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by LCN on 2016-12-18.
 */
public interface TopicDao extends CrudRepository<Topic, Long> {
}
