package com.enjoylife.blogs.controller;

import com.enjoylife.article.vo.ArticleWithBLOBs;
import com.enjoylife.base.annotations.ToCrsf;
import com.enjoylife.base.controller.BaseController;
import com.enjoylife.comment.ICommentService;
import com.enjoylife.comment.vo.Comment;
import com.enjoylife.enums.YesNoTypeEnum;
import com.enjoylife.form.CommentForm;
import com.enjoylife.tags.ITagesService;
import com.enjoylife.tags.vo.Tags;
import com.enjoylife.type.vo.Type;
import com.enjoylife.utils.ConUtils;
import com.enjoylife.utils.SessionKeyUtils;
import com.enjoylife.view.Page;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
    private ICommentService commentService;
    @Resource
    private ITagesService tagesService;

    /**
     * 跳转到详情页面
     * @param sid
     * @param map
     * @return
     */
    @RequestMapping(value = "/{sid}", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    @ToCrsf
    public String getArticle(@PathVariable Integer sid, ModelMap map, HttpServletRequest request){

        map.addAttribute("isEyes", YesNoTypeEnum.Yes.getCode());

        ArticleWithBLOBs article = blogsService.selectArticleBySID(sid);
        if(article == null){
            logger.error("查询文章详情失败，原因 【没有找到对应id为: " + sid + " 的文章】");
            return "redirect:/error";
        }
        map.addAttribute("article", article);

        //根据文章id查询类型和标签
        Type articleType = typeService.selectTypeByArticleId(article.getArticleId());
        if(articleType != null){
            map.addAttribute("type", articleType);
        }

        //根据文章id查询标签
        List<Tags> tagses = tagesService.selectTagsByArticleId(article.getArticleId());
        map.addAttribute("tags", tagses);

        //查询总分类数和总文章数给前台展示
        super.getTotalTypesToMap(map);
        super.getTotalArticlesToMap(map);
        //将crsf的token放入页面
        map.addAttribute("ck", getSession(request).getAttribute(SessionKeyUtils.SESSION_CRSF_TOKEN));

        map.addAttribute("dataType", "blogs");

        try {
            return toPjax(request, map, "blogs");
        } catch (TemplateException | IOException e) {
            logger.error("pjax返回错误");
        }

        return "/error";
    }

    @RequestMapping(value = "/getComment.html", method = RequestMethod.POST)
    @ResponseBody
    public String getComment(Page<Comment> page){
        page = commentService.getCommentsByPage(page);
        //将结果封装成map对象，然后转为json返回给前台
        return super.parseObjectToJson(this.getCommentMapByResult(page));
    }

    @RequestMapping(value = "/postComment.html", method = RequestMethod.POST)
    @ResponseBody
    public String postComment(CommentForm form){
        try {
            return commentService.insertCommentByForm(form);
        }catch (Exception e){
            logger.error("保存文章Id为: " + form.getArticleId() + " 的评论发生异常：" + e);
        }
        return "error";
    }


    /**
     * 根据评论结果封装成map
     */
    private Map<String, Object> getCommentMapByResult(Page<Comment> page){
        Map<String, Object> map = ConUtils.hashmap();
        List<Comment> list = page.getResultList();
        Integer totalCounts = page.getTotalCounts();
        Integer totalPages = page.getTotalPages();
        Integer current = page.getPage();
        if(ConUtils.isNotNull(list)){
            map.put("isOk", "Y");
            map.put("totalCounts", totalCounts);
            map.put("totalPages", totalPages);
            map.put("current", current);
            map.put("list", list);
        }else{
            map.put("isOk", "N");
            map.put("msg", "没有数据");
        }
        return map;
    }
}
