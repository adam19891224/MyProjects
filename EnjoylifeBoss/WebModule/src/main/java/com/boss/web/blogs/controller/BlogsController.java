package com.boss.web.blogs.controller;

import com.boss.dao.blog.pojo.ArticleBossPJ;
import com.boss.foundation.entity.ArticleEntity;
import com.boss.foundation.entity.EnjoyFile;
import com.boss.foundation.entity.TagInfo;
import com.boss.foundation.entity.UserInfo;
import com.boss.foundation.view.Page;
import com.boss.service.blogs.IBlogService;
import com.boss.web.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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

    @Resource
    private IBlogService blogService;

    private static final String URL = "http://localhost:10086/";

    @RequestMapping("/list.html")
    public String list(ModelMap map, Page<ArticleBossPJ> page){

        return "blogs/list";
    }

    @RequestMapping("/datas.html")
    @ResponseBody
    public String getData(Page<ArticleBossPJ> page){
        page = blogService.selectArticleByPage(page);
        return super.castPageToResultString(page);
    }

    @RequestMapping("/index.html")
    public String index(ModelMap map){

        UserInfo userInfo = getUser();
        if(userInfo == null){
            return "redirect:/login.html";
        }

        map.addAttribute("userSimpleName", userInfo.getUserName());

        return "blogs/index";
    }

    @RequestMapping("/save.html")
    @ResponseBody
    public String save(ArticleEntity entity, ModelMap map){

        return blogService.saveBlog(entity);
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
            EnjoyFile file = new EnjoyFile();
            file.setFile(upload);
            String reponse = blogService.toUploadFile(file);
            if(reponse.equalsIgnoreCase("exception")){
                res = "<script type=\"text/javascript\">" +
                        "window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",''," + "'上传发生异常，请查询日志');" +
                        "</script>";
            }else if(reponse.equalsIgnoreCase("fail")){
                res = "<script type=\"text/javascript\">" +
                        "window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",''," + "'不知道什么原因，上传失败');" +
                        "</script>";
            }else if(reponse.equalsIgnoreCase("notAllow")){
                res = "<script type=\"text/javascript\">" +
                        "window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",''," + "'文件在传播途中可能被篡改，签名未通过');" +
                        "</script>";
            }else if(reponse.equalsIgnoreCase("null")){
                res = "<script type=\"text/javascript\">" +
                        "window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",''," + "'文件没有内容');" +
                        "</script>";
            }else{
                res = (URL + reponse).replaceAll("\\\\", "/");
                res = "<script type=\"text/javascript\">" +
                        "window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",'" + res + "','')" +
                        "</script>";
            }
        }

        return res;
    }

    @RequestMapping("/uploadTag.html")
    @ResponseBody
    public String uploadTag(MultipartFile file, String topx, String topy, String imgW, String imgH){

        EnjoyFile enjoyFile = new EnjoyFile();
        enjoyFile.setCheckTag(true);
        TagInfo info = new TagInfo();
        info.setX(topx);
        info.setY(topy);
        info.setCutWidth(imgW);
        info.setCutHeight(imgH);
        enjoyFile.setTag(info);
        enjoyFile.setFile(file);
        return blogService.toUploadFile(enjoyFile);
    }

    @RequestMapping("/refresh.html")
    @ResponseBody
    public String refresh(){

        boolean res = blogService.refresh();
        return res ? "success" : "error";
    }
}
