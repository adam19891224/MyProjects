package com.file.entity;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2015/12/31
 */
public abstract class AbstractFileEntity implements Serializable {

    private static final long serialVersionUID = 6141530701740261773L;

    private MultipartFile file;

    private String sign;

    private Boolean tag;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Boolean getTag() {
        return tag;
    }

    public void setTag(Boolean tag) {
        this.tag = tag;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

}
