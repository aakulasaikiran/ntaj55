package com.nt.beans;

public class StudentBean {
	private int sno;
	private String sname;
	private String result;
	public StudentBean() {
		System.out.println("StudentBean:0-param constructor");
	}
	public int getSno() {
		System.out.println("StudentBean:getSno()");
		return sno;
	}
	public void setSno(int sno) {
		System.out.println("StudentBean:setSno(-)");
		this.sno = sno;
	}
	public String getSname() {
		System.out.println("StudentBean:getSname()");
		return sname;
	}
	public void setSname(String sname) {
		System.out.println("StudentBean:setSname(-)");
		this.sname = sname;
	}
	public String getResult() {
		System.out.println("StudentBean:getResult()");
		return result;
	}
	public void setResult(String result) {
		System.out.println("StudentBean:setResult(-)");
		this.result = result;
	}

}
