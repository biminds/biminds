package com.biminds.sys.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.biminds.entity.SysUserRoleEntity;
import com.biminds.framework.mvc.mybatis.BaseDao;

/**
 * 用户角色sql
 * 
 * @author bieskeith.li
 * @date 2017年11月13日 下午10:56:12
 * @since 1.0.0
 */
@Repository
public class SysUserRoleDao extends BaseDao<SysUserRoleEntity, Serializable> {

	/**
	 * 根据用户ID删除用户角色
	 * 
	 * @param userId
	 *            用户ID
	 * @return
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午11:00:37
	 */
	public int deleteUserRoleByUserId(String userId) {
		return getSqlSession().delete("SysUserRoleDao.deleteUserRoleByUserId",
				userId);
	}

}
