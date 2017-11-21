package com.biminds.sys.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.biminds.entity.SysUserEntity;
import com.biminds.framework.mvc.form.PageForm;
import com.biminds.framework.mvc.mybatis.BaseDao;
import com.biminds.sys.form.SysUserForm;
import com.biminds.sys.model.SysUserModel;
import com.github.pagehelper.PageInfo;

/**
 * 系统用户sql
 * 
 * @author bieskeith.li
 * @date 2017年10月24日 下午9:59:22
 * @since 1.0.0
 */
@Repository
public class SysUserDao extends BaseDao<SysUserEntity, Serializable> {

	/**
	 * 根据系统用户名称查询系统用户
	 * 
	 * @param userName
	 *            用户名
	 * @return 系统用户
	 * @author bieskeith.li
	 * @date 2017年10月24日 下午10:11:51
	 */
	public SysUserEntity selectUserByUserName(String userName) {
		// 构建查询条件
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("userName", userName);
		return getSqlSession().selectOne("SysUserDao.selectUserByParameter",
				parameter);
	}

	/**
	 * 根据查询条件查询系统用户列表
	 * 
	 * @param pageForm
	 *            分页对象
	 * @param sysUserForm
	 *            查询条件
	 * @return 系统用户列表
	 * @author bieskei
	 * @date 2017年11月12日 上午11:02:07
	 */
	public PageInfo<SysUserModel> getUserList(PageForm pageForm,
			SysUserForm sysUserForm) {
		return pageFindModel("SysUserDao.selectUserByParameterForModel",
				pageForm, sysUserForm);
	}

}
