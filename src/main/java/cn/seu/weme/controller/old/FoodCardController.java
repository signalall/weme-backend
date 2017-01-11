package cn.seu.weme.controller.old;

import cn.seu.weme.common.result.ResponseInfo;
import cn.seu.weme.entity.User;
import cn.seu.weme.service.FoodCardService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by LCN on 2017-1-3.
 */
@RestController
@RequestMapping(value = "/card_route")
public class FoodCardController {

    @Autowired
    private FoodCardService foodCardService;

    @RequestMapping(value = "/publishcard", method = RequestMethod.POST)
    public Map publishCard(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        String title = jsonObject.getString("title");
        String location = jsonObject.getString("location");
        String longtitude = jsonObject.getString("longtitude");
        String latitude = jsonObject.getString("latitude");
        Double price =  jsonObject.getDouble("price");
        String comment = jsonObject.getString("comment");

        return foodCardService.publishCard(token,title,location,longtitude,latitude,price,comment);
    }


    @RequestMapping(value = "/getfoodcard", method = RequestMethod.POST)
    public Map getFoodCard(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");
        Long foodcardid = jsonObject.getLong("foodcardid");
        return foodCardService.likeFoodCard(token,foodcardid);
    }



    @RequestMapping(value = "/likefoodcard", method = RequestMethod.POST)
    public Map likeFoodCard(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("token");

        return foodCardService.getFoodCard(token);

    }
}
