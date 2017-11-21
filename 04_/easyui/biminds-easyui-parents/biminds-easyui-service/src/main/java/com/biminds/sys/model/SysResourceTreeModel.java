package com.biminds.sys.model;

public class SysResourceTreeModel {

	private String id;

	private String pId;

	private String name;

	private Boolean checked;

	private Boolean open;

	private Boolean isParent;

	private String code;

	private String click;

	private String resourceId;

	public String getId() {
		return id == null ? "" : id.trim();
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId == null ? "" : pId.trim();
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name == null ? "" : name.trim();
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public String getCode() {
		return code == null ? "" : code.trim();
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getClick() {
		return click == null ? "" : click.trim();
	}

	public void setClick(String click) {
		this.click = click;
	}

	public String getResourceId() {
		return resourceId == null ? "" : resourceId.trim();
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

}
