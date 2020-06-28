package demo.application;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import demo.entity.Employee;
// Transient --> Persistent --> Detached --> Persistent --> Transient --> Removed
public class EmployeeDemo5 
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
			
			session.detach(emp); // emp in Detached State
			
//			emp = session.get(Employee.class, emp.getId()); // emp in Persistent State
			session.saveOrUpdate(emp); // Applicable
//			session.merge(emp); // Not applicable // Error
			
			session.delete(emp); // emp in Transient State
			
			tx.commit();
			System.out.println("\nEmployee : "+emp);
			
			emp.finalize();
			emp = null;
			System.gc(); // emp in Removed State
			
		}finally {
			factory.close();
		}
	}
}
