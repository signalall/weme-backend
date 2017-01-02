package cn.seu.weme.dao;

import cn.seu.weme.entity.MongoUser;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by LCN on 2016-12-22.
 */
public interface MongoUserDao extends MongoRepository<MongoUser, Long> {
    MongoUser findByUsername(String username);
}
