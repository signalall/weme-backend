package cn.seu.weme.service.impl;

import cn.seu.weme.dao.ReportDao;
import cn.seu.weme.dao.UserDao;
import cn.seu.weme.entity.Report;
import cn.seu.weme.entity.User;
import cn.seu.weme.service.ReportService;
import org.apache.commons.collections.map.HashedMap;
import org.hibernate.annotations.common.reflection.java.generics.TypeEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Visen (zhvisen@gmail.com) on 2017/1/5.
 */
@Service
@Transactional

public class ReportServiceImpl implements ReportService {

    public static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private ReportDao reportDao;

    public Map publishreport(String token,String body,int type,Integer typeid)
    {
        User user = userDao.findByToken(token);
        Map<String,Object> m =new HashMap<>();

        if(user!=null)
        {
           // if(type.equals("post")|| type.equals("comment")||type.equals("acftivity")||type.equals("typeid"))
            if(type==0||type==1||type==2)
            {
                try {
                    String utf8body = new String(body.getBytes(),"UTF-8");
                    Report report =new Report();
                    report.setBody(body);
                    report.setType(type);
                    report.setType(typeid);
                    report.setAuthorUser(user);
                    reportDao.save(report);
                    m.put("state","successful");
                    m.put("reason","");
                    return  m;
                }
                catch (Exception e)
                {
                    logger.info(e.getMessage());
                    m.put("state","fail");
                    m.put("reason","");
                    return m;
                }

            }
            else
            {
                m.put("state","fail");
                m.put("reason","no this type report");
                return m;
            }
        }
        else
        {
            m.put("state","fail");
            m.put("reason","no user");
            return m;
        }
       //return null;
    }

}
