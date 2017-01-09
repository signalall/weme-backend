package cn.seu.weme.service.impl;

import cn.seu.weme.dao.AndroidVersionDao;
import cn.seu.weme.entity.AndroidVersion;
import cn.seu.weme.service.AndroidVersionService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Visen (zhvisen@gmail.com) on 2017/1/5.
 */
@Service
@Transactional
public class AndroidVersionImpl implements AndroidVersionService {

    @Autowired
    private AndroidVersionDao androidVersionDao;


    public Map chechApkVersion(Integer v1, Integer v2, Integer v3){
        Map<String ,Object> m = new HashedMap();
        String updateflag="no";
        String apkurl="";
        AndroidVersion androidVersion = androidVersionDao.getNewestAndroidVersion();
        if (androidVersion==null)
        {
            Integer NumberNow =v1*10000+v2*100+v3;
            Integer Newest = androidVersion.getV1()*10000+androidVersion.getV2()*100+androidVersion.getV3();
            if (NumberNow < Newest)
            {
                updateflag ="yes";
                apkurl = androidVersion.getWemeurl();

            }
            else
            {
                updateflag ="no";
                apkurl = "";
            }
            m.put("state","successful");
            m.put("reason","");
            HashMap<String,Object> temp =new HashMap<>();
            temp.put("v1",androidVersion.getV1());
            temp.put("v2",androidVersion.getV2());
            temp.put("v3",androidVersion.getV3());
            m.put("version_newest",temp);
            m.put("updateflag",updateflag);
            m.put("apkurl",apkurl);
            return m;
        }
        else
        {
            m.put("state","fail");
            m.put("reason","Not apk in server");
            m.put("version_newest","");
            m.put("updateflag",updateflag);
            m.put("apkurl",apkurl);
            return m;
        }

    }

}
