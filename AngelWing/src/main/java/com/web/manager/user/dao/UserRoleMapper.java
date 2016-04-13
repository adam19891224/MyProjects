package com.web.manager.user.dao;

import com.web.manager.user.entity.UserRole;

public interface UserRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_user_role
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String userRoleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_user_role
     *
     * @mbggenerated
     */
    int insert(UserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_user_role
     *
     * @mbggenerated
     */
    int insertSelective(UserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_user_role
     *
     * @mbggenerated
     */
    UserRole selectByPrimaryKey(String userRoleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_user_role
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(UserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_user_role
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(UserRole record);
}