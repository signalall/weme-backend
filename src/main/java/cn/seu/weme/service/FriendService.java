package cn.seu.weme.service;

import cn.seu.weme.common.result.ResponseInfo;

/**
 * Created by LCN on 2017-1-5.
 */
public interface FriendService {

    public ResponseInfo visit(String token, Long userId);

    public ResponseInfo getVisitInfo(Long userId);

    public ResponseInfo follow(String token, Long userId);

    public ResponseInfo unFollow(String token, Long userId);

    public ResponseInfo getFollowView(String token, int page, int direction);

    public ResponseInfo searchUser(String token, String text);

    public ResponseInfo getRecommendUser(String token, Long userId);


    public ResponseInfo getReCommendUsers(String token);

    public ResponseInfo likeUserCard(String token, Long userId);

    public ResponseInfo unLikeUserCard(String token, Long userId);

    public ResponseInfo getLikeUserNumber(String token);

}
