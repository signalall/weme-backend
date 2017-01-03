package cn.seu.weme.dao;

import cn.seu.weme.entity.CheckMsg;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by LCN on 2017-1-2.
 */
public interface CheckMsgDao extends CrudRepository<CheckMsg, Long> {
    public CheckMsg findByPhone(String phone);
}
