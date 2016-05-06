package com.web.controller;

import com.article.show.NewArticle;
import com.article.vo.ArticleWithBLOBs;
import com.article.vo.Comment;
import com.foundation.form.CommentForm;
import com.foundation.view.Page;
import com.service.IBlogsService;
import com.service.ICommentService;
import com.service.ISolrService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/15
 */
@Controller
@RequestMapping("/blogs")
public class BlogsController extends BaseController {

    @Resource
    private IBlogsService blogsService;
    @Resource
    private ICommentService iCommentService;
    @Resource
    private ISolrService iSolrService;

    @RequestMapping("/getBlogs.html")
    @ResponseBody
    public String list(Page<NewArticle> page){
        logger.info("进入获取文章列表方法");
        page = iSolrService.selectArticlesByPage(page);
        logger.info("退出获取文章列表方法，返回参数：" + page);
        return super.parseObjectToJson(page);
    }

    @RequestMapping("/{sid}.html")
    public String getArticle(@PathVariable Integer sid, ModelMap map){

        ArticleWithBLOBs article = blogsService.selectArticleBySID(sid);
        if(article == null){
            return "redirect:/error";
        }
        map.addAttribute("article", article);
        return "article/main";
    }

    @RequestMapping("/getComment.html")
    @ResponseBody
    public String getComment(Page<Comment> page){
        logger.info("进入获取评论方法");
        page = iCommentService.selectCommentByPage(page);
        return super.parseObjectToJson(page);
    }

    @RequestMapping("/saveComment.html")
    @ResponseBody
    public String saveComment(CommentForm form){
        try {
            iCommentService.saveComment(form);
            return "success";
        } catch (Exception e){
            logger.error("评论文章Id为： " + form.getArticleId() + "  的文章时发生异常，导致该评论无法添加");
            logger.error(e);
        }
        return "err";
    }
}
