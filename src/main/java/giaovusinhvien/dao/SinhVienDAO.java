package giaovusinhvien.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import giaovusinhvien.entity.Lop;
import giaovusinhvien.entity.SinhVien;
import giaovusinhvien.helpers.HibernateUtils;

public class SinhVienDAO {
	public static void addMany(List<SinhVien> listSv) {
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        try {
        	for (SinhVien sv : listSv){
        	  session.save(sv);
        	}
        } catch (HibernateException ex) {
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
	public static ResultSet getAll() {
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        ResultSet ds = null;
        try {
            String hql = "FROM SinhVien";
            Query query = session.createQuery(hql);
             ds = (ResultSet) query.list(); 
        } catch (HibernateException ex) {
            System.out.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
}
