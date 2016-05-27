package com.article.vo;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/5/5
 */
public class NewArticle extends ArticleWithBLOBs {

    private String highLightTitle;

    private int hots;

    public int getHots() {
        return hots;
    }

    public void setHots(int hots) {
        this.hots = hots;
    }

    public String getHighLightTitle() {
        return highLightTitle;
    }

    public void setHighLightTitle(String highLightTitle) {
        this.highLightTitle = highLightTitle;
    }
}
