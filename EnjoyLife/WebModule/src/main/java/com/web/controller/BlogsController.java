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

    /**
     * 获取文章列表
     * @param page
     * @return
     */
    @RequestMapping("/getBlogs.html")
    @ResponseBody
    public String list(Page<NewArticle> page){
        logger.info("进入获取文章列表方法");
//        page = blogsService.selectArticlesByPage(page);
        page = blogsService.selectArticlesByPageSolr(page);
        logger.info("退出获取文章列表方法，返回参数：" + page);
        return super.parseObjectToJson(page);
    }

    /**
     * 获取热门文章列表
     */
    @RequestMapping("/getHotsBlogs.html")
    @ResponseBody
    public String hotsList(){
        logger.info("进入获取热门文章列表方法");
        List<NewArticle> hots = blogsService.selectHotsForEight();
        logger.info("退出获取热门文章列表方法，返回结果数：" + hots.size());
        return super.parseObjectToJson(hots);
    }

    /**
     * 跳转到详情页面
     * @param sid
     * @param map
     * @return
     */
    @RequestMapping("/{sid}.html")
    public String getArticle(@PathVariable Integer sid, ModelMap map){

        logger.info("进入查询文章详情方法，文章id：" + sid);
        ArticleWithBLOBs article = blogsService.selectArticleBySID(sid);
        if(article == null){
            logger.error("查询文章详情失败，原因 【没有找到对应的文章】");
            return "redirect:/error";
        }
        logger.info("退出查询文章详情方法，返回文章标题：" + article.getArticleTitle());
        map.addAttribute("article", article);
        return "blogs/main";
    }

    /**
     * 获取主评论列表
     * @param page
     * commentIsReply = 0
     * @return
     */
    @RequestMapping("/getComment.html")
    @ResponseBody
    public String getComment(Page<Comment> page){
        logger.info("进入查询主评论列表方法， 文章id： " + page.getArticleId());
        page.setPageSize(10);
        page.setPage(page.getPage());
        page.setIsReply(Byte.valueOf("0"));
        page = commentService.selectCommentByPage(page);
        logger.info("退出查询主评论列表方法， 返回参数" + page);
        return super.parseObjectToJson(page);
    }

    /**
     * 获取回复的评论列表
     * @param form
     * commentIsReply = 1
     * @return
     */
    @RequestMapping("/getReplyComment.html")
    @ResponseBody
    public String getReplyComment(CommentForm form){
        logger.info("进入查询回复评论方法");
        List<Comment> list = commentService.selectReplyCommentByForm(form);
        Page<Comment> page = new Page<>();
        page.setResultList(list);
        logger.info("退出查询回复评论方法， 返回参数" + page);
        return super.parseObjectToJson(page);
    }

    /**
     * 保存评论
     * @param form
     * commentIsReply 0: 主评论
     * commentIsReply 1: 回复评论
     * @return
     */
    @RequestMapping("/saveComment.html")
    @ResponseBody
    public String saveComment(CommentForm form){
        logger.info("进入保存评论方法");
        try {
            commentService.saveComment(form);
            return "success";
        } catch (Exception e){
            logger.error("保存评论文章Id为： " + form.getArticleId() + "  的文章评论时发生异常，导致该评论无法添加");
            logger.error("异常原因：  " + e);
        }
        return "err";
    }
}
