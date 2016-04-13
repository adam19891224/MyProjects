package com.article.domain;

import com.article.dao.CommentMapper;
import com.article.vo.Comment;
import com.foundation.form.CommentForm;
import com.foundation.utils.StringUtils;
import com.foundation.view.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/25
 */
@Component
public class CommentDomain {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ArticleCommentDomain articleCommentDomain;


    public Page<Comment> selectByPage(Page<Comment> page){
        {
            List<Comment> list = commentMapper.selectByPage(page);
            if(list != null && list.size() > 0){
                page.setPage(page.getPage() + 1);
            }
            page.setTotalCounts(commentMapper.selectCountsByArticle(page.getArticleId()));
            page.setResultList(list);
        }
        return page;
    }

    public Integer selectCountsByArticle(String articleId){
        return commentMapper.selectCountsByArticle(articleId);
    }

    @Transactional
    public void saveComment(CommentForm form){

        //创建评论对象
        Comment comment = new Comment();
        comment.setCommentId(UUID.randomUUID().toString());
        comment.setCommentBody(HtmlUtils.htmlEscape(form.getCommentBody()));
        comment.setCommentBody(comment.getCommentBody().replace("\n","<br/>"));
        comment.setCommentIsReply(form.getCommentIsReply());
        comment.setCommentUser(form.getCommentUser());
        comment.setCommentEmail(form.getCommentEmail());
        comment.setCommentUserWebsite(form.getCommentUserWebsite());
        if(StringUtils.isNotNull(form.getCommentReplyUser()) && StringUtils.isNotNull(form.getCommentReplyBody())){
            Byte b = 1;
            comment.setCommentIsReply(b);
            comment.setCommentReplyUser(form.getCommentReplyUser());
            form.setCommentReplyBody(form.getCommentReplyBody().replace("<br>","\n"));
            comment.setCommentReplyBody(HtmlUtils.htmlEscape(HtmlUtils.htmlUnescape(form.getCommentReplyBody())));
            //替换掉换行符
            comment.setCommentReplyBody(comment.getCommentReplyBody().replace("\n","<br/>"));
        }
        comment.setCreateDate(new Date());
        comment.setUpdateDate(new Date());

        commentMapper.insertSelective(comment);

        articleCommentDomain.save(form.getArticleId(), comment.getCommentId());

    }

}
