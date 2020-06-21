package giaovusinhvien.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import giaovusinhvien.entity.GiaoVu;
import giaovusinhvien.entity.Lop;
import giaovusinhvien.entity.Mon;
import giaovusinhvien.entity.SinhVien;
import giaovusinhvien.helpers.HibernateUtils;

public class SinhVienDAO {
	public static void addMany(List<SinhVien> listSv) {
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        Transaction transaction = null;
        try {
        	transaction = session.beginTransaction();
        	for (SinhVien sv : listSv){
        	  session.save(sv);
        	}
        	transaction.commit();
        } catch (HibernateException ex) {
        	transaction.rollback();
            System.out.println(ex);
        } finally {
            session.close();
        }
    }
	public static void add(SinhVien sv) {
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        Transaction transaction = null;
        try {
        	transaction = session.beginTransaction();
        	session.save(sv);
        	transaction.commit();
        } catch (HibernateException ex) {
        	transaction.rollback();
            System.out.println(ex);
        } finally {
            session.close();
        }
    }
	
	public static SinhVien getByMssv(int mssv) {
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        Transaction transaction = null;
        try {
        	transaction = session.beginTransaction();
            String hql = "FROM SinhVien sv WHERE sv.mssv = :mssv";
            SinhVien sv = (SinhVien) session.createQuery(hql).setParameter("mssv", mssv).uniqueResult();
            if(sv != null) {
            	transaction.commit();
            	return sv;
            }
            transaction.commit();
        } catch (HibernateException ex) {
        	transaction.rollback();
            System.out.println(ex);
        } finally {
            session.close();
        }
        return null;
    }
	
	public static List<SinhVien> getByClass(String tenLop) {
        List<SinhVien> ds = new ArrayList<SinhVien>();
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        Transaction transaction = null;
        try {
        	transaction = session.beginTransaction();
            String hql = "FROM SinhVien sv WHERE sv.lop.tenLop = :tenLop";
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
	
	public static boolean checkLoginTrue(String mssv, String pass) {
		Session session = HibernateUtils.getSessionFactory()
                .openSession();
		SinhVien sv = null;
		Transaction transaction = null;
        try {
        	transaction = session.beginTransaction();
            String hql = "FROM SinhVien sv WHERE sv.mssv = :mssv";
            sv = (SinhVien) session.createQuery(hql).setParameter("mssv", Integer.parseInt(mssv)).uniqueResult();
            if (sv != null && sv.getPass().equals(pass)) {
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
	
	public static void update(SinhVien sv) {
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        Transaction transaction = null;
        try {
        	transaction = session.beginTransaction();
            session.update(sv);
            transaction.commit();
        } catch (HibernateException ex) {
        	transaction.rollback();
            System.out.println(ex);
        } finally {
            session.close();
        }
	}
	
}
