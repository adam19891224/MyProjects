package com.tools.utils;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2015/12/29
 */
public class SizeUtils {

    private Double topx;

    private Double topy;

    private Double imgW;

    private Double imgH;

    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Double getTopx() {
        return topx;
    }

    public void setTopx(Double topx) {
        this.topx = topx;
    }

    public Double getTopy() {
        return topy;
    }

    public void setTopy(Double topy) {
        this.topy = topy;
    }

    public Double getImgW() {
        return imgW;
    }

    public void setImgW(Double imgW) {
        this.imgW = imgW;
    }

    public Double getImgH() {
        return imgH;
    }

    public void setImgH(Double imgH) {
        this.imgH = imgH;
    }
}
