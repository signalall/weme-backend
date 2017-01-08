package cn.seu.weme.service.impl;

import cn.seu.weme.common.result.ResponseInfo;
import cn.seu.weme.dao.UserDao;
import cn.seu.weme.service.CheckUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LCN on 2017-1-5.
 */
@Service
public class CheckUserServiceImpl implements CheckUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean validateUser(String token) {
        if (userDao.findByToken(token) == null)
            return false;
        return true;
    }

    @Override
    public ResponseInfo failResponse() {
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setState("fail");
        responseInfo.setReason("invalid");
        return responseInfo;
    }
}
