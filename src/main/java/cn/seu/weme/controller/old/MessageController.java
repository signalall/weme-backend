package cn.seu.weme.controller.old;

import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by LCN on 2017-1-3.
 */
@RestController
@RequestMapping(value = "/personalmessage_route")
public class MessageController {

    @RequestMapping(value = "/unreadmessagenum", method = RequestMethod.POST)
    public Map unReadMessageNum(@RequestBody String token) {

        return null;
    }


    @RequestMapping(value = "/readcommunitynotification", method = RequestMethod.POST)
    public Map readCommunityNotification(@RequestBody JSONObject jsonObject) {

        return null;
    }


    @RequestMapping(value = "/systemnotification", method = RequestMethod.POST)
    public Map systemNotification(@RequestBody String token) {

        return null;
    }


    @RequestMapping(value = "/sendmessage", method = RequestMethod.POST)
    private Map sendMessage(@RequestBody JSONObject jsonObject) {

        return null;
    }


    @RequestMapping(value = "/readmessage", method = RequestMethod.POST)
    public Map readMessage(@RequestBody JSONObject jsonObject) {

        return null;
    }


    @RequestMapping(value = "/getSendUserList", method = RequestMethod.POST)
    public Map getSendUserList() {

        return null;
    }

    @RequestMapping(value = "/getMessageDetailList", method = RequestMethod.POST)
    public Map getMessageDetailList() {
        return null;
    }

    @RequestMapping(value = "/getmessageunreadnumber", method = RequestMethod.POST)
    public Map getMessageUnReadNumber() {
        return null;
    }


}
