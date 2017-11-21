package com.biminds.sys.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biminds.entity.SysResourceEntity;
import com.biminds.sys.dao.SysResourceDao;
import com.biminds.sys.service.SysResourceService;

/**
 * 用户资源业务处理逻辑实现
 * 
 * @author bieskeith.li
 * @date 2017年10月24日 下午10:27:41
 * @since 1.0.0
 */
@Service(value="sysResourceServiceImpl")
public class SysResourceServiceImpl implements SysResourceService {

	@Autowired
	private SysResourceDao sysResourceDao;

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

}
