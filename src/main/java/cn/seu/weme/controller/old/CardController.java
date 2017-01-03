package cn.seu.weme.controller.old;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by LCN on 2017-1-3.
 */
@RestController
@RequestMapping(value = "/card_route")
public class CardController {

    @RequestMapping(value = "/publishcard", method = RequestMethod.POST)
    public Map publishCard() {

        return null;
    }


    @RequestMapping(value = "/getfoodcard", method = RequestMethod.POST)
    public Map getFoodCard() {

        return null;
    }



    @RequestMapping(value = "/likefoodcard", method = RequestMethod.POST)
    public Map likeFoodCard() {

        return null;
    }
}
