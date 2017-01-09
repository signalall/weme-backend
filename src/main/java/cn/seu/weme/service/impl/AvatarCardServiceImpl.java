package cn.seu.weme.service.impl;

import cn.seu.weme.dao.AvatarVoiceDao;
import cn.seu.weme.dao.UserDao;
import cn.seu.weme.entity.AvatarVoice;
import cn.seu.weme.entity.User;
import cn.seu.weme.service.AvatarCardService;
import com.alibaba.druid.sql.ast.statement.SQLIfStatement;
import net.sf.ehcache.CacheOperationOutcomes;
import net.sf.ehcache.search.expression.LessThan;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Visen (zhvisen@gmail.com) on 2017/1/6.
 */
@Service
@Transactional
public class AvatarCardServiceImpl implements AvatarCardService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AvatarVoiceDao avatarVoiceDao;


    private PageRequest buildPageResult(int pageNumber,int pageSize){
        return new PageRequest(pageNumber-1,pageSize,null);
    }

    private String ifNullReturnEmpty(Object object){
        if(object==null)
            return "";
        else
            return object.toString();
    }

    @Override
    public Map getAllUserCard(String token, Integer page, Integer number)
    {
        Map<String,Object> result = new HashMap<>();

        User user = userDao.findByToken(token);
        if (user==null && user.getUsername()=="administrator")
        {
            PageRequest request = this.buildPageResult(page,number);


            /////////！！！！！需要注意排序  保证用户enable
            Page<AvatarVoice> page1=this.avatarVoiceDao.findAll(request);

            //int totalNumber = page1.getTotalPages();
            List<AvatarVoice> avatarVoiceList = page1.getContent();
            int pagesize = page1.getSize();
            List<JSONObject> jsonObjectList =new ArrayList<>();
            for (AvatarVoice temp:avatarVoiceList)
            {
                if (temp.getDisable()==true)
                {
                    continue;
                }
                JSONObject tempjson = new JSONObject();
                tempjson.put("id",temp.getId());
                tempjson.put("userid",temp.getUser().getId());
                if(temp.getAvatarUrl()!=null)
                    tempjson.put("avatarurl",temp.getAvatarUrl()+"_card.jpg");
                else
                    tempjson.put("avatarurl","");
                tempjson.put("voiceurl",ifNullReturnEmpty(temp.getVoiceUrl()));
                tempjson.put("gender",ifNullReturnEmpty(temp.getGender()));
                tempjson.put("cardflag",ifNullReturnEmpty(temp.getCardFlag()));
                tempjson.put("disable",ifNullReturnEmpty(temp.getDisable()));
                tempjson.put("name",ifNullReturnEmpty(temp.getName()));
                jsonObjectList.add(tempjson);

            }

            result.put("state","successful");
            result.put("reason","");
            result.put("result",jsonObjectList);
            result.put("pages",pagesize);
            return result;
        }
        else
        {
            result.put("state","successful");
            result.put("reason","");
            result.put("result","");

            return result;
        }
    }




    @Override
    public Map getAllUserCardByGendder(String token,Integer page,Integer number,String gendder){
        Map<String,Object> result = new HashMap<>();

        User user = userDao.findByToken(token);
        if (user==null && user.getUsername()=="administrator")
        {
            PageRequest request = this.buildPageResult(page,number);


            /////////！！！！！需要注意排序  保证用户enable
            Page<AvatarVoice> page1=this.avatarVoiceDao.findByGender(request,gendder);

            //int totalNumber = page1.getTotalPages();
            List<AvatarVoice> avatarVoiceList = page1.getContent();
            int pagesize = page1.getSize();
            List<JSONObject> jsonObjectList =new ArrayList<>();
            for (AvatarVoice temp:avatarVoiceList)
            {
                if (temp.getDisable()==true)
                {
                    continue;
                }
                JSONObject tempjson = new JSONObject();
                tempjson.put("id",temp.getId());
                tempjson.put("userid",temp.getUser().getId());
                if(temp.getAvatarUrl()!=null)
                    tempjson.put("avatarurl",temp.getAvatarUrl()+"_card.jpg");
                else
                    tempjson.put("avatarurl","");
                tempjson.put("voiceurl",ifNullReturnEmpty(temp.getVoiceUrl()));
                tempjson.put("gender",ifNullReturnEmpty(temp.getGender()));
                tempjson.put("cardflag",ifNullReturnEmpty(temp.getCardFlag()));
                tempjson.put("disable",ifNullReturnEmpty(temp.getDisable()));
                tempjson.put("name",ifNullReturnEmpty(temp.getName()));
                jsonObjectList.add(tempjson);

            }

            result.put("state","successful");
            result.put("reason","");
            result.put("result",jsonObjectList);
            result.put("pages",pagesize);
            return result;
        }
        else
        {
            result.put("state","fail");
            result.put("reason","非法用户");
            result.put("result","");

            return result;
        }
    }


    @Override
    public Map setPassUserCard(String token, List<Long> usercardlist){
        Map<String ,Object> result =  new HashMap<>();

        User user =userDao.findByToken(token);
        if(user!=null && user.getUsername()=="administrator"){
            for (Long tempusercardid :usercardlist){
                AvatarVoice tempavator = avatarVoiceDao.findOne(tempusercardid);
                if (tempavator!=null)
                    tempavator.setDisable(false);
                    avatarVoiceDao.save(tempavator);
            }
            result.put("state","successful");
            result.put("reason","");
            return result;
        }
        else
        {
            result.put("state","fail");
            result.put("reason","非法用户");
            return result;
        }


    }


    @Override
    public Map setNoPassUserCard(String token, List<Long> usercardlist){
        Map<String ,Object> result =  new HashMap<>();

        User user =userDao.findByToken(token);
        if(user!=null && user.getUsername()=="administrator"){
            for (Long tempusercardid :usercardlist){
                AvatarVoice tempavator = avatarVoiceDao.findOne(tempusercardid);
                if (tempavator!=null)
                    tempavator.setDisable(true);
                avatarVoiceDao.save(tempavator);
            }
            result.put("state","successful");
            result.put("reason","");
            return result;
        }
        else
        {
            result.put("state","fail");
            result.put("reason","非法用户");
            return result;
        }


    }




}
