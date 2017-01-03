package cn.seu.weme.controller.old;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by LCN on 2017-1-3.
 */
@RestController
@RequestMapping(value = "/push")
public class PushController {

    @RequestMapping(value = "/uploadiosdevicetoken", method = RequestMethod.POST)
    public Map uploadIosDeviceToken() {
        return null;
    }
}
