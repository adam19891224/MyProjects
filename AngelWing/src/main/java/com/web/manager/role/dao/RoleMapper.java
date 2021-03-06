package com.web.manager.role.dao;

import java.util.List;

import com.system.webui.Page;
import com.web.manager.role.entity.Role;

public interface RoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_role
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String roleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_role
     *
     * @mbggenerated
     */
    int insert(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_role
     *
     * @mbggenerated
     */
    int insertSelective(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_role
     *
     * @mbggenerated
     */
    Role selectByPrimaryKey(String roleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_role
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_role
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Role record);
    
    
    Role selectRoleByUserId(String userid);
    
    List<Role> getAllRole();
    
    Integer getCounts(Page<Role> page);
    
    List<Role> selectByPage(Page<Role> page);
}