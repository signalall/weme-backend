package cn.seu.weme.controller.old;

import cn.seu.weme.common.result.ResponseInfo;
import cn.seu.weme.dto.SchoolInfoVo;
import cn.seu.weme.dto.UserInfoVo;
import cn.seu.weme.dto.UserVo;
import cn.seu.weme.service.CheckUserService;
import cn.seu.weme.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LCN on 2017-1-3.
 */
@RestController
//@RequestMapping(value = "/editprofile_route")
public class EditInfoController {

    @Autowired
    private UserService userService;

    @Autowired
    private CheckUserService checkUserService;

    @RequestMapping(value = "/editprofileinfo", method = RequestMethod.POST)
    public ResponseInfo editProfileInfo(@RequestBody UserVo userVo) {

        if (userVo.getToken() == null || !checkUserService.validateUser(userVo.getToken())){
           return checkUserService.failResponse();
        }

        return userService.updateUser(userVo);
    }

    @RequestMapping(value = "/editprofile/editschoolinformation", method = RequestMethod.POST)
    public ResponseInfo editSchoolInfomation(@RequestBody SchoolInfoVo schoolInfoVo) {

        if (schoolInfoVo.getToken() == null || !checkUserService.validateUser(schoolInfoVo.getToken())){
            return checkUserService.failResponse();
        }


        return userService.editSchoolInfo(schoolInfoVo);
    }


    @RequestMapping(value = "/editprofile/editpersonalinformation", method = RequestMethod.POST)
    public ResponseInfo editPersonInfo(@RequestBody UserInfoVo userInfoVo) {
        if (userInfoVo.getToken() == null || !checkUserService.validateUser(userInfoVo.getToken())){
            return checkUserService.failResponse();
        }
        return userService.editPersonInfo(userInfoVo);
    }


    @RequestMapping(value = "/editprofile/editpreferinformation", method = RequestMethod.POST)
    public ResponseInfo editPreferInfo(@RequestBody JSONObject jsonObject) {

        String token = jsonObject.getString("token");
        String hobby = jsonObject.getString("hobby");
        String preference = jsonObject.getString("preference");

        if (token == null || !checkUserService.validateUser(token)){
            return checkUserService.failResponse();
        }

        return userService.editPreferenceInfo(token, hobby, preference);
    }

    @RequestMapping(value = "/editprofile/editcardsetting", method = RequestMethod.POST)
    public ResponseInfo editCardSetting(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        String cardflag = jsonObject.getString("cardflag");

        if (token == null || !checkUserService.validateUser(token)){
            return checkUserService.failResponse();
        }

        return userService.editCardSetting(token, cardflag);
    }
}
