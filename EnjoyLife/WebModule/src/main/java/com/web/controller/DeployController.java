package com.web.controller;

import com.alibaba.fastjson.JSON;
import com.foundation.enums.UserEnum;
import com.foundation.form.ArticleForm;
import com.foundation.utils.FileUploadUtils;
import com.foundation.utils.MD5Utils;
import com.foundation.utils.StringUtils;
import com.foundation.view.Tree;
import com.service.blogs.IBlogsService;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/19
 */
@Controller
@RequestMapping("/deploy")
public class DeployController extends BaseController {

    @Resource
    private IBlogsService blogsService;

    @RequestMapping("/login.html")
    public String login() {
        return "deploy/login";
    }

    @RequestMapping("/main.html")
    public String main(HttpServletRequest request, String name, String password, String code){

        logger.info("用户：   " + name + " 的用户登录  ");
        String sessionCode = (String) super.getSession(request).getAttribute("code");
        if(!StringUtils.isNotNull(code) || !StringUtils.isEqual(code, sessionCode)){
            logger.error("用户：   " + name + " 的用户登录失败，原因： 验证码错误");
            return "redirect:/index.html";
        }

        String username = UserEnum.LoginUser.getName();
        String userpass = UserEnum.LoginUser.getPassword();

        if(StringUtils.isEqual(username, name) && StringUtils.isEqual(userpass, MD5Utils.toMD5(password))){
            logger.info("用户：   " + name + " 的用户登录成功");
            super.getSession(request).setAttribute("isLogin", true);
            return "deploy/main";
        }

        logger.error("用户：   " + name + " 的用户登录失败，原因： 账号校验失败");
        return "redirect:/index.html";
    }

    @RequestMapping("/getType.html")
    @ResponseBody
    public String getType(HttpServletRequest request){
        if(super.getSession(request).getAttribute("isLogin") != null && (Boolean) super.getSession(request).getAttribute("isLogin")){
            List<Tree> list = new ArrayList<>();
            Tree root = Tree.getRoot();
            list.stream().filter(t -> !StringUtils.isNotNull(t.getParentId())).forEach(t -> {
                t.setParentId(root.getId());
            });
            list.add(root);
            return JSON.toJSONString(list);
        }
        return "noLogin";
    }

    @RequestMapping("/save.html")
    @ResponseBody
    public String save(ArticleForm form, HttpServletRequest request){
        logger.info(super.getIP(request) + "  发起保存文章请求。");
        if(form.getTypeId().equals("111") || !StringUtils.isNotNull(form.getTypeId())){
            logger.error("该文章选择的类别为根类别，或者该文章没有选择类别");
            return "err";
        }
        try {
//            blogsService.saveArticle(form);
            logger.info("保存文章成功");
            return "success";
        }catch (Exception e){
            logger.error("保存文章失败，原因： ");
            logger.error(e);
        }
        return "err";
    }

    @RequestMapping("/upload.html")
    @ResponseBody
    public String upload(String topx, String topy, String imgW, String imgH, MultipartFile upload, String CKEditorFuncNum, HttpServletRequest request) {

        boolean isEditor = false;
        logger.info(super.getIP(request) + " 开启发送图片请求");
        try {
            HttpEntity reqEntity = null;

            if (StringUtils.isNotNull(topx) && StringUtils.isNotNull(topy) && StringUtils.isNotNull(imgW) && StringUtils.isNotNull(imgH)) {

                reqEntity = MultipartEntityBuilder.create()
                        .addPart("file", new InputStreamBody(upload.getInputStream(), upload.getOriginalFilename()))
                        .addPart("x", new StringBody(topx, ContentType.TEXT_PLAIN))
                        .addPart("y", new StringBody(topy, ContentType.TEXT_PLAIN))
                        .addPart("cutWidth", new StringBody(imgW, ContentType.TEXT_PLAIN))
                        .addPart("cutHeight", new StringBody(imgH, ContentType.TEXT_PLAIN))
                        .addPart("suffix", new StringBody(FileUploadUtils.getPostfix(upload.getOriginalFilename()), ContentType.TEXT_PLAIN))
                        .build();
            } else {
                reqEntity = MultipartEntityBuilder.create()
                        .addPart("file", new InputStreamBody(upload.getInputStream(), upload.getOriginalFilename()))
                        .addPart("suffix", new StringBody(FileUploadUtils.getPostfix(upload.getOriginalFilename()), ContentType.TEXT_PLAIN))
                        .build();
                isEditor = true;
            }
            String res = FileUploadUtils.WEB_URL + FileUploadUtils.getResponseString(FileUploadUtils.uploadFile(FileUploadUtils.WEB_URL + "image/upload.html", reqEntity));
            logger.info("成功发送图片，返回结果为：" + res);
            res = res.replaceAll("\\\\", "/");
            if(isEditor){
                return "<script type=\"text/javascript\">" +
                        "window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",'" + res + "','')" +
                        "</script>";
            }
            return res;
        } catch (IOException e) {
            logger.error(super.getIP(request) + " 发送图片请求失败，异常原因：");
            logger.error(e);
        }
        return "err";
    }

}
