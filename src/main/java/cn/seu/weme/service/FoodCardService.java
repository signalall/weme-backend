package cn.seu.weme.service;

import cn.seu.weme.common.result.ResponseInfo;

import java.util.Map;

/**
 * Created by Visen (zhvisen@gmail.com) on 2017/1/4.
 */
public interface FoodCardService {

    Map publishCard(String token,String title,String location,String longtitude ,String latitude ,Double price ,String comment);

    Map likeFoodCard(String token, long foodcardid);

    Map getFoodCard(String token);



}
