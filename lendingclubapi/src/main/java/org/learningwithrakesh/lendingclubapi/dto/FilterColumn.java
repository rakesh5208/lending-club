package org.learningwithrakesh.lendingclubapi.dto;

public class FilterColumn {
	private String col;
	private String value;
	
	public FilterColumn(String col,String value) {
		this.col = col;
		this.value = value;
	}

	public String getCol() {
		return col;
	}

	public String getValue() {
		return value;
	}
}
