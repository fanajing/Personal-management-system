package com.artisan.model;

/**
 * 班级实体
 * @author llq-artisan
 *
 */
public class works {

	private int id; // 编号
	private String worksName; // 班级名称
	private String worksDesc; // 备注
	
	
	
	public works() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public works(String worksName, String worksDesc) {
		super();
		this.worksName = worksName;
		this.worksDesc = worksDesc;
	}
	
	


	public works(int id, String worksName, String worksDesc) {
		super();
		this.id = id;
		this.worksName = worksName;
		this.worksDesc = worksDesc;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}



	public String getworkName() {
		return worksName;
	}


	public void setworkName(String worksName) {
		this.worksName = worksName;
	}


	public String getCalssDesc() {
		return worksDesc;
	}


	public void setCalssDesc(String worksDesc) {
		this.worksDesc = worksDesc;
	}


	@Override
	public String toString() {
		return worksName;
	}


}
