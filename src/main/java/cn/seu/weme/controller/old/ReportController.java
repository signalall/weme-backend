package cn.seu.weme.controller.old;

import cn.seu.weme.service.ReportService;
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
//@RequestMapping(value = "/report_route")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "/publishreport", method = RequestMethod.POST)
    public Map publishReport(@RequestBody JSONObject jsonObject) {

        String token = jsonObject.getString("token");
        String body = jsonObject.getString("body");
        Integer type = jsonObject.getInt("type");
        Integer typeid= jsonObject.getInt("typeid");

        return reportService.publishreport(token,body,type,typeid);
    }
}
