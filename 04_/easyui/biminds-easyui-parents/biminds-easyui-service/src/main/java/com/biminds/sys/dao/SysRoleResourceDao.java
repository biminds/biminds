package com.biminds.sys.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.biminds.entity.SysRoleResourceEntity;
import com.biminds.framework.mvc.mybatis.BaseDao;

/**
 * 角色权限资源sql
 * 
 * @author bieskeith.li
 * @date 2017年11月13日 下午10:15:46
 * @since 1.0.0
 */
@Repository
public class SysRoleResourceDao extends
		BaseDao<SysRoleResourceEntity, Serializable> {

	/**
	 * 根据角色ID删除角色权限资源
	 * 
	 * @param roleId
	 * @return
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午10:16:12
	 */
	public int deleteRoleResourceByRoleId(String roleId) {
		return getSqlSession().delete(
				"SysRoleResourceDao.deleteRoleResourceByRoleId", roleId);
	}

}
