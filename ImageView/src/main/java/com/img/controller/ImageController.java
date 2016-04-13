package com.img.controller;

import com.img.entity.image.CutImageFile;
import com.img.util.FileUploadUtils;
import com.img.util.IPUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2015/12/30
 */
@Controller
@RequestMapping("/image")
public class ImageController {

    private Logger logger = Logger.getLogger(this.getClass());

    @RequestMapping("/upload.html")
    @ResponseBody
    public String upload(CutImageFile file, HttpServletRequest request){
        logger.info("来自： " + IPUtils.getClientIp(request) + "   请求文件上传。");
        if(file.getFile() == null){
            logger.warn("来自： " + IPUtils.getClientIp(request) + " 请求文件上传失败， 原因： 【没有图片】。");
            return "null";
        }
        String suffix = file.getSuffix();
        if(suffix == null){
            logger.warn("来自： " + IPUtils.getClientIp(request) + " 请求文件上传失败， 原因： 【没有后缀】。");
            return "null";
        }
        if(FileUploadUtils.isImg(suffix)){
            return FileUploadUtils.upload(file, request);
        }
        logger.warn("来自： " + IPUtils.getClientIp(request) + " 请求文件上传失败， 原因： 【文件不是有效图片】。");
        return "null";
    }

}
