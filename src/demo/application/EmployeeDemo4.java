package demo.application;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import demo.entity.Employee;
// Transient --> Persistent --> Transient --> Removed
public class EmployeeDemo4 
{
	public static void main(String[] args) 
	{
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();
		
		try {
			Session session = factory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			
			Employee emp = new Employee("Debi"); // emp in Transient State
			System.out.println("\nEmployee : "+emp);
			
			session.save(emp); // emp in Persistent State
			
			session.delete(emp); // emp in Transient State
			System.out.println("\nEmployee : "+emp); 
			
			tx.commit();
			
			emp.finalize();
			emp=null;
			System.gc(); // emp in Removed State
			
		}finally {
			factory.close();
		}
	}
}
