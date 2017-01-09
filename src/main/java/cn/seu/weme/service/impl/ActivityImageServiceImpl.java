package cn.seu.weme.service.impl;

import cn.seu.weme.dao.UserDao;
import cn.seu.weme.entity.ActivityImage;
import cn.seu.weme.service.ActivityImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by Visen (zhvisen@gmail.com) on 2017/1/6.
 */
@Service
@Transactional
public class ActivityImageServiceImpl implements ActivityImageService {

    @Autowired
    private UserDao userDao;


    public Map getAllAcitivity(String token,int page,int number){

        return null;

    }


}
