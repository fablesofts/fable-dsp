package com.fable.dsp.common.dto.dataswitch;

public class TestDto {
	private String productid;
	private int unitcost;
	private String status;
	private double listprice;
	private String attr1;
	private String itemid;
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public int getUnitcost() {
		return unitcost;
	}
	public void setUnitcost(int unitcost) {
		this.unitcost = unitcost;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getListprice() {
		return listprice;
	}
	public void setListprice(double listprice) {
		this.listprice = listprice;
	}
	public String getAttr1() {
		return attr1;
	}
	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public TestDto(String productid, int unitcost, String status,
			double listprice, String attr1, String itemid) {
		this.productid = productid;
		this.unitcost = unitcost;
		this.status = status;
		this.listprice = listprice;
		this.attr1 = attr1;
		this.itemid = itemid;
	}
	public TestDto() {
		super();
	}
	
}	
