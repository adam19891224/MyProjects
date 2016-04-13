package com.web.manager.resource.entity;

import java.util.Date;

public class Resource {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ts_resources.RESOURCE_ID
     *
     * @mbggenerated
     */
    private String resourceId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ts_resources.RESOURCE_NAME
     *
     * @mbggenerated
     */
    private String resourceName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ts_resources.RESOURCE_DESCRIPTION
     *
     * @mbggenerated
     */
    private String resourceDescription;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ts_resources.RESOURCE_URL
     *
     * @mbggenerated
     */
    private String resourceUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ts_resources.RESOURCE_TYPE
     *
     * @mbggenerated
     */
    private String resourceType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ts_resources.PARENT_RESOURCE_ID
     *
     * @mbggenerated
     */
    private String parentResourceId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ts_resources.CREATE_DATE
     *
     * @mbggenerated
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ts_resources.UPDATE_DATE
     *
     * @mbggenerated
     */
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ts_resources.IS_DELETE
     *
     * @mbggenerated
     */
    private Boolean isDelete;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ts_resources.RESOURCE_ORDER
     *
     * @mbggenerated
     */
    private Integer resourceOrder;
    
    private String resourceIcon;
    
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ts_resources.RESOURCE_ID
     *
     * @return the value of ts_resources.RESOURCE_ID
     *
     * @mbggenerated
     */
    public String getResourceId() {
        return resourceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ts_resources.RESOURCE_ID
     *
     * @param resourceId the value for ts_resources.RESOURCE_ID
     *
     * @mbggenerated
     */
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId == null ? null : resourceId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ts_resources.RESOURCE_NAME
     *
     * @return the value of ts_resources.RESOURCE_NAME
     *
     * @mbggenerated
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ts_resources.RESOURCE_NAME
     *
     * @param resourceName the value for ts_resources.RESOURCE_NAME
     *
     * @mbggenerated
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ts_resources.RESOURCE_DESCRIPTION
     *
     * @return the value of ts_resources.RESOURCE_DESCRIPTION
     *
     * @mbggenerated
     */
    public String getResourceDescription() {
        return resourceDescription;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ts_resources.RESOURCE_DESCRIPTION
     *
     * @param resourceDescription the value for ts_resources.RESOURCE_DESCRIPTION
     *
     * @mbggenerated
     */
    public void setResourceDescription(String resourceDescription) {
        this.resourceDescription = resourceDescription == null ? null : resourceDescription.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ts_resources.RESOURCE_URL
     *
     * @return the value of ts_resources.RESOURCE_URL
     *
     * @mbggenerated
     */
    public String getResourceUrl() {
        return resourceUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ts_resources.RESOURCE_URL
     *
     * @param resourceUrl the value for ts_resources.RESOURCE_URL
     *
     * @mbggenerated
     */
    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl == null ? null : resourceUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ts_resources.RESOURCE_TYPE
     *
     * @return the value of ts_resources.RESOURCE_TYPE
     *
     * @mbggenerated
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ts_resources.RESOURCE_TYPE
     *
     * @param resourceType the value for ts_resources.RESOURCE_TYPE
     *
     * @mbggenerated
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType == null ? null : resourceType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ts_resources.PARENT_RESOURCE_ID
     *
     * @return the value of ts_resources.PARENT_RESOURCE_ID
     *
     * @mbggenerated
     */
    public String getParentResourceId() {
        return parentResourceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ts_resources.PARENT_RESOURCE_ID
     *
     * @param parentResourceId the value for ts_resources.PARENT_RESOURCE_ID
     *
     * @mbggenerated
     */
    public void setParentResourceId(String parentResourceId) {
        this.parentResourceId = parentResourceId == null ? null : parentResourceId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ts_resources.CREATE_DATE
     *
     * @return the value of ts_resources.CREATE_DATE
     *
     * @mbggenerated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ts_resources.CREATE_DATE
     *
     * @param createDate the value for ts_resources.CREATE_DATE
     *
     * @mbggenerated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ts_resources.UPDATE_DATE
     *
     * @return the value of ts_resources.UPDATE_DATE
     *
     * @mbggenerated
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ts_resources.UPDATE_DATE
     *
     * @param updateDate the value for ts_resources.UPDATE_DATE
     *
     * @mbggenerated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ts_resources.IS_DELETE
     *
     * @return the value of ts_resources.IS_DELETE
     *
     * @mbggenerated
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ts_resources.IS_DELETE
     *
     * @param isDelete the value for ts_resources.IS_DELETE
     *
     * @mbggenerated
     */
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ts_resources.RESOURCE_ORDER
     *
     * @return the value of ts_resources.RESOURCE_ORDER
     *
     * @mbggenerated
     */
    public Integer getResourceOrder() {
        return resourceOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ts_resources.RESOURCE_ORDER
     *
     * @param resourceOrder the value for ts_resources.RESOURCE_ORDER
     *
     * @mbggenerated
     */
    public void setResourceOrder(Integer resourceOrder) {
        this.resourceOrder = resourceOrder;
    }

	public String getResourceIcon() {
		return resourceIcon;
	}

	public void setResourceIcon(String resourceIcon) {
		this.resourceIcon = resourceIcon;
	}
}