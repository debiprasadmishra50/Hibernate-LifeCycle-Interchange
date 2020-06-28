package demo.application;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import demo.entity.Employee;
// Persistent --> Detached --> Persistent --> Detached --> Removed
public class EmployeeDemo3 
{
	public static void main(String[] args) 
	{
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();
		
		try {
			Session session = factory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			
			Employee emp = session.get(Employee.class, 1); // emp in Persistent State
			System.out.println("\nEmployee : "+emp);
			
			session.detach(emp); // emp in Detached State
			
			emp.setEmpName("Debi Prasad"); // not reflected in DB
			System.out.println("\nEmployee : "+emp);
			
			session.update(emp); // Persistent State
			
			tx.commit();
			
			session.close(); // Detached State
			
			emp.finalize();
			emp=null;
			System.gc(); // emp in Removed State
			
		}finally {
			factory.close();
		}
	}
}
