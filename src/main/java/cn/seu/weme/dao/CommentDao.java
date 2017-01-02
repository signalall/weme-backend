package cn.seu.weme.dao;

import cn.seu.weme.entity.Comment;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by LCN on 2016-12-18.
 */
public interface CommentDao extends CrudRepository<Comment, Long> {
}
