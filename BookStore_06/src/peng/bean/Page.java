package peng.bean;

import java.util.List;

public class Page<T>{

	// current pageNumber pageNo; user input
	private int pageNo;

	// total pageNumber totalPage; calculate to get
	private int totalPage;

	// total number of results totalCount; search from databank
	private int totalCount;

	// size of every page pageSize; manually set
	private int pageSize = 4;

	// index of the first result in this page index; calculate to get, no set
	// function
	private int index;

	// hasNext or not; assess
	private boolean hasNext;

	// hasPrev or not; assess
	private boolean hasPrev;

	// actual data on this page; get from database
	private List<T> pageData;

	// url of page for both frontend and backend
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		pageNo = pageNo > 0 ? pageNo : 1;
		// 判断当前页面是否越上界：
		// 要获取真正的totalPage必须有getTotalCount()和getPageSize(), 要在service中先设置这两个值
		pageNo = pageNo > getTotalPage() ? getTotalPage() : pageNo;
		this.pageNo = pageNo;
	}

	public int getTotalPage() {
		// calculate totalpage
		int t = getTotalCount() / getPageSize();
		if (!(getTotalCount() % getPageSize() == 0)) {
			t = t + 1;
		}
		return t;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	// calculate to get
	public int getIndex() {
		int index = (getPageNo() - 1) * getPageSize();
		if (index < 0)
			index = 0;
		return index;
	}

	public boolean isHasNext() {
		return getPageNo() < getTotalPage();
	}

	public boolean isHasPrev() {
		return getPageNo() > 1;
	}

	public List<T> getPageData() {
		return pageData;
	}

	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}

	public Page(int pageNo, int totalPage, int totalCount, int pageSize, int index, boolean hasNext, boolean hasPrev,
			List<T> pageData, String url) {
		super();
		this.pageNo = pageNo;
		this.totalPage = totalPage;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.index = index;
		this.hasNext = hasNext;
		this.hasPrev = hasPrev;
		this.pageData = pageData;
		this.url = url;
	}

	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Page [pageNo=" + pageNo + ", totalPage=" + totalPage + ", totalCount=" + totalCount + ", pageSize="
				+ pageSize + ", index=" + index + ", hasNext=" + hasNext + ", hasPrev=" + hasPrev + ", pageData="
				+ pageData + ", url=" + url + "]";
	}

}
