package cn.seu.weme.dao;

import cn.seu.weme.entity.User;
import org.hibernate.annotations.SQLInsert;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by LCN on 2016-12-17.
 */
public interface UserDao extends CrudRepository<User, Long> {

    @Query(value = "update t_attend_user_activity set activity_id = ?1 and user_id = ?2", nativeQuery = true)
    public void attendActivity(Long userId, Long activityId);


    public User findByPhone(String phone);

    public User findByUsername(String username);

    public User findByToken(String token);

    @Query(value = "select count(*) from t_attend_user_activity where user_id =:userId and activity_id=:activityId",nativeQuery = true)
    public int isAttendActivity(@Param("userId") Long userId,@Param("activityId") Long activityId);


    @Query(value = "select count(*) from t_like_user_activity where user_id =:userId and activity_id=:activityId",nativeQuery = true)
    public int isLikeActivity(@Param("userId") Long userId,@Param("activityId") Long activityId);

}
