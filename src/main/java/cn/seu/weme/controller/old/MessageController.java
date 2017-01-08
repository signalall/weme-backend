package cn.seu.weme.controller.old;

import cn.seu.weme.common.result.ResponseInfo;
import cn.seu.weme.service.CheckUserService;
import cn.seu.weme.service.MessageService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by LCN on 2017-1-3.
 */
@RestController
//@RequestMapping(value = "/personalmessage_route")
public class MessageController {


    @Autowired
    private CheckUserService checkUserService;
    @Autowired
    private MessageService messageService;


    @RequestMapping(value = "/unreadmessagenum", method = RequestMethod.POST)
    public ResponseInfo unReadMessageNum(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return checkUserService.failResponse();
        }

        return messageService.getUnReadMessageNum(token);
    }


    @RequestMapping(value = "/readcommunitynotification", method = RequestMethod.POST)
    public ResponseInfo readCommunityNotification(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return checkUserService.failResponse();
        }

        Long commentId = jsonObject.getLong("commentid");
        return messageService.readComment(token, commentId);
    }


    @RequestMapping(value = "/systemnotification", method = RequestMethod.POST)
    public ResponseInfo systemNotification(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return checkUserService.failResponse();
        }
        return null;
    }


    @RequestMapping(value = "/sendmessage", method = RequestMethod.POST)
    private ResponseInfo sendMessage(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return checkUserService.failResponse();
        }
        String text = jsonObject.getString("text");
        Long toUserId = jsonObject.getLong("RecId");
        return messageService.sendMessage(token, toUserId, text);
    }


    @RequestMapping(value = "/readmessage", method = RequestMethod.POST)
    public ResponseInfo readMessage(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return checkUserService.failResponse();
        }
        Long messageId = jsonObject.getLong("id");
        return messageService.readMessage(token, messageId);
    }


    @RequestMapping(value = "/getSendUserList", method = RequestMethod.POST)
    public ResponseInfo getSendUserList(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return checkUserService.failResponse();
        }
        return messageService.getSendUserList(token);
    }

    @RequestMapping(value = "/getMessageDetailList", method = RequestMethod.POST)
    public ResponseInfo getMessageDetailList(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return checkUserService.failResponse();
        }

        Long sendId = jsonObject.getLong("SendId");
        int page = jsonObject.getInt("page");
        return messageService.getMessageDetailList(token, sendId, page);
    }

    @RequestMapping(value = "/getmessageunreadnumber", method = RequestMethod.POST)
    public ResponseInfo getMessageUnReadNumber(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token)) {
            return checkUserService.failResponse();
        }
        return messageService.getUnReadMessageNum(token);
    }


}
