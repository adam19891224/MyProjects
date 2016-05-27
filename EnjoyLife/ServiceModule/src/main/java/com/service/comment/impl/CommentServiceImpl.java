package com.service.comment.impl;

import com.comment.dao.ArticleCommentMapper;
import com.comment.dao.CommentMapper;
import com.comment.vo.ArticleComment;
import com.comment.vo.Comment;
import com.foundation.form.CommentForm;
import com.foundation.utils.ConUtils;
import com.foundation.view.Page;
import com.service.base.BaseServiceImpl;
import com.service.comment.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/18
 */
@Service
public class CommentServiceImpl extends BaseServiceImpl implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    @Override
    public Page<Comment> selectCommentByPage(Page<Comment> page) {
        logger.info("开始查询主评论列表......");
        {
            List<Comment> list = commentMapper.selectByPage(page);
            if(list != null && list.size() > 0){
                int count = commentMapper.selectCountsByArticle(page);
                page.setTotalCounts(count);
                page.setResultList(list);
                page.setTotalPages((count + page.getPageSize() - 1) / page.getPageSize());
            }else{
                page.setResultList(ConUtils.arraylist());
            }
        }
        return page;
    }

    @Override
    public List<Comment> selectReplyCommentByForm(CommentForm form) {
        logger.info("开始查询回复评论列表......");
        return commentMapper.selectReplyComment(form);
    }

    @Transactional
    @Override
    public void saveComment(CommentForm form) {
        logger.info("开始保存评论......");
        //创建评论对象
        Comment comment = this.createCommentEntity(form);
        ArticleComment articleComment = this.createArticleCommentEntity(form, comment);
        commentMapper.insertSelective(comment);
        articleCommentMapper.insertSelective(articleComment);

    }

    private Comment createCommentEntity(CommentForm form){
        //创建评论对象
        Comment comment = new Comment();
        comment.setCommentId(UUID.randomUUID().toString());
        comment.setCommentBody(form.getCommentBody());
        comment.setCommentIsReply(form.getCommentIsReply());
        comment.setCommentUser(form.getCommentUser());
        comment.setCommentEmail(form.getCommentEmail());
        comment.setCommentUserWebsite(form.getCommentUserWebsite());
        comment.setCommentReplyUser(form.getCommentReplyUser());
        comment.setCommentReplyBody(form.getCommentReplyBody());
        comment.setCreateDate(new Date());
        comment.setUpdateDate(new Date());
        return comment;
    }

    private ArticleComment createArticleCommentEntity(CommentForm form, Comment comment){
        ArticleComment articleComment = new ArticleComment();
        articleComment.setArticleId(form.getArticleId());
        articleComment.setCommentId(comment.getCommentId());
        articleComment.setArticleCommentId(UUID.randomUUID().toString());
        return articleComment;
    }
}
