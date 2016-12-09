package com.boss.service.blogs.impl;

import com.boss.dao.blog.mapper.ArticleMapper;
import com.boss.dao.blog.pojo.ArticleWithBLOBs;
import com.boss.foundation.modules.ArticleESEntity;
import com.boss.foundation.entity.ArticleEntity;
import com.boss.foundation.entity.EnjoyFile;
import com.boss.foundation.entity.TagInfo;
import com.boss.foundation.utils.ConUtils;
import com.boss.foundation.utils.MD5Utils;
import com.boss.foundation.utils.StringUtils;
import com.boss.service.base.AbstractService;
import com.boss.service.blogs.IBlogService;
import com.boss.service.blogs.repository.IBlogRepository;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.UUID;


/**
 * ranmin-zhouyuhong
 * 2016/12/5
 */
@Service
public class BlogServiceImpl extends AbstractService implements IBlogService {

    @Value("${image.host}")
    private String host;
    @Value("${image.url}")
    private String url;

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private IBlogRepository blogRepository;

    private static final String key = "zhouyuhong19891224";

    @Override
    public String toUploadFile(EnjoyFile file) {
        CloseableHttpResponse response = null;

        try {
            //上传列表标记图片
            if (file.isCheckTag()) {
                response = this.uploadTagFile(file.getFile(), file.getTag());
            }
            //上传文章内容图片
            if (!file.isCheckTag()) {
                response = this.uploadImageFile(file.getFile());
            }

            if(response != null){
                return this.getResultByResponse(response);
            }

        } catch (IOException e) {
            logger.error("上传图片发生异常", e);
            return "exception";
        }

        return "fail";
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
        articleWithBLOBs.setArticleImg(host + entity.getArticleImg());
        articleWithBLOBs.setArticleDescription(entity.getArticleDescription());
        articleWithBLOBs.setArticleBody(entity.getArticleBody());

        articleMapper.insertSelective(articleWithBLOBs);

        ArticleESEntity esEntity = articleMapper.selectByArticleId(uuid);
        blogRepository.save(esEntity);

        return "success";
    }

    @Override
    public boolean refresh() {

        List<ArticleESEntity> list = articleMapper.selectAllForRefresh();
        if(ConUtils.isNotNull(list)){
            try {
                blogRepository.save(list);
                return true;
            }catch (Exception e){
                logger.error("elasticsearch导入数据错误", e);
            }
            return false;
        }
        return true;
    }

    private CloseableHttpResponse uploadImageFile(MultipartFile file) throws IOException {
        ContentType contentType = ContentType.create(HTTP.CONTENT_TYPE, Consts.UTF_8);
        //根据文件名 + 私钥 生成签名
        String sign = MD5Utils.getMD5(file.getOriginalFilename() + key);

        HttpEntity reqEntity = MultipartEntityBuilder.create()
                .setCharset(Charset.forName("utf-8"))
                .addPart("file", new InputStreamBody(file.getInputStream(), file.getOriginalFilename()))
                .addPart("tag", new StringBody("false", contentType))
                .addPart("sign", new StringBody(sign, contentType))
                .build();

        return this.startUpload(reqEntity);
    }

    private CloseableHttpResponse uploadTagFile(MultipartFile file, TagInfo tagInfo) throws IOException {
        ContentType contentType = ContentType.create(HTTP.CONTENT_TYPE, Consts.UTF_8);
        //根据文件名 + 私钥 生成签名
        String sign = MD5Utils.getMD5(file.getOriginalFilename() + key);

        HttpEntity reqEntity = MultipartEntityBuilder.create()
                .setCharset(Charset.forName("utf-8"))
                .addPart("file", new InputStreamBody(file.getInputStream(), file.getOriginalFilename()))
                .addPart("x", new StringBody(tagInfo.getX(), contentType))
                .addPart("y", new StringBody(tagInfo.getY(), contentType))
                .addPart("cutWidth", new StringBody(tagInfo.getCutWidth(), contentType))
                .addPart("cutHeight", new StringBody(tagInfo.getCutHeight(), contentType))
                .addPart("tag", new StringBody("true", contentType))
                .addPart("sign", new StringBody(sign, contentType))
                .build();

        return this.startUpload(reqEntity);
    }

    private CloseableHttpResponse startUpload(HttpEntity entity) {
        HttpPost httpPost = new HttpPost(host + url);

        httpPost.setEntity(entity);
        //创建httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            return httpclient.execute(httpPost);
        } catch (IOException e) {
            logger.error("执行上传功能发生异常", e);
        }
        return null;
    }

    private String getResultByResponse(CloseableHttpResponse response){
        String res = "null";

        if(response != null){
            System.out.println(response.getStatusLine());
            HttpEntity responseEntity = response.getEntity();
            InputStream instream = null;
            BufferedReader reader = null;
            try {
                instream = responseEntity.getContent();
                reader = new BufferedReader(new InputStreamReader(instream, "utf-8"));

                res = reader.readLine();
                //消耗掉response
                EntityUtils.consume(responseEntity);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                    if (instream != null) {
                        instream.close();
                    }
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return res;
    }
}
