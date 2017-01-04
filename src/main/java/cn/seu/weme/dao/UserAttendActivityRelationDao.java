package cn.seu.weme.dao;

import cn.seu.weme.entity.UserAttendActivityRelation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by LCN on 2017-1-4.
 */
public interface UserAttendActivityRelationDao extends CrudRepository<UserAttendActivityRelation, Long> {

    @Query(value = "select ua from  UserAttendActivityRelation as ua where ua.user.id =:userId and ua.activity.id= :activityId")
    public UserAttendActivityRelation findUserAttendActivityRelation(@Param("userId") Long userId, @Param("activityId") Long activityId);
}
