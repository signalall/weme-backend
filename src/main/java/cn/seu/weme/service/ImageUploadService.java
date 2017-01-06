package cn.seu.weme.service;

import cn.seu.weme.common.result.ResponseInfo;
import cn.seu.weme.dto.old.UploadFileVo;

/**
 * Created by LCN on 2017-1-6.
 */
public interface ImageUploadService {

    public ResponseInfo uploadFile(UploadFileVo uploadFileVo) ;
}
