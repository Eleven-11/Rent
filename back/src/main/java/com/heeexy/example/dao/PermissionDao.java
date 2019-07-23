package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.Set;

/**
 * @author: Lingling
 * @description: 后台用户权限
 * @date: 2019-07-23 17:49
 */
public interface PermissionDao {
	/**
	 * 查询用户的角色 菜单 权限
	 * @param username
	 * @return
	 */
	JSONObject getUserPermission(String username);

	/**
	 * 查询所有的菜单
	 * @return
	 */
	Set<String> getAllMenu();

	/**
	 * 查询所有的权限
	 * @return
	 */
	Set<String> getAllPermission();
}
