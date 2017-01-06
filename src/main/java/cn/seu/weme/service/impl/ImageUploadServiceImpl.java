package cn.seu.weme.service.impl;

import cn.seu.weme.common.result.ResponseInfo;
import cn.seu.weme.common.utils.MyFileUtils;
import cn.seu.weme.common.utils.UUIDBuilder;
import cn.seu.weme.dao.*;
import cn.seu.weme.dto.old.UploadFileVo;
import cn.seu.weme.entity.*;
import cn.seu.weme.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by LCN on 2017-1-6.
 */
@Service
@Transactional
public class ImageUploadServiceImpl implements ImageUploadService {

    private final static String BASE_URL = "http://218.244.147.240:80/";

    private final static String IMAGE_BASE_URL = "F:\\fileuploadtest\\";

    private final static int THUMB_IMAGE_WIDTH = 400;


    @Autowired
    private UserDao userDao;

    @Autowired
    private PostDao postDao;

    @Autowired
    private PostImageDao postImageDao;

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private TopicImageDao topicImageDao;

    @Autowired
    private UserImageDao userImageDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private CommentImageDao commentImageDao;


    private static void thumbnailImage(String source, String dest) throws IOException {

        AffineTransform transform = new AffineTransform();
        BufferedImage bis = ImageIO.read(new File(source));

        //获得图片原来的高宽
        int w = bis.getWidth();
        int h = bis.getHeight();

        double scale = (double) w / h;

        int nowWidth = THUMB_IMAGE_WIDTH;
        int nowHeight = (nowWidth * h) / w;
        if (nowHeight > nowWidth) {
            nowHeight = nowWidth;
            nowWidth = (nowHeight * w) / h;
        }

        double sx = (double) nowWidth / w;
        double sy = (double) nowHeight / h;

        transform.setToScale(sx, sy);

        AffineTransformOp ato = new AffineTransformOp(transform, null);
        BufferedImage bid = new BufferedImage(nowWidth, nowHeight,
                BufferedImage.TYPE_3BYTE_BGR);
        ato.filter(bis, bid);
        ImageIO.write(bid, "jpg", new File(dest));
    }


    @Override

    public ResponseInfo uploadFile(UploadFileVo uploadFileVo) {

        ResponseInfo responseInfo = new ResponseInfo();

        User user = userDao.findByToken(uploadFileVo.getToken());
        Long userId = user.getId();

        String dest = "";
        int type = uploadFileVo.getType();
        int number = uploadFileVo.getNumber();
        Long messageId = uploadFileVo.getMessageId();
        String srcPath = uploadFileVo.getPath();
        Long postId = uploadFileVo.getPostId();
        Long topicId = uploadFileVo.getTopicId();
        Long commentId = uploadFileVo.getCommentId();
        Long activityTopOfficialId = uploadFileVo.getTopicOfficialId();
        Long activityId = uploadFileVo.getActivityId();
        Long foodCardId = uploadFileVo.getFoodCardId();


        String uuid = UUIDBuilder.getUUID();
        String thumb_dest = "";


        try {
            switch (type) {
                case 0:
                    break;
                case 1:
                    dest = "/home/www/picture/qianshoudongda/" + userId + "-" + type + "" + number;
                    break;
                case 5:
                    dest = "/home/www/picture/yaoda/" + userId + "-" + type + "" + number;
                    break;
                case 3:
                    dest = "/home/www/picture/autumn-2/" + userId + "-" + type + "" + number;
                    break;
                case 4:
                    dest = "/home/www/picture/autumn-3/" + userId + "-" + type + "" + number;
                    break;
                case -1:
                    dest = "/home/www/background/" + userId;
                    break;
                case -2:
                    // TODO: 2017-1-6
                    break;
                case -3:
                    dest = "/home/www/message/vedio/" + messageId + "-" + number;
                    break;
                case -4:
                    //type = -4 表示上传post的图片附件
                    dest = "posts/" + postId + "_" + uuid + ".jpg";
                    MyFileUtils.copyFileUsingApacheCommonsIO(srcPath, IMAGE_BASE_URL + dest);
                    thumb_dest = "posts/thumb/" + +postId + "_" + uuid + ".jpg";
                    thumbnailImage(IMAGE_BASE_URL + dest, IMAGE_BASE_URL + thumb_dest);
                    PostImage postImage = new PostImage();
                    Post post = postDao.findOne(postId);
                    postImage.setPost(post);
                    postImage.setUrl(dest);
                    postImage.setThumbnailUrl(thumb_dest);
                    postImageDao.save(postImage);
                    break;
                case -5:
                    //type = -5 表示上传topic的附图
                    dest = "topics/" + topicId + "_" + uuid + ".jpg";
                    MyFileUtils.copyFileUsingApacheCommonsIO(srcPath, IMAGE_BASE_URL + dest);
                    thumb_dest = "topics/thumb/" + +topicId + "_" + uuid + ".jpg";
                    thumbnailImage(IMAGE_BASE_URL + dest, IMAGE_BASE_URL + thumb_dest);
                    TopicImage topicImage = new TopicImage();
                    Topic topic = topicDao.findOne(topicId);
                    topicImage.setTopic(topic);
                    topicImage.setUrl(dest);
                    topicImage.setThumbnailUrl(thumb_dest);
                    topicImageDao.save(topicImage);
                    break;
                case -6:

                    break;
                case -7:
                    //type = -7表示上传comment的图片附件
                    dest = "comments/" + commentId + "_" + uuid + ".jpg";
                    MyFileUtils.copyFileUsingApacheCommonsIO(srcPath, IMAGE_BASE_URL + dest);
                    thumb_dest = "comments/thumb/" + +commentId + "_" + uuid + ".jpg";
                    thumbnailImage(IMAGE_BASE_URL + dest, IMAGE_BASE_URL + thumb_dest);
                    CommentImage commentImage = new CommentImage();
                    Comment comment = commentDao.findOne(commentId);
                    commentImage.setComment(comment);
                    commentImage.setUrl(dest);
                    commentImage.setThumbnailUrl(thumb_dest);
                    commentImageDao.save(commentImage);
                    break;
                case -8:
                    //type = -8 表示上传activitytopofficial的图片
                    dest = "activitytopofficials/" + activityTopOfficialId + "_" + uuid + ".jpg";
                    MyFileUtils.copyFileUsingApacheCommonsIO(srcPath, IMAGE_BASE_URL + dest);
                    thumb_dest = "activitytopofficials/thumb/" + +activityTopOfficialId + "_" + uuid + ".jpg";
                    thumbnailImage(IMAGE_BASE_URL + dest, IMAGE_BASE_URL + thumb_dest);
                    break;
                case -9:
                    //type = -9 表示上传活动activity的生活照
                    dest = "activity/" + activityId + "_" + uuid + ".jpg";
                    MyFileUtils.copyFileUsingApacheCommonsIO(srcPath, IMAGE_BASE_URL + dest);
                    thumb_dest = "activity/thumb/" + +activityId + "_" + uuid + ".jpg";
                    thumbnailImage(IMAGE_BASE_URL + dest, IMAGE_BASE_URL + thumb_dest);
                    break;
                case -10:
                    //type = -10 表示上传活动海报照片
                    dest = "poster/" + activityId + "_" + uuid + ".jpg";
                    MyFileUtils.copyFileUsingApacheCommonsIO(srcPath, IMAGE_BASE_URL + dest);
                    thumb_dest = "poster/thumb/" + +activityId + "_" + uuid + ".jpg";
                    thumbnailImage(IMAGE_BASE_URL + dest, IMAGE_BASE_URL + thumb_dest);
                    break;
                case -11:
                    //type = -11 表示上传美食卡片
                    dest = "foodcards/" + foodCardId + "_" + uuid + ".jpg";
                    MyFileUtils.copyFileUsingApacheCommonsIO(srcPath, IMAGE_BASE_URL + dest);
                    thumb_dest = "foodcards/thumb/" + +foodCardId + "_" + uuid + ".jpg";
                    thumbnailImage(IMAGE_BASE_URL + dest, IMAGE_BASE_URL + thumb_dest);
                    break;
                case -12:
                    //type ==12 表示上传个人声音名片
                    break;
                case -13:
                    //type = -13 表示上传cetification的附件图片
                    break;
                case -14:
                    //type = -14表示上传commentact的图片附件
                    break;

                case -15:
                    //type = -15 表示上传更新的android APK安装包
                    break;
                case -16:
                    //type = -16 表示上传生活照
                    dest = "users/" + userId + "_" + uuid + ".jpg";
                    MyFileUtils.copyFileUsingApacheCommonsIO(srcPath, IMAGE_BASE_URL + dest);
                    thumb_dest = "users/thumb/" + +userId + "_" + uuid + ".jpg";
                    thumbnailImage(IMAGE_BASE_URL + dest, IMAGE_BASE_URL + thumb_dest);
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    break;
                case 13:
                    break;
                case 14:
                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            responseInfo.setState("fail");
            responseInfo.setReason("上传图片失败,请重传");
            return responseInfo;


        }

        responseInfo.setState("successful");
        return responseInfo;
    }
}
