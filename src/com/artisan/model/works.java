package com.artisan.model;

/**
 * ��Ʒʵ��
 */
public class works {

	private int id; // ���
	private String worksName; // ��Ʒ����
	private String worksDesc; // ��Ʒ���
	
	
	
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
