package giaovusinhvien.helpers;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static final SessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml")
            		.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null){
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        }
        return sessionFactory;
    }
	
}
