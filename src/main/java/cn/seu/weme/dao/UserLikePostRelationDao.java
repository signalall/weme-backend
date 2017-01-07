package cn.seu.weme.dao;

import cn.seu.weme.entity.UserLikePostRelation;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by LCN on 2017-1-7.
 */
public interface UserLikePostRelationDao  extends CrudRepository<UserLikePostRelation, Long> {
}
