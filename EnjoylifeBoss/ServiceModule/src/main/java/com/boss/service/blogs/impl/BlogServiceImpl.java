package com.boss.service.blogs.impl;

import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.model.PutObjectResponse;
import com.boss.dao.blog.mapper.ArticleMapper;
import com.boss.dao.blog.pojo.Article;
import com.boss.dao.blog.pojo.ArticleBossPJ;
import com.boss.dao.blog.pojo.ArticlePutObj;
import com.boss.dao.blog.pojo.ArticleWithBLOBs;
import com.boss.dao.comment.mapper.CommentMapper;
import com.boss.dao.series.mapper.SeriesMapper;
import com.boss.dao.tags.mapper.TagsMapper;
import com.boss.dao.tags.pojo.ArticleTags;
import com.boss.dao.types.mapper.TypeMapper;
import com.boss.foundation.entity.ArticleEntity;
import com.boss.foundation.entity.EnjoyFile;
import com.boss.foundation.entity.TagInfo;
import com.boss.foundation.modules.ArticleESEntity;
import com.boss.foundation.utils.ConUtils;
import com.boss.foundation.utils.StringUtils;
import com.boss.foundation.view.Page;
import com.boss.service.base.AbstractService;
import com.boss.service.blogs.IBlogService;
import com.boss.service.blogs.repository.IBlogRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


/**
 * ranmin-zhouyuhong
 * 2016/12/5
 */
@Service
public class BlogServiceImpl extends AbstractService implements IBlogService {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private IBlogRepository blogRepository;
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private TypeMapper typeMapper;
    @Resource
    private TagsMapper tagsMapper;
    @Resource
    private SeriesMapper seriesMapper;
    @Resource
    private BosClient bosClient;

    @Value("${image.cdn.host}")
    private String cdnHost;
    @Value("${baidu.bucketname}")
    private String bucketName;

    @Override
    public String toUploadFile(EnjoyFile file) {
        String response = null;
        MultipartFile multipartFile = file.getFile();
        try {
            //上传列表标记图片
            if (file.isCheckTag()) {
                response = this.uploadTagFile(multipartFile, file.getTag());
            }
            //上传文章内容图片
            if (!file.isCheckTag()) {
                response = this.uploadImageFile(multipartFile);
            }
        } catch (IOException e) {
            logger.error("上传图片发生异常", e);
            return "exception";
        }

        return response;
    }

    @Override
    @Transactional
    public String saveBlog(ArticleEntity entity) {
        if(!StringUtils.isNotBlank(entity.getArticleTitle())){
            return "没有标题";
        }
        if(!StringUtils.isNotBlank(entity.getArticleImg())){
            return "没有标志图片";
        }
        if(!StringUtils.isNotBlank(entity.getArticleDescription())){
            return "没有描述";
        }
        if(!StringUtils.isNotBlank(entity.getArticleBody())){
            return "没有内容";
        }

        ArticleWithBLOBs articleWithBLOBs = new ArticleWithBLOBs();
        String uuid = UUID.randomUUID().toString();
        articleWithBLOBs.setArticleId(uuid);
        articleWithBLOBs.setArticleTitle(entity.getArticleTitle());
        articleWithBLOBs.setArticleImg(entity.getArticleImg());
        articleWithBLOBs.setArticleDescription(entity.getArticleDescription());
        articleWithBLOBs.setArticleBody(entity.getArticleBody());

        articleMapper.insertSelective(articleWithBLOBs);

        ArticleESEntity esEntity = articleMapper.selectByArticleId(uuid);
        blogRepository.save(esEntity);

        return "success";
    }

    @Override
    @Transactional
    public String updateBlog(ArticleWithBLOBs entity) {

        if(StringUtils.isNotBlank(entity.getArticleImg())){
            entity.setArticleImg(entity.getArticleImg());
        }
        articleMapper.updateByPrimaryKeySelective(entity);
        ArticleWithBLOBs temp = articleMapper.selectByPrimaryKey(entity.getArticleSid());

        ArticleESEntity esEntity = new ArticleESEntity();
        BeanUtils.copyProperties(temp, esEntity);
        blogRepository.save(esEntity);

        return "success";
    }

    @Override
    @Transactional
    public String deleteBlog(Integer sid) {
        ArticleWithBLOBs article = articleMapper.selectByPrimaryKey(sid);
        if(article == null){
            return "null";
        }
        //删除该文章的所有评论
        commentMapper.deleteCommentsByArticleId(article.getArticleId());
        //删除该文章的所有类型
        typeMapper.deleteTypesByArticleId(article.getArticleId());
        //删除该文章的所有标签
        tagsMapper.deleteTagsByArticleId(article.getArticleId());
        //删除该文章的系列所属
        seriesMapper.deleteSeriesByArticleId(article.getArticleId());
        //删除文章主题
        articleMapper.deleteByPrimaryKey(article.getArticleSid());
        //删除es库的文章记录
        ArticleESEntity esEntity = new ArticleESEntity();
        BeanUtils.copyProperties(article, esEntity);
        blogRepository.delete(esEntity);

        return "success";
    }

    @Override
    public Page<ArticleBossPJ> selectArticleByPage(Page<ArticleBossPJ> page) {

        List<ArticleBossPJ> list = articleMapper.selectArticleByPage(page);
        if(ConUtils.isNotNull(list)){
            int count = articleMapper.selectArticleCountsByPage(page);
            page.setResultList(list);
            page.setTotalCounts(count);
            page.setSuccess(true);
        }

        return page;
    }

    @Override
    public boolean refresh() {

        List<ArticleESEntity> list = articleMapper.selectAllForRefresh();
        if(ConUtils.isNotNull(list)){
            try {
                blogRepository.deleteAll();
                blogRepository.save(list);
                return true;
            }catch (Exception e){
                logger.error("elasticsearch导入数据错误", e);
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteES() {
        blogRepository.deleteAll();
        return true;
    }

    private String uploadImageFile(MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();
        // 以数据流形式上传Object
        PutObjectResponse response = bosClient.putObject(bucketName, fileName, file.getInputStream());
        String responstStr = response.getETag();
        if(StringUtils.isNotBlank(responstStr)){
            return cdnHost + "/" + bucketName + "/" + fileName;
        }
        return "error";
    }

    private String uploadTagFile(MultipartFile file, TagInfo tagInfo) throws IOException {
        String fileName = file.getOriginalFilename();
        // 以数据流形式上传Object
        PutObjectResponse response = bosClient.putObject(bucketName, fileName, file.getInputStream());
        String responstStr = response.getETag();
        if(StringUtils.isNotBlank(responstStr)){
            return cdnHost + "/" + bucketName + "/" + fileName + "@c_1,x_" + tagInfo.getX().intValue() + ",y_" + tagInfo.getY().intValue() + ",w_" + tagInfo.getCutWidth().intValue() + ",h_" + tagInfo.getCutHeight().intValue();
        }
        return "error";
    }

    @Override
    public List<Article> selectArticlesByTypeID(String id) {
        return articleMapper.selectArticlesByTypeID(id);
    }

    @Override
    public List<Article> selectArticlesWithOutTypeID(String id) {
        return articleMapper.selectArticlesWithOutTypeID(id);
    }

    @Override
    public ArticleWithBLOBs selectArticleByPrimaryKey(Integer key) {
        return articleMapper.selectByPrimaryKey(key);
    }

    @Override
    @Transactional
    public String saveTags(ArticlePutObj obj) {
        String id = obj.getArticleId();
        //先删除该文章的所有标签
        tagsMapper.deleteTagsByArticleId(id);
        List<String> tags = obj.getTagIds();
        ArticleTags articleTags;
        List<ArticleTags> inserts = ConUtils.arraylist();
        for(String tempS : tags){
            articleTags = new ArticleTags();
            articleTags.setTagId(tempS);
            articleTags.setArticleId(id);
            inserts.add(articleTags);
        }
        tagsMapper.insertBatchTags(inserts);
        return "success";
    }

    @Override
    public List<Article> selectArticlesBySeriesId(String id) {
        return articleMapper.selectArticlesBySeriesId(id);
    }

    @Override
    public List<Article> selectArticlesWithOutSeriesID(String id) {
        return articleMapper.selectArticlesWithOutSeriesID(id);
    }
}
