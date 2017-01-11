package cn.seu.weme.dao;

import cn.seu.weme.entity.AvatarVoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Visen (zhvisen@gmail.com) on 2017/1/6.
 */
public interface AvatarVoiceDao extends PagingAndSortingRepository<AvatarVoice,Long> {

    Page<AvatarVoice> findByGender(Pageable pageable,String gender);



}
