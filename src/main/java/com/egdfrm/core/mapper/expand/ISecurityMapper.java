/**
 * ISecurityMapper.java
 * Created at 2014年4月19日
 * Created by wangkang
 */
package com.egdfrm.core.mapper.expand;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * ClassName: ISecurityMapper
 * </p>
 * <p>
 * Description: 安全DAO
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年4月19日
 * </p>
 */
public interface ISecurityMapper {

    /**
     * <p>
     * Description: 返回用户所关联的角色
     * </p>
     * 
     * @param loginName 用户名
     * @return 角色列表
     */
    List<String> findUserRoles(@Param("loginName") String loginName);

    /**
     * <p>
     * Description: 返回角色权限
     * </p>
     * 
     * @param roleList 角色列表
     * @return 权限列表
     */
    List<Map<String, Object>> findRolePermissions(@Param("roleList") List<String> roleList);

    /**
     * <p>
     * Description: 返回用户直接权限
     * </p>
     * 
     * @param loginName 用户名
     * @return 权限列表
     */
    List<Map<String, Object>> findUserPermissions(@Param("loginName") String loginName);

    /**
     * <p>
     * Description: 返回角色功能
     * </p>
     * 
     * @param roleList 角色列表
     * @return 功能列表
     */
    List<String> findRoleFunctions(@Param("roleList") List<String> roleList);

    /**
     * <p>
     * Description: 返回用户直接功能
     * </p>
     * 
     * @param loginName 用户名
     * @return 功能列表
     */
    List<String> findUserFunctions(@Param("loginName") String loginName);
    
    /**
     * <p>
     * Description: 返回职责功能
     * </p>
     *  
     * add by sjf 20161201
     * @param loginName 用户名
     * @return 功能列表
     */
    List<String> findJobFunctionsByLoginName(@Param("loginName")  String loginName);
}
