package cn.seu.weme.controller.old;

import cn.seu.weme.common.result.ResponseInfo;
import cn.seu.weme.dto.SchoolInfoVo;
import cn.seu.weme.dto.UserInfoVo;
import cn.seu.weme.dto.UserVo;
import cn.seu.weme.service.UserService;
import net.sf.json.JSONObject;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LCN on 2017-1-3.
 */
@RestController
@RequestMapping(value = "/editprofile_route")
public class EditInfoController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/editprofileinfo", method = RequestMethod.POST)
    public ResponseInfo editProfileInfo(@RequestBody UserVo userVo) {
        return userService.updateUser(userVo);
    }

    @RequestMapping(value = "/editprofile/editschoolinformation", method = RequestMethod.POST)
    public ResponseInfo editSchoolInfomation(@RequestBody SchoolInfoVo schoolInfoVo) {
        return userService.editSchoolInfo(schoolInfoVo);
    }


    @RequestMapping(value = "/editprofile/editpersonalinformation", method = RequestMethod.POST)
    public ResponseInfo editPersonInfo(@RequestBody UserInfoVo userInfoVo) {
        return userService.editPersonInfo(userInfoVo);
    }


    @RequestMapping(value = "/editprofile/editpreferinformation", method = RequestMethod.POST)
    public ResponseInfo editPreferInfo(@RequestBody JSONObject jsonObject) {

        String token = jsonObject.getString("token");
        String hobby = jsonObject.getString("hobby");
        String preference = jsonObject.getString("preference");

        return userService.editPreferenceInfo(token, hobby, preference);
    }

    @RequestMapping(value = "/editprofile/editcardsetting", method = RequestMethod.POST)
    public ResponseInfo editCardSetting(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        String cardflag = jsonObject.getString("cardflag");

        return userService.editCardSetting(token, cardflag);

    }
}
