package cn.seu.weme.dao;

import org.springframework.data.repository.CrudRepository;
import cn.seu.weme.entity.Address;

/**
 * Created by LCN on 2016-12-19.
 */
public interface AddressDao extends CrudRepository<Address,Long> {
}
