package com.article.show;

import com.article.vo.ArticleWithBLOBs;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/5/5
 */
public class NewArticle extends ArticleWithBLOBs {

    private String highLightTitle;

    public String getHighLightTitle() {
        return highLightTitle;
    }

    public void setHighLightTitle(String highLightTitle) {
        this.highLightTitle = highLightTitle;
    }
}
