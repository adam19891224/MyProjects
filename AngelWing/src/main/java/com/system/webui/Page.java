package com.system.webui;

import java.util.Date;
import java.util.List;

public class Page<T> {

	private Integer total;
	
	private Integer pageSize;
	
	private Integer pageNumber;
	
	private List<T> resultList;
	
	private String stringParam1;
	
	private String stringParam2;
	
	private String stringParam3;
	
	private String stringParam4;
	
	private String stringParam5;
	
	private Date dateParam1;
	
	private Date dateParam2;
	
	private Date dateParam3;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber > 1 ? ((pageNumber - 1) * pageSize) : 0;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	public String getStringParam1() {
		return stringParam1;
	}

	public void setStringParam1(String stringParam1) {
		this.stringParam1 = stringParam1;
	}

	public String getStringParam2() {
		return stringParam2;
	}

	public void setStringParam2(String stringParam2) {
		this.stringParam2 = stringParam2;
	}

	public String getStringParam3() {
		return stringParam3;
	}

	public void setStringParam3(String stringParam3) {
		this.stringParam3 = stringParam3;
	}

	public String getStringParam4() {
		return stringParam4;
	}

	public void setStringParam4(String stringParam4) {
		this.stringParam4 = stringParam4;
	}

	public String getStringParam5() {
		return stringParam5;
	}

	public void setStringParam5(String stringParam5) {
		this.stringParam5 = stringParam5;
	}

	public Date getDateParam1() {
		return dateParam1;
	}

	public void setDateParam1(Date dateParam1) {
		this.dateParam1 = dateParam1;
	}

	public Date getDateParam2() {
		return dateParam2;
	}

	public void setDateParam2(Date dateParam2) {
		this.dateParam2 = dateParam2;
	}

	public Date getDateParam3() {
		return dateParam3;
	}

	public void setDateParam3(Date dateParam3) {
		this.dateParam3 = dateParam3;
	}
}
