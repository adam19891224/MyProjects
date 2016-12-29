package com.enjoylife.profile.controller;

import com.enjoylife.base.controller.BaseController;
import com.enjoylife.enums.YesNoTypeEnum;
import freemarker.template.TemplateException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * ranmin-zhouyuhong
 * 2016/11/28
 */
@RestController
public class ProfileController extends BaseController{

    @RequestMapping("/profile")
    public String profile(ModelMap map, HttpServletRequest request){

        map.addAttribute("isProfile", YesNoTypeEnum.Yes.getCode());

        //查询总文章数和总分类数给前台展示
        super.getTotalArticlesToMap(map);
        super.getTotalTypesToMap(map);

        map.addAttribute("dataType", "profile");

        try {
            return toPjax(request, map, "profile");
        } catch (TemplateException | IOException e) {
            logger.error("pjax返回错误");
        }

        return "/error";
    }

}
