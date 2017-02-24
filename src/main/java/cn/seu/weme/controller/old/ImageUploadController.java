package cn.seu.weme.controller.old;

import cn.seu.weme.common.result.ResponseInfo;
import cn.seu.weme.dto.old.UploadFileVo;
import cn.seu.weme.service.ImageUploadService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LCN on 2017-1-6.
 */
@RestController
public class ImageUploadController {

    @Autowired
    private ImageUploadService imageUploadService;


    @RequestMapping(value = "/uploadavatar", method = RequestMethod.POST)
    public ResponseInfo uploadFile(@RequestParam("json") String json, @RequestParam("avatar_path") String path) {
        JSONObject jsonObject = JSONObject.fromObject(json);
        String token = jsonObject.getString("token");
        int type = jsonObject.optInt("type", 0);
        int number = jsonObject.optInt("number", 0);
        Long messageId = jsonObject.optLong("messageid", 0L);
        Long postId = jsonObject.optLong("postid", 0L);
        Long topicOfficialId = jsonObject.optLong("topofficialid", 0L);
        Long commentId = jsonObject.optLong("commentid", 0L);
        Long activityId = jsonObject.optLong("activityid", 0L);
        Long topicId = jsonObject.optLong("topicId");

        UploadFileVo uploadFileVo = new UploadFileVo();
        uploadFileVo.setToken(token);
        uploadFileVo.setType(type);
        uploadFileVo.setNumber(number);
        uploadFileVo.setMessageId(messageId);
        uploadFileVo.setPostId(postId);
        uploadFileVo.setTopicOfficialId(topicOfficialId);
        uploadFileVo.setCommentId(commentId);
        uploadFileVo.setActivityId(activityId);
        uploadFileVo.setPath(path);
        uploadFileVo.setTopicId(topicId);

        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setResult(uploadFileVo);

//        return imageUploadService.uploadFile(uploadFileVo);
        //test 123
        return responseInfo;
    }
}
