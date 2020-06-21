package giaovusinhvien.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import giaovusinhvien.entity.Mon;
import giaovusinhvien.entity.SV_Mon;
import giaovusinhvien.helpers.HibernateUtils;

public class SV_Mon_DAO {
	public static SV_Mon layThongTinSVMon(SV_Mon svm) {
		Session session = HibernateUtils.getSessionFactory()
                .openSession();
        Transaction transaction = null;
        SV_Mon svmIns = null;
        try {
        	transaction = session.beginTransaction();
        	String hql = "FROM SV_Mon svm WHERE svm.mon.idMon = :idMon and svm.sv.idSV = :idSV";
        	svmIns = (SV_Mon) session.createQuery(hql).setParameter("idMon", svm.getMon().getIdMon())
            		.setParameter("idSV", svm.getSv().getIdSV())
            		.uniqueResult();
        	transaction.commit();
        } catch (HibernateException ex) {
        	transaction.rollback();
            System.out.println(ex);
        } finally {
            session.close();
        }
        return svmIns;
	}
	public static boolean add(SV_Mon sv_mon) {
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        Transaction transaction = null;
        if(SV_Mon_DAO.layThongTinSVMon(sv_mon) != null) {
        	return false;
        }
        try {
        	transaction = session.beginTransaction();
        	session.save(sv_mon);
        	transaction.commit();
        } catch (HibernateException ex) {
        	transaction.rollback();
            System.out.println(ex);
        } finally {
            session.close();
        }
        return true;
    }
	
	public static List<SV_Mon> getBySub(Mon mon) {
        List<SV_Mon> ds = new ArrayList<SV_Mon>();
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        Transaction transaction = null;
        try {
        	transaction = session.beginTransaction();
            String hql = "FROM SV_Mon svm WHERE svm.mon.idMon = :idMon";
            Query query = session.createQuery(hql).setParameter("idMon", mon.getIdMon());
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
