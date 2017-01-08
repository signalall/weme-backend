package cn.seu.weme.controller.old;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by LCN on 2017-1-3.
 */
@RestController
//@RequestMapping(value = "/androidapk_route")
public class AndroidController {

    @RequestMapping(value = "/checkapkversion", method = RequestMethod.POST)
    public Map chechApkVersion() {

        return null;
    }
}
