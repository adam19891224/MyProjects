package com.enjoylife.comment.impl;

import com.enjoylife.comment.dao.ArticleCommentMapper;
import com.enjoylife.comment.dao.CommentMapper;
import com.enjoylife.comment.vo.ArticleComment;
import com.enjoylife.comment.vo.Comment;
import com.enjoylife.form.CommentForm;
import com.enjoylife.utils.ConUtils;
import com.enjoylife.view.Page;
import com.enjoylife.base.BaseAbstractClass;
import com.enjoylife.comment.ICommentService;
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
public class CommentServiceImpl extends BaseAbstractClass implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    @Override
    public Page<Comment> selectCommentByPage(Page<Comment> page) {
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
        return commentMapper.selectReplyComment(form);
    }

    @Transactional
    @Override
    public void saveComment(CommentForm form) {
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
