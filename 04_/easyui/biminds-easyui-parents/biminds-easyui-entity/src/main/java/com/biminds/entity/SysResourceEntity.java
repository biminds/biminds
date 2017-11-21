package com.biminds.entity;

import java.io.Serializable;

/**
 * 系统资源对象
 * 
 * @author bieskeith.li
 * @date 2017年10月22日 下午8:29:58
 * @since 1.0.0
 */
public class SysResourceEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5415211762939796503L;

	/**
	 * 资源ID
	 */
	private String resourceId;

	/**
	 * 父资源ID
	 */
	private String parentId;

	/**
	 * 资源名称
	 */
	private String resourceName;

	/**
	 * 资源编码
	 */
	private String resourceCode;

	/**
	 * 是否为菜单：10|是/20|否
	 */
	private Integer isMenu;

	/**
	 * 是否有效：10|是/20|否
	 */
	private Integer enabled;

	/**
	 * 是否是叶子节点：10|否/20|是
	 */
	private Integer isLeaf;

	/**
	 * 权限URL
	 */
	private String url;

	/**
	 * 排序
	 */
	private Integer sortNo;

	public String getResourceId() {
		return resourceId == null ? "" : resourceId.trim();
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getParentId() {
		return parentId == null ? "" : parentId.trim();
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getResourceName() {
		return resourceName == null ? "" : resourceName.trim();
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceCode() {
		return resourceCode == null ? "" : resourceCode.trim();
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	public Integer getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(Integer isMenu) {
		this.isMenu = isMenu;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getUrl() {
		return url == null ? "" : url.trim();
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

}
