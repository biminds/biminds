package com.biminds.framework.mvc.form;

public class PageForm {

	/**
	 * 当前页
	 */
	private Integer page;

	/**
	 * 每页显示条数
	 */
	private Integer rows;

	/**
	 * 排序
	 */
	private Integer sort;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
