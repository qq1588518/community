package com.meigu.community.util.persistence.model; 

import java.io.Serializable;
import java.util.List;

public class Paginate implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static final Integer DEF_COUNT = 10;
	
	/**
	 * data 集合 
	 */
	private List<?> data;
	
	/**
	 * 共有多少条记录
	 */
	private Integer totalCount;
	
	/**
	 * 每页显示记录条数
	 */
	private Integer pageSize;
	
	/**
	 * 第多少页 
	 */
	private Integer cpage;
	
	/**
	 * 共多少页
	 */
	private Integer totalPage;
	
	
	
	public Paginate() {}
	
	/**
	 * @param list	bean 	集合 
	 * @param totalCount	共有多少条记录
	 * @param pageSize		每页显示记录条数
	 * @param cpage		第多少页 	
	 */
	public Paginate(List<?> data,
						Integer totalCount,
						Integer pageSize,
						Integer cpage
	) {
		this.data = data;
		
//		this.totalCount = totalCount;
		if (totalCount <= 0) {
			this.totalCount = 0;
		} else {
			this.totalCount = totalCount;
		}
		
//		this.pageSize = pageSize;
		if (pageSize <= 0) {
			this.pageSize = DEF_COUNT;
		} else {
			this.pageSize = pageSize;
		}
		
//		this.pageNo= pageNo;
		if (cpage <= 0) {
			this.cpage = 1;
		} else {
			this.cpage = cpage;
		}
		if ((this.cpage - 1) * this.pageSize >= totalCount) {
			this.cpage = totalCount / pageSize;
		}
		
		this.totalPage = totalCount / pageSize;
		if (totalCount % pageSize != 0 || totalPage == 0) {
			this.totalPage++;
		}
	}
	
	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	/**
	 * 共有多少条记录
	 * @return
	 */
	public Integer getTotalCount() {
		return totalCount;
	}

	/**
	 * 共有多少条记录
	 */
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 每页显示记录条数
	 * @return
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * 每页显示记录条数
	 * @param pageSize
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 第多少页 
	 * @return
	 */
	public Integer getCpage() {
		return cpage;
	}
	
	/**
	 * 第多少页 
	 * @param cpage
	 */
	public void setCpage(Integer cpage) {
		this.cpage = cpage;
	}

//	public Integer getTotalPage() {
//		return totalPage;
//	}
	/**
	 * 共多少页
	 */
	public Integer getTotalPage() {
//		int totalPage = totalCount / pageSize;
//		if (totalCount % pageSize != 0 || totalPage == 0) {
//			totalPage++;
//		}
		return totalPage;
	}

	/**
	 * 共多少页
	 * @param totalPage
	 */
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	
}