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
    public void attendActivity(Long userId, Long activityId);


    public User findByPhone(String phone);

    public User findByUsername(String username);

    public User findByToken(String token);

//    @Query(value = "insert into t_follower_followed(follower_id,followed_id) VALUES(?1,?2)",nativeQuery = true)
//    public void followUser(Long followerId, Long followedId);
}
