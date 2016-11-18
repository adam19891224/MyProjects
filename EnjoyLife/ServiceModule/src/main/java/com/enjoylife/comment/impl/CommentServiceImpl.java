package com.enjoylife.comment.impl;

import com.enjoylife.article.dao.ArticleMapper;
import com.enjoylife.base.BaseAbstractClass;
import com.enjoylife.comment.ICommentService;
import com.enjoylife.comment.dao.ArticleCommentMapper;
import com.enjoylife.comment.dao.CommentMapper;
import com.enjoylife.comment.vo.ArticleComment;
import com.enjoylife.comment.vo.Comment;
import com.enjoylife.form.CommentForm;
import com.enjoylife.utils.ConUtils;
import com.enjoylife.utils.StringUtils;
import com.enjoylife.view.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/18
 */
@Service
public class CommentServiceImpl extends BaseAbstractClass implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ArticleCommentMapper articleCommentMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Page<Comment> getCommentsByPage(Page<Comment> page) {
        {
            Date start = new Date();
            List<Comment> list = commentMapper.selectCommentsByPage(page);
            if(ConUtils.isNotNull(list)){
                page.setResultList(list);
                if(page.getTotalCounts() == null){
                    int count = commentMapper.selectCommentsCountsByPage(page);
                    page.setTotalCounts(count);
                    //总页数计算方法：总记录数 + 每页显示记录数 - 1 的结果 / 每页显示记录数
                    page.setTotalPages((count + page.getPageSize() - 1) / page.getPageSize());
                }
            }else{
                page.setResultList(ConUtils.arraylist());
            }
            Date end = new Date();
            logger.info("【查询文章评论集合结束】，当前页数: " + page.getPage() + "，总共耗时： " + super.getMsBetweenTwoDate(start, end) + " ms");
        }
        return page;
    }

    @Transactional
    @Override
    public String insertCommentByForm(CommentForm form) {
        Comment comment = new Comment();
        String articleID = form.getArticleId();
        String articleTitle = articleMapper.selectArticleInfoByArticleID(articleID);
        if(!StringUtils.isNotNull(articleTitle)){
            return "没有对应的文章";
        }
        BeanUtils.copyProperties(form, comment);
        String commentID = UUID.randomUUID().toString();
        comment.setCommentId(commentID);
        comment.setCommentEmail(HtmlUtils.htmlEscape(comment.getCommentEmail()));
        comment.setCommentUser(HtmlUtils.htmlEscape(comment.getCommentUser()));
        comment.setCommentUserWebsite(HtmlUtils.htmlEscape(comment.getCommentUserWebsite()));
        //创建文章，评论关联关系
        ArticleComment articleComment = new ArticleComment();
        articleComment.setArticleId(articleID);
        articleComment.setCommentId(commentID);

        articleCommentMapper.insertSelective(articleComment);
        commentMapper.insertSelective(comment);

        return "success";
    }
}
