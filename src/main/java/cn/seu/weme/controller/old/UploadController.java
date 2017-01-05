package cn.seu.weme.controller.old;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by LCN on 2017-1-3.
 */
@RestController
//@RequestMapping(value = "/upload_image")
public class UploadController {

    @RequestMapping(value = "/uploadavatar", method = RequestMethod.POST)
    public Map deleteUser() {
        return null;
    }
}
