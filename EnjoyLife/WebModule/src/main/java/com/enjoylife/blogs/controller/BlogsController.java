package com.enjoylife.blogs.controller;

import com.enjoylife.article.vo.ArticleWithBLOBs;
import com.enjoylife.base.controller.BaseController;
import com.enjoylife.blogs.IBlogsService;
import com.enjoylife.comment.ICommentService;
import com.enjoylife.comment.vo.Comment;
import com.enjoylife.form.CommentForm;
import com.enjoylife.utils.StringUtils;
import com.enjoylife.view.Page;
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
     * 跳转到详情页面
     * @param sid
     * @param map
     * @return
     */
    @RequestMapping("/{sid}.html")
    public String getArticle(@PathVariable Integer sid, ModelMap map){

        ArticleWithBLOBs article = blogsService.selectArticleBySID(sid);
        if(article == null){
            logger.error("查询文章详情失败，原因 【没有找到对应id为: " + sid + " 的文章】");
            return "redirect:/error";
        }
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
        page.setPageSize(10);
        page.setPage(page.getPage());
        page.setIsReply(Byte.valueOf("0"));
        page = commentService.selectCommentByPage(page);
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
        List<Comment> list = commentService.selectReplyCommentByForm(form);
        Page<Comment> page = new Page<>();
        page.setResultList(list);
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
        try {
            if(!StringUtils.isNotNull(form.getCommentUser()) || !StringUtils.isNotNull(form.getCommentEmail())){
                return "err";
            }
            commentService.saveComment(form);
            return "success";
        } catch (Exception e){
            logger.error("保存评论文章Id为： " + form.getArticleId() + "  的文章评论时发生异常，导致该评论无法添加");
            logger.error("异常原因：  " + e);
        }
        return "err";
    }
}
