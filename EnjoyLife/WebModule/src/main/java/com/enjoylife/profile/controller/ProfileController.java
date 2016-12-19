package com.enjoylife.profile.controller;

import com.enjoylife.base.controller.BaseController;
import com.enjoylife.enums.YesNoTypeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ranmin-zhouyuhong
 * 2016/11/28
 */
@Controller
public class ProfileController extends BaseController{

    @RequestMapping("/profile")
    public String profile(ModelMap map){

        map.addAttribute("isProfile", YesNoTypeEnum.Yes.getCode());

        //查询总文章数和总分类数给前台展示
        super.getTotalArticlesToMap(map);
        super.getTotalTypesToMap(map);

        return "profile/index";
    }

}
