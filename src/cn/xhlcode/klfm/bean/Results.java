package cn.xhlcode.klfm.bean;

import java.util.List;

public class Results {
	private int haveNext;
	private int nextPage;
	private int havePre;
	private int prePage;
	private int currentPage;
	private int count;
	private int sumPage;
	private int pageSize;
	private List<DataList> dataList;
	public int getHaveNext() {
		return haveNext;
	}
	public void setHaveNext(int haveNext) {
		this.haveNext = haveNext;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getHavePre() {
		return havePre;
	}
	public void setHavePre(int havePre) {
		this.havePre = havePre;
	}
	public int getPrePage() {
		return prePage;
	}
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getSumPage() {
		return sumPage;
	}
	public void setSumPage(int sumPage) {
		this.sumPage = sumPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<DataList> getDataList() {
		return dataList;
	}
	public void setDataList(List<DataList> dataList) {
		this.dataList = dataList;
	}
	
	@Override
	public String toString() {
		return "Result [haveNext=" + haveNext + ", nextPage=" + nextPage + ", havePre=" + havePre + ", prePage="
				+ prePage + ", currentPage=" + currentPage + ", count=" + count + ", sumPage=" + sumPage + ", pageSize="
				+ pageSize + ", dataList=" + dataList + "]";
	}
	
}
