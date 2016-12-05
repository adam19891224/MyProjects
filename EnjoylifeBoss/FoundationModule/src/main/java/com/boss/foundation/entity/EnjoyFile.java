package com.boss.foundation.entity;

import org.springframework.web.multipart.MultipartFile;

/**
 * ranmin-zhouyuhong
 * 2016/12/5
 */
public class EnjoyFile {

    private boolean checkTag = false;

    private MultipartFile file;

    private TagInfo tag;

    public boolean isCheckTag() {
        return checkTag;
    }

    public void setCheckTag(boolean checkTag) {
        this.checkTag = checkTag;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public TagInfo getTag() {
        return tag;
    }

    public void setTag(TagInfo tag) {
        this.tag = tag;
    }
}
