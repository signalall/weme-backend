package cn.seu.weme.service.impl;

import cn.seu.weme.common.result.ResponseInfo;
import cn.seu.weme.common.utils.WEMEGlobalParams;
import cn.seu.weme.controller.old.FoodCardController;
import cn.seu.weme.dao.FoodCardDao;
import cn.seu.weme.dao.LikeFoodCardDao;
import cn.seu.weme.dao.UserDao;
import cn.seu.weme.entity.FoodCard;
import cn.seu.weme.entity.LikeFoodCard;
import cn.seu.weme.entity.User;
import cn.seu.weme.service.FoodCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.cert.CertPathValidatorException;
import java.util.*;

/**
 * Created by Visen (zhvisen@gmail.com) on 2017/1/4.
 */
@Service
@Transactional
public class FoodCardServiceImpl implements FoodCardService {

    public static final Logger logger = LoggerFactory.getLogger(FoodCardController.class);

    @Autowired
    private FoodCardDao foodCardDao;

    @Autowired
    private LikeFoodCardDao likeFoodCardDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Map publishCard(String token,String title,String location,String longtitude ,String latitude ,Double price ,String comment)
    {
        Map<String,Object> result = new HashMap<>();
        User user =userDao.findByToken(token);
        if (user == null) {
            result.put("state","fail");
            result.put("reason","no user");
            return result;
            //return new ResponseInfo("fail","no user");
        }
        else
        {
            user.setWeme(user.getWeme()+ WEMEGlobalParams.getWEMEPUBLISHFOODCARD());
            userDao.save(user);
            try {
                String strcomment = new String(comment.getBytes(),"UTF-8");
                FoodCard foodcard =new FoodCard();
                foodcard.setTitle(title);
                foodcard.setLocation(location);
                foodcard.setLongitude(longtitude);
                foodcard.setLatitude(latitude);
                foodcard.setPrice(price.toString());
                foodcard.setComment(strcomment);

                foodcard.setAuthor(user);

                foodCardDao.save(foodcard);
                long id = foodcard.getId();
                result.put("state","successful");
                result.put("reason","");
                result.put("id",id);
                return result;
                //return new ResponseInfo("successful","",id);


            }
            catch (Exception e)
            {
                logger.info(e.getMessage());
                //return new ResponseInfo("fail","exception");
                result.put("state","fail");
                result.put("reason","exception");
                return result;
            }

        }

    }

    @Override
    public Map likeFoodCard(String token,long foodcardid)
    {
        User user = userDao.findByToken(token);
        Map<String,Object> m =new HashMap<>();
        if (user==null)
        {

            m.put("state","fail");
            m.put("reason","no user");
            return m;
        }
        else
        {
            FoodCard foodcard = foodCardDao.findOne(foodcardid);
            LikeFoodCard likeFoodCard=likeFoodCardDao.findOne(foodcard.getId());
            if (likeFoodCard==null)
            {
                likeFoodCard = new LikeFoodCard();
                likeFoodCard.setUser(user);
                likeFoodCard.setFoodCard(foodcard);
                likeFoodCardDao.save(likeFoodCard);
                user.setWeme(user.getWeme()+WEMEGlobalParams.getWEMELIKE());
                userDao.save(user);

                int likeNumber = foodcard.getLikeFoodCards().size();

                m.put("state","successful");
                m.put("reason", "");
                m.put("likenumber",likeNumber);
                return m;

            }
            else
            {

                m.put("state","fail");
                m.put("reason", "already like");
                return m;

            }
        }
    }

    @Override
    public Map getFoodCard(String token)
    {
        User user=  userDao.findByToken(token);
        Map<String,Object> m = new HashMap<>();
        if(user==null)
        {
            List<FoodCard> foodCard = foodCardDao.findActivityByPassFlagTrue();
            Random random = new Random();
            List<FoodCard> foodresult = new ArrayList<>();
            for (int i =0;i<Math.min(10,foodCard.size());i++)
            {
               int index= random.nextInt(foodCard.size());
                if(foodresult.contains(foodCard.get(index)))
                {
                    i--;
                    continue;
                }
                else
                    foodresult.add(foodCard.get(index));
            }
            if(foodCard.size()>0)
            {
                m.put("state","successful");
                m.put("reason","");
                m.put("result",foodresult);
                return m;
            }
            else
            {
                m.put("result","");
                m.put("state","fail");
                m.put("reason","no card");
                return m;
            }


        }
        else
        {
            m.put("result","");
            m.put("state","fail");
            m.put("reason","no user");
            return m;
        }


    }


}
