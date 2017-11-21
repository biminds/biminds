package com.biminds.sys.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.biminds.entity.SysResourceEntity;
import com.biminds.framework.constant.OperateConstant;
import com.biminds.framework.exception.HandlerException;
import com.biminds.framework.mvc.BaseController;
import com.biminds.framework.mvc.model.ResultMessage;
import com.biminds.sys.model.SysResourceTreeModel;
import com.biminds.sys.service.SysResourceService;

/**
 * 角色权限管理
 * 
 * @author bieskeith.li
 * @date 2017年11月13日 下午9:47:55
 * @since 1.0.0
 */
@Controller
@RequestMapping(value = "/sys/resource")
public class SysResourceController extends BaseController {

	/**
	 * 日志
	 */
	protected static final Logger logger = LoggerFactory
			.getLogger(SysResourceController.class);

	@Autowired
	@Qualifier(value = "sysResourceServiceImpl")
	private SysResourceService sysResourceServiceImpl;

	/**
	 * 获取权限资源树形结构
	 * 
	 * @param roleId
	 *            角色ID
	 * @return
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午9:59:49
	 */
	@ResponseBody
	@RequestMapping(value = "/tree")
	public List<SysResourceTreeModel> getResourceTree(String roleId) {
		logger.debug("获取权限资源树形结构，角色ID：" + JSON.toJSONString(roleId));
		List<SysResourceTreeModel> treeList = new ArrayList<SysResourceTreeModel>();
		// 获取系统全部资源列表
		List<SysResourceEntity> resourceList = sysResourceServiceImpl
				.getAllResourceList();
		// 根据角色ID获取角色权限资源列表
		List<SysResourceEntity> permitList = sysResourceServiceImpl
				.getRoleResourceByRoleId(roleId);
		// 遍历系统权限资源
		// 与当前角色权限资源对比，遍历出当前角色拥有权限资源
		// 构建权限资源树
		if (CollectionUtils.isNotEmpty(resourceList)) {
			for (SysResourceEntity resource : resourceList) {
				// 构建树
				SysResourceTreeModel bean = new SysResourceTreeModel();
				bean.setId(resource.getResourceId());
				bean.setpId(null != resource.getParentId() ? resource
						.getParentId() : "0");
				bean.setName(resource.getResourceName());
				bean.setOpen(false);
				bean.setResourceId(resource.getResourceId());
				if (CollectionUtils.isNotEmpty(permitList)) {
					for (SysResourceEntity permit : permitList) {
						if (permit.getResourceId().equals(resource.getResourceId())) {
							bean.setChecked(true);
							break;
						}
					}
				}
				treeList.add(bean);
			}
		}
		return treeList;
	}

	/**
	 * 更新角色权限列表
	 * 
	 * @param roleId
	 *            角色ID
	 * @param resources
	 *            权限信息
	 * @return
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午10:09:04
	 */
	@ResponseBody
	@RequestMapping(value = "/allocate")
	public ResultMessage doAuthAllocate(String roleId, String resources) {
		logger.debug("更新角色权限列表，权限资源：" + JSON.toJSONString(resources));
		ResultMessage msg = new ResultMessage();
		int result = sysResourceServiceImpl
				.updateRoleResouce(roleId, resources);
		if (result == 0) {
			throw new HandlerException(OperateConstant.OPERATE_FAILURE);
		}
		return msg;
	}

}
