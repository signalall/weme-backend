package cn.seu.weme.dao;

import cn.seu.weme.entity.LikeUserRelation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by LCN on 2017-1-4.
 */
public interface LikeUserRelationDao extends CrudRepository<LikeUserRelation, Long> {
    @Query(value = "select lur from LikeUserRelation as lur where lur.liker.id =:likerId and lur.liked.id = :likedId")
    LikeUserRelation findByLikerAndLiked(@Param("likerId") Long likerId, @Param("likedId") Long likedId);
}
