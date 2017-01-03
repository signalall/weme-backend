package cn.seu.weme.dao;

import cn.seu.weme.entity.PersonalImage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * todo limit
 * Created by LCN on 2016-12-21.
 */
public interface PersonImageDao extends CrudRepository<PersonalImage, Long> {


    @Query(value = "select personImage from PersonalImage as personImage where personImage.user.id =:userId and " +
            "personImage.disable = false and personImage.id > :previousImageId ")
    public List<PersonalImage> getPersonalImages2(@Param("userId") Long userId, @Param("previousImageId") Long previousImageId);


    @Query(value = "select personImage from PersonalImage as personImage where personImage.user.id =:userId ")
    public List<PersonalImage> getPersonalImages(@Param("userId") Long userId);

}
