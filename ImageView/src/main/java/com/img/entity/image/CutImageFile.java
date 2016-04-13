package com.img.entity.image;

import com.img.entity.AbstractFileEntity;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2015/12/31
 */
public class CutImageFile extends AbstractFileEntity {

    private String x;

    private String y;

    private String cutWidth;

    private String cutHeight;

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getCutWidth() {
        return cutWidth;
    }

    public void setCutWidth(String cutWidth) {
        this.cutWidth = cutWidth;
    }

    public String getCutHeight() {
        return cutHeight;
    }

    public void setCutHeight(String cutHeight) {
        this.cutHeight = cutHeight;
    }
}
