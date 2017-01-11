package cn.seu.weme.dao;

import cn.seu.weme.entity.FoodCard;
import cn.seu.weme.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by LCN on 2017-1-3.
 */
public interface FoodCardDao extends CrudRepository<FoodCard, Long> {

    @Query("from FoodCard a where a.passFlag = true ")
    List<FoodCard> findActivityByPassFlagTrue();

}
