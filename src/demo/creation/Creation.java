package demo.creation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import demo.entity.Employee; 

public class Creation 
{
	public static void main(String[] args) 
	{
		// Creating the factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();
		
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml"); //we can simply say cfg.configure() and it will look for the file in your classpath by default
		cfg.addAnnotatedClass(Employee.class); // either add the mapping in cfg file else do this
		SessionFactory factory1 = cfg.buildSessionFactory();
		
		// Opening a Session
		Session session = factory.openSession(); // Manual closing
		Session session2 = factory.getCurrentSession(); // Auto closing
		
		// Beginning a transaction
		session.getTransaction().begin();
		Transaction tx = session.beginTransaction();
		
		// Committing a transaction
		tx.commit();
		session.getTransaction().commit();
		
		// Creating a Transaction Directly
		Transaction transaction = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory().getCurrentSession().beginTransaction();
		
		transaction.commit();
		
	}
}
