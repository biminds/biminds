package com.biminds.sys.service;

import java.util.List;
import java.util.Map;

import com.biminds.entity.SysResourceEntity;

/**
 * 用户资源业务处理逻辑
 * 
 * @author bieskeith.li
 * @date 2017年10月24日 下午10:25:39
 * @since 1.0.0
 */
public interface SysResourceService {

	/**
	 * 根据用户ID获取用户资源列表
	 * 
	 * @param userId
	 *            用户ID
	 * @return
	 * @author bieskeith.li
	 * @date 2017年10月24日 下午10:26:39
	 */
	public Map<String, List<SysResourceEntity>> getResourceListByUserId(
			String userId);

	/**
	 * 获取系统所有权限列表
	 * 
	 * @return 权限列表
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午9:55:28
	 */
	public List<SysResourceEntity> getAllResourceList();

	/**
	 * 根据角色ID获取角色权限列表
	 * 
	 * @param roleId
	 *            角色ID
	 * @return 权限列表
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午9:56:16
	 */
	public List<SysResourceEntity> getRoleResourceByRoleId(String roleId);

	/**
	 * 更新角色权限资源
	 * 
	 * @param roleId
	 *            角色ID
	 * @param resources
	 *            权限资源
	 * @return
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午10:10:54
	 */
	public int updateRoleResouce(String roleId, String resources);
}
