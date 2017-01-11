package cn.seu.weme.dao;

import cn.seu.weme.entity.AndroidVersion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Visen (zhvisen@gmail.com) on 2017/1/5.
 */
public interface AndroidVersionDao extends CrudRepository<AndroidVersion, Long> {

    @Query("from AndroidVersion  a where a.disable = true order by a.timestamp desc")
    AndroidVersion getNewestAndroidVersion();
}
