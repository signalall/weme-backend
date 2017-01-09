package cn.seu.weme.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Visen (zhvisen@gmail.com) on 2017/1/6.
 */
public interface AvatarCardService {
    Map getAllUserCard(String token, Integer page, Integer number);

    Map getAllUserCardByGendder(String token,Integer page,Integer number,String gendder);

    Map setPassUserCard(String token, List<Long> usercardlist);

    Map setNoPassUserCard(String token, List<Long> usercardlist);

}
