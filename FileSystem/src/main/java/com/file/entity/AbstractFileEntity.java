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
