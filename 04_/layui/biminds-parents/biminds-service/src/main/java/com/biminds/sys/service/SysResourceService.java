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
}
