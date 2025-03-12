package com.product.model;
import java.sql.Date;

public class EmpVO implements java.io.Serializable{
	private Integer empno;
	private String ename;
	private String job;
	private Date hiredate;
	private Double sal;
	private Double comm;
	private Integer deptno;

	public EmpVO() {
		super();
	}

	public EmpVO(Integer empno, String ename, String job, Date hiredate, Double sal, Double comm, Integer deptno) {
		super();
		this.empno = empno;
		this.ename = ename;
		
		this.job = job;
		this.hiredate = hiredate;
		this.sal = sal;
		this.comm = comm;
		this.deptno = deptno;
	}
	
	public Integer getEmpno() {
		return empno;
	}
	public void setEmpno(Integer empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public Double getSal() {
		return sal;
	}
	public void setSal(Double sal) {
		this.sal = sal;
	}
	public Double getComm() {
		return comm;
	}
	public void setComm(Double comm) {
		this.comm = comm;
	}
	public Integer getDeptno() {
		return deptno;
	}
	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}
}
