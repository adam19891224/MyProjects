package com.enjoylife.base.controller;

import com.alibaba.fastjson.JSONObject;
import com.enjoylife.article.vo.NewArticle;
import com.enjoylife.blogs.IBlogsService;
import com.enjoylife.enums.ResponseEnum;
import com.enjoylife.type.ITypeService;
import com.enjoylife.type.vo.Type;
import com.enjoylife.utils.ConUtils;
import com.enjoylife.utils.IPUtils;
import com.enjoylife.utils.StringUtils;
import com.enjoylife.view.Page;
import com.enjoylife.view.ResponseData;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/15
 */
public class BaseController {

    protected Logger logger = Logger.getLogger(this.getClass());

    @Resource
    protected ITypeService typeService;
    @Resource
    protected IBlogsService blogsService;
    @Autowired
    private Configuration configuration;

    /**
     * 把参数转换为json
     */
    protected String parseObjectToJson(Object o){
        String res = null;
        try {
            res = JSONObject.toJSONString(o);
        }catch (Exception e){
            logger.error("JSON 转换错误: =========================");
            logger.error("异常原因： " + e);
        }
        return res;
    }

    /**
     * xss过滤
     */
    protected <T> void escapeKeyword(Page<T> page){
        page.setKw(StringEscapeUtils.escapeJavaScript(page.getKw()));
    }

    /**
     * 获取session
     */
    protected HttpSession getSession(HttpServletRequest request){
        return request.getSession();
    }

    /**
     * 获取当前用户ip
     */
    protected String getIP(HttpServletRequest request){
        return IPUtils.getClientIp(request);
    }

    /**
     * 获取当前登录时的文章总数
     */
    protected void getTotalArticlesToMap(Map<String, Object> map){
        int blogCount = blogsService.selectArticlesCountsByPage(new Page<NewArticle>());
        map.put("totalArticles", blogCount);
    }

    /**
     * 获取当前登录的类别总数
     */
    protected void getTotalTypesToMap(Map map){
        List<Type> types = typeService.selectAllTypes();
        if(ConUtils.isNotNull(types)){
            map.put("types", types);
            map.put("totalTypes", types.size());
        }
    }

    /**
     * 返回Pjax形式的页面
     */
    protected String toPjax(HttpServletRequest request, ModelMap map, String type) throws IOException, TemplateException {
        String isPj = request.getHeader("X-PJAX");
        // 设置FreeMarker的模版文件位置
        configuration.setClassForTemplateLoading(BaseController.class, "/templates");
        configuration.setEncoding(Locale.getDefault(), "utf-8");

        // 创建Template对象
        Template template;
        if(StringUtils.isNotNull(isPj) && isPj.equalsIgnoreCase("true")){
            template = configuration.getTemplate("/" + type + "/main.ftl");
        }else{
            template = configuration.getTemplate("/" + type + "/index.ftl");
        }
        // 输出流
        StringWriter writer = new StringWriter();
        // 将数据和模型结合生成html
        template.process(map, writer);
        // 获得html
        String resultString = writer.toString();

        writer.close();
        return resultString;
    }

    /**
     * 返回responseDatA数据
     */
    protected ResponseData<Map<String, Object>> responseRes(ResponseEnum responseEnum, Map<String, Object> map){
        ResponseData<Map<String, Object>> responseData = new ResponseData<>();
        responseData.setCode(responseEnum.getCode());
        responseData.setMessage(responseEnum.getMessage());
        responseData.setData(map);
        return responseData;
    }
}
