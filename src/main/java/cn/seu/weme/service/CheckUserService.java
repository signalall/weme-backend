package cn.seu.weme.service;

import cn.seu.weme.common.result.ResponseInfo;

/**
 * Created by LCN on 2017-1-5.
 */
public interface CheckUserService {

    boolean validateUser(String token);

    ResponseInfo failResponse();
}
