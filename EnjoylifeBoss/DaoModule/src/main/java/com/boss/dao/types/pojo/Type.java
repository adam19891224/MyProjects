package com.boss.dao.types.pojo;

public class Type {
    private Integer typeSid;

    private String typeId;

    private String typeName;

    private String typeParentId;

    public Integer getTypeSid() {
        return typeSid;
    }

    public void setTypeSid(Integer typeSid) {
        this.typeSid = typeSid;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getTypeParentId() {
        return typeParentId;
    }

    public void setTypeParentId(String typeParentId) {
        this.typeParentId = typeParentId == null ? null : typeParentId.trim();
    }
}