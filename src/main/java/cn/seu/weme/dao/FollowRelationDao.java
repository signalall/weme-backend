package cn.seu.weme.dao;

import cn.seu.weme.entity.FollowRelation;
import cn.seu.weme.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by LCN on 2017-1-4.
 */
public interface FollowRelationDao extends CrudRepository<FollowRelation, Long> {


    @Query(value = "select fr from FollowRelation as fr where fr.follower.id =:followerId and fr.followed.id = :followedId")
    FollowRelation findByFollowerAndFollowed(@Param("followerId") Long followerId, @Param("followedId") Long followedId);
}
