package cn.seu.weme.controller.old;

import cn.seu.weme.dao.AndroidVersionDao;
import cn.seu.weme.entity.AndroidVersion;
import cn.seu.weme.service.AndroidVersionService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by LCN on 2017-1-3.
 */
@RestController
@RequestMapping(value = "/androidapk_route")
public class AndroidController {

    @Autowired
    private AndroidVersionService androidVersionService;

    @RequestMapping(value = "/checkapkversion", method = RequestMethod.POST)
    public Map chechApkVersion(@RequestBody JSONObject jsonObject) {

        Integer v1_now = jsonObject.getInt("V1");
        if (v1_now==null) v1_now=1;
        Integer v2_now = jsonObject.getInt("V2");
        if (v2_now==null) v2_now =0;
        Integer v3_now =jsonObject.getInt("V3");
        if(v3_now ==null) v3_now =0;

        return androidVersionService.chechApkVersion(v1_now,v2_now,v3_now);

    }
}
