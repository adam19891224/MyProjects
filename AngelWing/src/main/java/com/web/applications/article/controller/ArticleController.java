package com.web.applications.article.controller;

import com.alibaba.fastjson.JSON;
import com.system.webui.Page;
import com.system.webui.Tree;
import com.tools.utils.UploadUtils;
import com.web.applications.article.entity.ArticleWithBLOBs;
import com.web.applications.article.form.ArticleForm;
import com.web.applications.article.service.ArticleService;
import com.web.applications.article.service.TypeService;
import com.web.base.controller.BaseController;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: adam
 * Date: 2015/11/13
 */
@Controller
@RequestMapping("/applications/article")
public class ArticleController extends BaseController {

    @Resource
    private ArticleService articleService;
    @Resource
    private TypeService typeService;

    private Page<ArticleWithBLOBs> pages = new Page<ArticleWithBLOBs>();

    @RequestMapping("/list")
    public String list() {
        return "applications/article/list";
    }

    @RequestMapping("/add")
    public String add() {
        return "applications/article/add";
    }

    @RequestMapping("/getType")
    @ResponseBody
    public Object getType() {
        List<Tree> list = typeService.selectToTree();
        return JSON.toJSON(list);
    }

    @RequestMapping("/uploadFile.html")
    @ResponseBody
    public String uploadFile(String topx, String topy, String imgW, String imgH, MultipartFile file) {

        //转换multipartFile为file
        DiskFileItem fi = (DiskFileItem) ((CommonsMultipartFile) file).getFileItem();
        File uploadFile = fi.getStoreLocation();

        HttpEntity reqEntity = MultipartEntityBuilder.create()
                .addPart("file", new FileBody(uploadFile))
                .addPart("x", new StringBody(topx, ContentType.TEXT_PLAIN))
                .addPart("y", new StringBody(topy, ContentType.TEXT_PLAIN))
                .addPart("cutWidth", new StringBody(imgW, ContentType.TEXT_PLAIN))
                .addPart("cutHeight", new StringBody(imgH, ContentType.TEXT_PLAIN))
                .addPart("suffix", new StringBody(UploadUtils.getPostfix(file.getOriginalFilename()), ContentType.TEXT_PLAIN))
                .build();

        return UploadUtils.getResponseString(UploadUtils.uploadFile(UploadUtils.WEB_URL + "image/upload.html", reqEntity));
    }

    @RequestMapping("/save.html")
    @ResponseBody
    public String save(ArticleForm form) {

        if (articleService.saveArticleByForm(form))
            return "success";
        return "false";
    }

    @RequestMapping("/getJson")
    @ResponseBody
    public Object getDatas(Integer page, Integer rows) {
        pages.setPageSize(rows);
        pages.setPageNumber(page);
        pages = super.getPage(pages, articleService);
        return JSON.toJSON(super.formatMap(pages));
    }

}
