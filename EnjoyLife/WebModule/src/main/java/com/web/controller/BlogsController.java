package com.web.controller;

import com.article.vo.NewArticle;
import com.article.vo.ArticleWithBLOBs;
import com.comment.vo.Comment;
import com.foundation.form.CommentForm;
import com.foundation.view.Page;
import com.service.blogs.IBlogsService;
import com.service.comment.ICommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

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
    private ICommentService commentService;

    @RequestMapping("/getBlogs.html")
    @ResponseBody
    public String list(Page<NewArticle> page){
        logger.info("进入获取文章列表方法");
        page = blogsService.selectArticlesByPage(page);
        logger.info("退出获取文章列表方法，返回参数：" + page);
        return super.parseObjectToJson(page);
    }

    @RequestMapping("/{sid}.html")
    public String getArticle(@PathVariable Integer sid, ModelMap map){

        logger.info("进入获取文章详情方法，文章id：" + sid);
        ArticleWithBLOBs article = blogsService.selectArticleBySID(sid);
        if(article == null){
            logger.error("查询文章详情失败，原因 【没有找到对应的文章】");
            return "redirect:/error";
        }
        map.addAttribute("article", article);
        return "blogs/main";
    }

    @RequestMapping("/getComment.html")
    @ResponseBody
    public String getComment(Page<Comment> page){
        logger.info("进入获取评论方法");
        page = commentService.selectCommentByPage(page);
        return super.parseObjectToJson(page);
    }

    @RequestMapping("/getReplyComment.html")
    @ResponseBody
    public String getReplyComment(CommentForm form){
        logger.info("进入获取回复评论方法");
        List<Comment> list = commentService.selectReplyCommentByForm(form);
        Page<Comment> page = new Page<>();
        page.setResultList(list);
        return super.parseObjectToJson(page);
    }

    @RequestMapping("/saveComment.html")
    @ResponseBody
    public String saveComment(CommentForm form){
        try {
            commentService.saveComment(form);
            return "success";
        } catch (Exception e){
            logger.error("评论文章Id为： " + form.getArticleId() + "  的文章时发生异常，导致该评论无法添加");
            logger.error(e);
        }
        return "err";
    }
}
