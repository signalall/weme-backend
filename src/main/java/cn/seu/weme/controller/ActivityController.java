package cn.seu.weme.controller;

import cn.seu.weme.common.result.ResultInfo;
import cn.seu.weme.dto.ActivityVo;
import cn.seu.weme.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by LCN on 2016-12-18.
 */
@RestController
@RequestMapping(value = "/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public ResultInfo getActivitiesList() {
        return activityService.getAllActities();
    }


    @RequestMapping(value="/", method=RequestMethod.POST)
    public ResultInfo postActivity(@RequestBody ActivityVo activityVo) {
        return activityService.addActivity(activityVo);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResultInfo getActivityById(@PathVariable Long id) {
        return activityService.getActivityById(id);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResultInfo putActivity(@PathVariable Long id, @RequestBody ActivityVo activityVo) {
        activityVo.setId(id);
        return activityService.updateActivity(activityVo);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResultInfo deleteActivity(@PathVariable Long id) {
        return activityService.deleteActivityById(id);
    }

}
