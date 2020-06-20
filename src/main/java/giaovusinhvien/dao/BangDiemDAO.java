package giaovusinhvien.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import giaovusinhvien.entity.BangDiem;
import giaovusinhvien.entity.Mon;
import giaovusinhvien.entity.SinhVien;
import giaovusinhvien.helpers.HibernateUtils;

public class BangDiemDAO {
	public static void addMany(List<BangDiem> listDiem) {
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        Transaction transaction = null;
        try {
        	transaction = session.beginTransaction();
        	for (BangDiem diem : listDiem){
        	  session.save(diem);
        	}
        	transaction.commit();
        } catch (HibernateException ex) {
        	transaction.rollback();
            System.out.println(ex);
        } finally {
            session.close();
        }
    }
	
	public static List<BangDiem> getBySub(Mon mon) {
        List<BangDiem> ds = new ArrayList<BangDiem>();
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        Transaction transaction = null;
        try {
        	transaction = session.beginTransaction();
            String hql = "FROM BangDiem bd WHERE bd.mon.idMon = :idMon";
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
