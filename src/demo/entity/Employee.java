package demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@org.hibernate.annotations.Entity(selectBeforeUpdate = true) //Optional, if there is any update before next session / if any setter method is called with Entity class object
	private int id;
	
	@Column(name = "empName")
	private String empName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", empName=" + empName + "]";
	}
	public Employee(String empName) {
		super();
		this.empName = empName;
	}
	public Employee() {
	}
	@Override
	public void finalize() {
	}
}
