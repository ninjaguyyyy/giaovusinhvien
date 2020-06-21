package giaovusinhvien.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import giaovusinhvien.entity.BangDiem;
import giaovusinhvien.entity.GiaoVu;
import giaovusinhvien.entity.Lop;
import giaovusinhvien.helpers.HibernateUtils;

public class GiaoVuDAO {
	public static boolean checkLoginTrue(String username, String pass) {
		Session session = HibernateUtils.getSessionFactory()
                .openSession();
		Transaction transaction = null;
		GiaoVu gv = null;
        try {
        	transaction = session.beginTransaction();
            String hql = "FROM GiaoVu gv WHERE gv.username = :username";
            gv = (GiaoVu) session.createQuery(hql).setParameter("username", username).uniqueResult();
            if (gv != null && gv.getPass().equals(pass)) {
            	transaction.commit();
                return true;
            }
        } catch (HibernateException ex) {
        	transaction.rollback();
            System.out.println(ex);
        } finally {
            session.close();
        }
		return false;
	}
	
	public static void update(GiaoVu giaovu) {
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        Transaction transaction = null;
        try {
        	transaction = session.beginTransaction();
            session.update(giaovu);
            transaction.commit();
        } catch (HibernateException ex) {
        	transaction.rollback();
            System.out.println(ex);
        } finally {
            session.close();
        }
	}
	
	public static GiaoVu getByUsername(String username) {
        
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        Transaction transaction = null;
        try {
        	transaction = session.beginTransaction();
            String hql = "FROM GiaoVu gv WHERE gv.username = :username";
            GiaoVu gv = (GiaoVu) session.createQuery(hql).setParameter("username", username).uniqueResult();
            if(gv != null) {
            	transaction.commit();
            	return gv;
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
