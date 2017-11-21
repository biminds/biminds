package com.biminds.sys.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biminds.entity.SysResourceEntity;
import com.biminds.entity.SysRoleResourceEntity;
import com.biminds.sys.dao.SysResourceDao;
import com.biminds.sys.dao.SysRoleResourceDao;
import com.biminds.sys.service.SysResourceService;

/**
 * 用户资源业务处理逻辑实现
 * 
 * @author bieskeith.li
 * @date 2017年10月24日 下午10:27:41
 * @since 1.0.0
 */
@Service(value = "sysResourceServiceImpl")
public class SysResourceServiceImpl implements SysResourceService {

	@Autowired
	private SysResourceDao sysResourceDao;

	@Autowired
	private SysRoleResourceDao sysRoleResourceDao;

	@Override
	public Map<String, List<SysResourceEntity>> getResourceListByUserId(
			String userId) {
		// 查询用户资源列表
		List<SysResourceEntity> resourceList = sysResourceDao
				.selectResourceByUserId(userId);
		// 对资源列表进行处理
		Map<String, List<SysResourceEntity>> authMap = new LinkedHashMap<String, List<SysResourceEntity>>();
		if (CollectionUtils.isNotEmpty(resourceList)) {
			for (SysResourceEntity resource : resourceList) {
				if (10 == resource.getIsLeaf()) {
					List<SysResourceEntity> childrenList = new ArrayList<SysResourceEntity>();
					for (SysResourceEntity cmodule : resourceList) {
						if (cmodule.getParentId() != null
								&& cmodule.getParentId().equals(
										resource.getResourceId())) {
							childrenList.add(cmodule);
						}
					}
					// get children menulist
					authMap.put(resource.getResourceName(), childrenList);
				}
			}
		}
		return authMap;
	}

	@Override
	public List<SysResourceEntity> getAllResourceList() {
		return sysResourceDao.getAllResourceList();
	}

	@Override
	public List<SysResourceEntity> getRoleResourceByRoleId(String roleId) {
		return sysResourceDao.getResourceListByRoleId(roleId);
	}

	@Override
	public int updateRoleResouce(String roleId, String resources) {
		int result = 0;
		// 判断更新权限是否为空
		if (StringUtils.isNotBlank(resources) && resources.endsWith(",")) {
			// 将权限json串转换成数组
			String[] resourceArray = resources.substring(0,
					resources.length() - 1).split(",");
			if (ArrayUtils.isNotEmpty(resourceArray)) {
				// 删除角色当前用有的权限资源
				sysRoleResourceDao.deleteRoleResourceByRoleId(roleId);
				for (String resourceId : resourceArray) {
					SysRoleResourceEntity r = new SysRoleResourceEntity();
					r.setRoleResourceId(UUID.randomUUID().toString());
					r.setRoleId(roleId);
					r.setResourceId(resourceId);
					sysRoleResourceDao.insert(r);
				}
				result = 1;
			}
		}
		return result;
	}

}
