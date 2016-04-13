package com.img.entity;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2015/12/31
 */
public abstract class AbstractFileEntity {

    private MultipartFile file;

    private String suffix;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
