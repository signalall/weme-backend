package cn.seu.weme.service;

import java.util.Map;

/**
 * Created by Visen (zhvisen@gmail.com) on 2017/1/5.
 */
public interface ReportService {

    Map publishreport(String token,String body,int type,Integer typeid);

}
