package cn.seu.weme.dao;

import cn.seu.weme.entity.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by LCN on 2016-12-18.
 */
public interface CommentDao extends CrudRepository<Comment, Long> {

    @Query(value = "select count (c) from Comment as c where c.toUser.id=:userId and " +
            " c.state = false and c.type = 1")
    int getUnReadPostCommentNum(@Param("userId") Long userId);


    @Query(value = "select count (c) from Comment as c where c.toUser.id=:userId and " +
            " c.state = false and c.type = 3")
    int getUnReadCommentNum(@Param("userId") Long userId);

    @Query(value = "update Comment c set c.state = true where c.id = :commentId")
    void readComment(@Param("commentId") Long commentId);

    @Query(value = "select c from Comment as c where c.toUser.id = :userId")
    List<Comment> getAllUnReadComments(@Param("userId") Long userId);
}
