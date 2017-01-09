package cn.seu.weme.service.impl;

import cn.seu.weme.dao.UserDao;
import cn.seu.weme.service.SchoolCertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by Visen (zhvisen@gmail.com) on 2017/1/5.
 */
@Service
@Transactional
public class SchoolCertServiceImpl implements SchoolCertService {


    @Autowired
    private UserDao userDao;



    public Map publishCertification(){
        return null;
    }


    public Map getAllCertifications(){
        return null;
    }



    public Map setPassCertification(){
        return null;
    }



    public Map setNoPassCertification(){
        return null;
    }



}
