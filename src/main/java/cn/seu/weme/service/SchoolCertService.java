package cn.seu.weme.service;

import java.util.Map;

/**
 * Created by Visen (zhvisen@gmail.com) on 2017/1/5.
 */
public interface SchoolCertService  {


    public Map publishCertification();


    public Map getAllCertifications();



    public Map setPassCertification();



    public Map setNoPassCertification();
}
