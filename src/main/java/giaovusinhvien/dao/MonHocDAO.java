package giaovusinhvien.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import giaovusinhvien.entity.Lop;
import giaovusinhvien.entity.Mon;
import giaovusinhvien.helpers.HibernateUtils;

public class MonHocDAO {
	public static void addMany(List<Mon> listMon) {
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        Transaction transaction = null;
        try {
        	transaction = session.beginTransaction();
        	for (Mon mon : listMon){
        	  session.save(mon);
        	}
        	transaction.commit();
        } catch (HibernateException ex) {
        	transaction.rollback();
            System.out.println(ex);
        } finally {
            session.close();
        }
    }
	
	public static Mon getBySubName(String tenMon) {
        
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        Transaction transaction = null;
        try {
        	transaction = session.beginTransaction();
            String hql = "FROM Mon m WHERE m.tenMon = :tenMon";
            Mon mon = (Mon) session.createQuery(hql).setParameter("tenMon", tenMon).uniqueResult();
            if(mon != null) {
            	transaction.commit();
            	return mon;
            }
        } catch (HibernateException ex) {
        	transaction.rollback();
            System.out.println(ex);
        } finally {
            session.close();
        }
        return null;
    }
	
	public static List<Mon> getByClass(String tenLop) {
        List<Mon> ds = new ArrayList<Mon>();
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        Transaction transaction = null;
        try {
        	transaction = session.beginTransaction();
            String hql = "FROM Mon m WHERE m.lop.tenLop = :tenLop";
            Query query = session.createQuery(hql).setParameter("tenLop", tenLop);
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
}
