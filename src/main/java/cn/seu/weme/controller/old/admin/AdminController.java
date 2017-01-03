package cn.seu.weme.controller.old.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by LCN on 2017-1-3.
 */
@RestController
@RequestMapping(value = "/adminuser_route")
public class AdminController {

    @RequestMapping(value = "/getallactivity", method = RequestMethod.POST)
    public Map getAllActivities() {

        return null;
    }


    @RequestMapping(value = "/setpassactivity", method = RequestMethod.POST)
    public Map setPassActivity() {

        return null;
    }


    @RequestMapping(value = "/setnopassactivity", method = RequestMethod.POST)
    public Map setNoPassActivity() {

        return null;
    }

    @RequestMapping(value = "/getallusercard", method = RequestMethod.POST)
    public Map getAllUserCard() {

        return null;
    }


    @RequestMapping(value = "/getallusercardfilter", method = RequestMethod.POST)
    public Map getAllUserCardFilter() {

        return null;
    }


    @RequestMapping(value = "/getallusercardbygender", method = RequestMethod.POST)
    public Map getAllUserCardByGender() {

        return null;
    }


    @RequestMapping(value = "/setpassusercard", method = RequestMethod.POST)
    public Map setPassUserCard() {

        return null;
    }



    @RequestMapping(value = "/setnopassusercard", method = RequestMethod.POST)
    public Map setNoPassUserCard() {

        return null;
    }

}
