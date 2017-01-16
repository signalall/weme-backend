package cn.seu.weme.dao;

import cn.seu.weme.entity.Message;
import cn.seu.weme.entity.MessageImage;
import cn.seu.weme.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by LCN on 2017-1-7.
 */
public interface MessageDao extends CrudRepository<Message, Long> {

    @Query(value = "select count (message) from Message as message where message.state = false  and message.sendTo.id = :userId")
    int getUnReadNumByUserId(@Param("userId") Long userId);

    @Query(value = "update Message m set m.state = true where m.id = :messageId")
    void readMessage(@Param("messageId") Long messageId);

    @Query(value = "select c from Message as c where c.sendFrom.id = :userId order by c.timestamp desc ")
    List<Message> getSendTo(@Param("userId") Long userId);


    @Query(value = "select c from Message as c where c.sendTo.id = :userId order by c.timestamp desc ")
    List<Message> getSendFrom(@Param("userId") Long userId);


    @Query(value = "select c from Message as c where c.sendTo.id = :userId or c.sendFrom.id = :userId  ")
    List<Message> getMessageBiDirection(@Param("userId") Long userId, Pageable pageable);


    @Query(value = "select mi from MessageImage as mi where mi.message.id = :messageId")
    List<MessageImage> getMessageImages(@Param("messageId") Long messageId);

}
