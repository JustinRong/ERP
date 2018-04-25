package com.myit.erp.auth.dep.vo;

public class DepModel {
	private Long uuid;
	private String name;
	private String tele;
	
	public DepModel() {
		super();
	}
	public DepModel(Long uuid) {
		super();
		this.uuid = uuid;
	}
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTele() {
		return tele;
	}
	public void setTele(String tele) {
		this.tele = tele;
	}
	@Override
	public String toString() {
		return "DepModel [uuid=" + uuid + "]";
	}

	
	
}
