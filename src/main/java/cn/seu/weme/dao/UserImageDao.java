package cn.seu.weme.dao;

import cn.seu.weme.entity.UserImage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * todo limit
 * Created by LCN on 2016-12-21.
 */
public interface UserImageDao extends CrudRepository<UserImage, Long> {


    @Query(value = "select userImage from UserImage as userImage where userImage.user.id =:userId and " +
            "userImage.disable = false and userImage.id > :previousImageId ")
    public List<UserImage> getPersonalImages2(@Param("userId") Long userId, @Param("previousImageId") Long previousImageId);


    @Query(value = "select userImage from UserImage as userImage where userImage.user.id =:userId ")
    public List<UserImage> getPersonalImages(@Param("userId") Long userId);

}
