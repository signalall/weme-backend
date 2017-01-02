package cn.seu.weme.controller;

import cn.seu.weme.common.result.ResultInfo;
import cn.seu.weme.common.result.ResultUtil;
import cn.seu.weme.common.utils.UUIDBuilder;
import cn.seu.weme.dto.PersonImageVo;
import cn.seu.weme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by LCN on 2016-12-21.
 */
@RestController
@RequestMapping("/fileupload")
public class FileUploadController {


    @Value("${web.upload-path}")
    private String webPath;

    @Value("${thumb.image.width}")
    private int thumbImageWidth;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/uploadPersonImage", method = RequestMethod.POST)
    public ResultInfo uploadPersonImage(@RequestParam("userId") Long userId, @RequestParam("image") MultipartFile file) throws IOException {

        String uuid = UUIDBuilder.getUUID();

        String path = webPath + uuid + ".jpg";

        byte[] bytes = file.getBytes();
        BufferedOutputStream stream =
                new BufferedOutputStream(new FileOutputStream(new File(path)));
        stream.write(bytes);
        stream.close();


        AffineTransform transform = new AffineTransform();
        BufferedImage bis = ImageIO.read(new File(path));

        //获得图片原来的高宽
        int w = bis.getWidth();
        int h = bis.getHeight();

        double scale = (double) w / h;

        int nowWidth = thumbImageWidth;
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

        String thumb_url = webPath + "thumb/" + uuid + ".jpg";
        ImageIO.write(bid, "jpg", new File(thumb_url));

        PersonImageVo personImageVo = new PersonImageVo();
        personImageVo.setUrl(uuid + ".jpg");
        personImageVo.setThumbnailUrl("thumb/" + uuid + ".jpg");

        return userService.uploadImage(userId,personImageVo);
    }


    private boolean isImage(File imageFile) {
        if (!imageFile.exists()) {
            return false;
        }
        Image img = null;
        try {
            img = ImageIO.read(imageFile);
            if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            img = null;
        }
    }

}
