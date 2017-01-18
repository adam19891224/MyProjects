package com.enjoylife.bullshit.controller;

import com.enjoylife.base.controller.BaseController;
import com.enjoylife.enums.YesNoTypeEnum;
import freemarker.template.TemplateException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * ranmin-zhouyuhong
 * 2017/1/18
 */
@RestController
public class BullshitController extends BaseController{

    @RequestMapping(value = "/bullshit", method = {RequestMethod.GET, RequestMethod.POST})
    public String bullshit(ModelMap map, HttpServletRequest request){

        map.addAttribute("isSeries", YesNoTypeEnum.Yes.getCode());

        //查询总文章数和总分类数给前台展示
        super.getTotalTypesToMap(map);
        super.getTotalArticlesToMap(map);

        map.addAttribute("dataType", "bull");

        try {
            return toPjax(request, map, "bull");
        } catch (TemplateException | IOException e) {
            logger.error("pjax返回错误");
        }

        return "/error";
    }

}
