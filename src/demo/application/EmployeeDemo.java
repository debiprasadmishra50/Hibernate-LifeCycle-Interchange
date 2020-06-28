package demo.application;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import demo.entity.Employee;

// Transient --> Persistent --> Detached --> Removed
public class EmployeeDemo 
{
	public static void main(String[] args) 
	{
		// Create a factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();
		// Create a session
		Session session = factory.getCurrentSession();
		
		try {
			// Begin Transaction
			Transaction tx = session.beginTransaction();
			
			Employee emp = new Employee("Debi"); // emp in Transient state
			System.out.println("\nEmployee : "+emp);
			
			emp.setEmpName("Debi Prasad");
			System.out.println("\nEmployee : "+emp);
			
			session.save(emp); // emp in Persistent State
			
			emp.setEmpName("Vicky"); // If emp is in Persistent State, no need to save() again, It will update in DB
			System.out.println("\nEmployee : "+emp);
			
			tx.commit();
			
			session.close(); // emp in Detached State

			emp.setEmpName("Debi Mishra"); // Not gonna be reflected in DB
			System.out.println("\nEmployee : "+emp);
			
			emp.finalize();
			emp=null;
			System.gc(); // emp in Removed State
			
		}finally {
			factory.close();
		}
	}
}
