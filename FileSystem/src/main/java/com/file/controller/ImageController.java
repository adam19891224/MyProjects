package com.file.controller;

import com.file.entity.image.CutImageFile;
import com.file.util.FileUploadUtils;
import com.file.util.IPUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2015/12/30
 */
@Controller
@RequestMapping("/images")
public class ImageController extends BaseController{

    private Logger logger = Logger.getLogger(this.getClass());

    @Value("${image.save.path}")
    private String savePath;

    @RequestMapping(value = "/upload.html", method = RequestMethod.POST)
    @ResponseBody
    public String upload(CutImageFile file, HttpServletRequest request){
        logger.info("来自： " + IPUtils.getClientIp(request) + "   请求文件上传。");
        if(file.getFile() == null){
            logger.warn("来自： " + IPUtils.getClientIp(request) + " 请求文件上传失败， 原因： 【没有图片】。");
            return "null";
        }

        if(FileUploadUtils.checkFileIsAllow(file)){
            String path = FileUploadUtils.getSavePath(file);
            String imagePath = savePath + path;

            FileUploadUtils.getFixedThreadPool().execute(() -> FileUploadUtils.upload(file, imagePath));

            return path;
        }
        return "notAllow";
    }

}
