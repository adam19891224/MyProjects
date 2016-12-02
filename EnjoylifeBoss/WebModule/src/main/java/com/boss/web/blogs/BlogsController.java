package com.boss.web.blogs;

import com.boss.foundation.entity.UserInfo;
import com.boss.web.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * ranmin-zhouyuhong
 * 2016/12/2
 */
@Controller
@RequestMapping("/blogs")
public class BlogsController extends BaseController {

    @RequestMapping("/index.html")
    public String index(ModelMap map){

        UserInfo userInfo = getUser();
        if(userInfo == null){
            return "redirect:/login.html";
        }

        map.addAttribute("userSimpleName", userInfo.getUserName());

        return "blogs/index";
    }

    @RequestMapping("/upload.html")
    @ResponseBody
    public String upload(MultipartFile upload, String CKEditorFuncNum){

        long size = upload.getSize();

        String res = "<script type=\"text/javascript\">" +
                        "window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",''," + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');" +
                     "</script>";
        if(upload.getSize() < 1){
            res = "<script type=\"text/javascript\">" +
                    "window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",''," + "'没有找到上传的文件');" +
                  "</script>";
        }
        String fileName = upload.getOriginalFilename();
        String type = fileName.substring(fileName.lastIndexOf("."), fileName.length()).toLowerCase();
        Set<String> set = new HashSet<>(Arrays.asList(IMG_TYPE));
        if(set.contains(type)){

        }

        return res;
    }

}
