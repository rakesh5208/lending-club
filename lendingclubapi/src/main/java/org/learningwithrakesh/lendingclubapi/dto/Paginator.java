package org.learningwithrakesh.lendingclubapi.dto;

import java.util.List;

public class Paginator<T> {
	private long totalRecords;
	private long currentPage;
	private long recordSize;
	private List<T> contents;
	
	public long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}
	public long getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}
	public long getRecordSize() {
		return recordSize;
	}
	public void setRecordSize(long recordSize) {
		this.recordSize = recordSize;
	}
	public List<T> getContents() {
		return contents;
	}
	public void setContents(List<T> contents) {
		this.contents = contents;
	}
	
}
