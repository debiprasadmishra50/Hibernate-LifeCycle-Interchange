package demo.application;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import demo.entity.Employee;
// Persistent --> Detached --> Persistent --> Detached --> Removed but will reflect as a new Object in DB as close() affects all instances
public class EmployeeDemo2 
{
	public static void main(String[] args) 
	{
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();
		
		try {
			Session session1 = factory.getCurrentSession();
			Transaction tx1 = session1.beginTransaction();
			
			Employee emp = session1.get(Employee.class, 1); // emp in Persistent State
			System.out.println("\nEmployee : "+emp);
			
			tx1.commit();
			session1.close(); // emp in Detached State
			
			emp.setEmpName("Debi"); // not reflected in DB
			System.out.println("\nEmployee : "+emp);
			
			Session session2 = factory.getCurrentSession();
			Transaction tx2 = session2.beginTransaction();
			
			session2.save(emp); // Persistent State // now changes will be reflected in DB but as a new object
			// To resolve, use @org.hibernate.annotations.Entity(selectBeforeUpdate = true) in POJO class
			// Or use detach() to stay in next method, Refer EmployeeDemo3.java
			
			tx2.commit();
			
			emp.finalize();
			emp=null;
			System.gc(); // emp in Removed State
			
		}finally {
			factory.close();
		}
	}
}
