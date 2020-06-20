package giaovusinhvien.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import giaovusinhvien.entity.Lop;
import giaovusinhvien.helpers.HibernateUtils;

public class LopDAO {
	public static List<Lop> getAll() {
        List<Lop> ds = new ArrayList<Lop>();
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        Transaction transaction = null;
        try {
        	transaction = session.beginTransaction();
            String hql = "FROM Lop";
            Query query = session.createQuery(hql);
            ds = query.list(); 
            transaction.commit();
        } catch (HibernateException ex) {
        	transaction.rollback();
            System.out.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
	public static Lop getByClassName(String tenLop) {
        
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        Transaction transaction = null;
        try {
        	transaction = session.beginTransaction();
            String hql = "FROM Lop l WHERE l.tenLop = :tenLop";
            Lop lop = (Lop) session.createQuery(hql).setParameter("tenLop", tenLop).uniqueResult();
            if(lop != null) {
            	transaction.commit();
            	return lop;
            }
        } catch (HibernateException ex) {
        	transaction.rollback();
            System.out.println(ex);
        } finally {
            session.close();
        }
        return null;
    }
}
