package com.biminds.sys.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.biminds.entity.SysResourceEntity;
import com.biminds.framework.mvc.mybatis.BaseDao;

/**
 * 系统资源
 * 
 * @author bieskeith.li
 * @date 2017年10月24日 下午8:57:01
 * @since 1.0.0
 */
@Repository
public class SysResourceDao extends BaseDao<SysResourceEntity, Serializable> {

	/**
	 * 根据用户ID查询用户资源列表
	 * 
	 * @param userId
	 *            用户ID
	 * @return
	 * @author bieskeith.li
	 * @date 2017年10月24日 下午10:34:50
	 */
	public List<SysResourceEntity> selectResourceByUserId(String userId) {
		return getSqlSession().selectList(
				"SysResourceDao.selectResourceByUserId", userId);
	}

	/**
	 * 获取所有系统权限资源
	 * 
	 * @return 系统权限资源列表
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午10:03:21
	 */
	public List<SysResourceEntity> getAllResourceList() {
		return getSqlSession().selectList("SysResourceDao.getAllResourceList");
	}

	/**
	 * 根据角色ID查询角色权限列表
	 * 
	 * @param roleId
	 *            角色ID
	 * @return 权限列表
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午10:07:21
	 */
	public List<SysResourceEntity> getResourceListByRoleId(String roleId) {
		return getSqlSession().selectList(
				"SysResourceDao.getResourceListByRoleId", roleId);
	}

}
