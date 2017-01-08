package cn.seu.weme.dao;

import cn.seu.weme.entity.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by LCN on 2017-1-7.
 */
public interface MessageDao extends CrudRepository<Message, Long> {

    @Query(value = "select count (message) from Message as message where message.state = false  and message.sendTo.id = :userId")
    int getUnReadNumByUserId(@Param("userId") Long userId);

    @Query(value = "update Message m set m.state = true where m.id = :messageId")
    void readMessage(@Param("messageId") Long messageId);


}
