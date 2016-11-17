package com.enjoylife.blogs.controller;

import com.enjoylife.article.vo.ArticleWithBLOBs;
import com.enjoylife.base.controller.BaseController;
import com.enjoylife.blogs.IBlogsService;
import com.enjoylife.comment.ICommentService;
import com.enjoylife.comment.vo.Comment;
import com.enjoylife.form.CommentForm;
import com.enjoylife.tags.ITagesService;
import com.enjoylife.tags.vo.Tags;
import com.enjoylife.type.ITypeService;
import com.enjoylife.type.vo.Type;
import com.enjoylife.utils.ConUtils;
import com.enjoylife.view.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    @Resource
    private ITypeService typeService;
    @Resource
    private ITagesService tagesService;

    /**
     * 跳转到详情页面
     * @param sid
     * @param map
     * @return
     */
    @RequestMapping("/{sid}.html")
    public String getArticle(@PathVariable Integer sid, ModelMap map){

        List<Type> types = typeService.selectAllTypes();
        if(ConUtils.isNotNull(types)){
            map.addAttribute("types", types);
        }

        ArticleWithBLOBs article = blogsService.selectArticleBySID(sid);
        if(article == null){
            logger.error("查询文章详情失败，原因 【没有找到对应id为: " + sid + " 的文章】");
            return "redirect:/error";
        }
        map.addAttribute("article", article);

        //根据文章id查询类型和标签
        Type articleType = typeService.selectTypeByArticleId(article.getArticleId());
        if(articleType == null){
            logger.error("查询文章详情失败，原因 【没有找到对应id为: " + sid + " 的文章类型】");
            return "redirect:/error";
        }
        map.addAttribute("type", articleType);

        //根据文章id查询标签
        List<Tags> tagses = tagesService.selectTagsByArticleId(article.getArticleId());
        map.addAttribute("tags", tagses);

        Page<Comment> page = new Page<>();
        page.setArticleId(article.getArticleId());
        page.setIsReply((byte) 0);
        page = commentService.getCommentsByPage(page);
        map.addAttribute("totalCounts", page.getTotalCounts());
        map.addAttribute("totalPages", page.getTotalPages());
        map.addAttribute("page", page.getPage());
        map.addAttribute("result", page.getResultList());

        return "blogs/index";
    }

    @RequestMapping("/getComment.html")
    @ResponseBody
    public String getComment(Page<Comment> page){
        page = commentService.getCommentsByPage(page);
        List<Comment> list = page.getResultList();
        Integer totalCounts = page.getTotalCounts();
        Integer totalPages = page.getTotalPages();
        Integer current = page.getPage();
        Map<String, Object> map = ConUtils.hashmap();
        if(ConUtils.isNotNull(list)){
            map.put("isOk", "Y");
        }else{
            map.put("isOk", "N");
        }
        map.put("totalCounts", totalCounts);
        map.put("totalPages", totalPages);
        map.put("current", current);
        map.put("list", list);
        return super.parseObjectToJson(map);
    }

    @RequestMapping("/postComment.html")
    @ResponseBody
    public String postComment(CommentForm form){
        //设置为主回复
        try {
            return commentService.insertCommentByForm(form);
        }catch (Exception e){
            logger.error("保存文章Id为: " + form.getArticleId() + " 的评论发生异常：" + e);
        }
        return "error";
    }
}
