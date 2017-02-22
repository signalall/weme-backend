package cn.seu.weme.controller.old;

import cn.seu.weme.common.result.ResponseInfo;
import cn.seu.weme.service.CheckUserService;
import cn.seu.weme.service.FoodCardService;
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
//@RequestMapping(value = "/card_route")
public class CardController {


    @Autowired
    private FoodCardService foodCardService;

    @Autowired
    private CheckUserService checkUserService;

    @RequestMapping(value = "/publishcard", method = RequestMethod.POST)
    public ResponseInfo publishCard(@RequestBody JSONObject jsonObject) {

        String token = jsonObject.getString("token");
        if (token == null || !checkUserService.validateUser(token))
            return failResponse();

        String title = jsonObject.getString("title");
        String location = jsonObject.getString("location");
        String longitude = jsonObject.getString("longitude");
        String latitude = jsonObject.getString("latitude");
        Double price = jsonObject.getDouble("price");
        String comment = jsonObject.getString("comment");
        return foodCardService.publishCard(token,title,location,longitude,latitude,price,comment);
    }


    @RequestMapping(value = "/getfoodcard", method = RequestMethod.POST)
    public ResponseInfo getFoodCard(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        if (token ==null||!checkUserService.validateUser(token))
            return failResponse();
        return foodCardService.getFoodCard(token);

    }



    @RequestMapping(value = "/likefoodcard", method = RequestMethod.POST)
    public ResponseInfo likeFoodCard(@RequestBody JSONObject jsonObject) {

        String token = jsonObject.getString("token");
        if (token ==null||!checkUserService.validateUser(token))
            return failResponse();
        Long foodcardid = jsonObject.getLong("foodcardid");

        return foodCardService.likeFoodCard(token,foodcardid);
    }


    private ResponseInfo failResponse() {
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setState("fail");
        responseInfo.setReason("用户不存在");
        return responseInfo;
    }

}
