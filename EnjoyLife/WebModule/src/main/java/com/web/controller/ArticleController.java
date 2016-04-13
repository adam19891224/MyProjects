package com.web.controller;

import com.article.vo.ArticleWithBLOBs;
import com.article.vo.Comment;
import com.foundation.form.CommentForm;
import com.foundation.view.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/15
 */
@Controller
@RequestMapping("/articles")
public class ArticleController extends BaseController {

    @RequestMapping("/list.html")
    @ResponseBody
    public String list(Page<ArticleWithBLOBs> page){
        page = iEnjoyService.selectArticlesByPage(page);
        return super.parseObjectToJson(page);
    }

    @RequestMapping("/{sid}.html")
    public String getArticle(@PathVariable Integer sid, ModelMap map){

        ArticleWithBLOBs article = iEnjoyService.selectArticleBySID(sid);
        if(article == null){
            return "redirect:/error";
        }
        map.addAttribute("article", article);
        return "article/main";
    }

    @RequestMapping("/getComment.html")
    @ResponseBody
    public String getComment(Page<Comment> page){
        page = iEnjoyService.selectCommentByPage(page);
        return super.parseObjectToJson(page);
    }

    @RequestMapping("/saveComment.html")
    @ResponseBody
    public String saveComment(CommentForm form){
        try {
            iEnjoyService.saveComment(form);
            return "success";
        } catch (Exception e){
            logger.error("评论文章Id为： " + form.getArticleId() + "  的文章时发生异常");
            logger.error(e);
        }
        return "err";
    }
}
