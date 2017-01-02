package cn.seu.weme.dao;

import cn.seu.weme.entity.PersonalImage;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by LCN on 2016-12-21.
 */
public interface PersonImageDao extends CrudRepository<PersonalImage, Long> {
}
