package cn.seu.weme.dao;

import cn.seu.weme.entity.User;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by LCN on 2016-12-17.
 */
public interface UserDao extends CrudRepository<User, Long> {

    @Query(value = "update t_attend_user_activity set activity_id = ?1 and user_id = ?2", nativeQuery = true)
    public void attenActivity(Long userId, Long activityId);


//    @Query(value = "insert into t_follower_followed(follower_id,followed_id) VALUES(?1,?2)",nativeQuery = true)
//    public void followUser(Long followerId, Long followedId);
}
