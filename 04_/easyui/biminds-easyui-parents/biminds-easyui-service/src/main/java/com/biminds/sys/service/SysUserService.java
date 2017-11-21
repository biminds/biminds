package com.biminds.sys.service;

import com.biminds.entity.SysUserEntity;
import com.biminds.framework.mvc.form.PageForm;
import com.biminds.sys.form.SysUserForm;
import com.biminds.sys.model.SysUserModel;
import com.github.pagehelper.PageInfo;

/**
 * 用户管理业务逻辑处理实现类
 * 
 * @author bieskei
 * @date 2017年11月12日 上午9:44:45
 * @since 1.0.0
 */
public interface SysUserService {

	/**
	 * 根据查询条件查询系统用户列表
	 * 
	 * @param pageForm
	 *            分页对象
	 * @param sysUserForm
	 *            查询条件
	 * @return 系统用户列表
	 * @author bieskei
	 * @date 2017年11月12日 上午10:57:48
	 */
	public PageInfo<SysUserModel> selectUserList(PageForm pageForm,
			SysUserForm sysUserForm);

	/**
	 * 根据用户名查询用户信息
	 * 
	 * @param userName
	 *            用户名
	 * @return 用户信息
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午10:47:00
	 */
	public SysUserEntity selectUserByUserName(String userName);

	/**
	 * 创建新用户
	 * 
	 * @param sysUserForm
	 *            用户信息
	 * @param roles
	 *            角色信息
	 * @return 是否成功
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午10:47:59
	 */
	public int insertUser(SysUserForm sysUserForm, String roles);

	/**
	 * 更新用户信息
	 * 
	 * @param sysUserForm
	 *            用户信息
	 * @param roles
	 *            角色信息
	 * @return 是否成功
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午10:48:36
	 */
	public int updateUser(SysUserForm sysUserForm, String roles);

	/**
	 * 根据用户ID查询用户信息
	 * 
	 * @param userId
	 *            用户ID
	 * @return 用户信息
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午11:43:56
	 */
	public SysUserEntity getUserByUserId(String userId);

	/**
	 * 更新用户信息
	 * 
	 * @param sysUserEntity
	 * @return
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午11:44:20
	 */
	public int updateUser(SysUserEntity sysUserEntity);
}
