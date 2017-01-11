package cn.seu.weme.dao;

import cn.seu.weme.entity.Post;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by LCN on 2016-12-18.
 */
public interface PostDao extends CrudRepository<Post, Long> {


}
