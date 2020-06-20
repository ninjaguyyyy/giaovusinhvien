package giaovusinhvien.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import giaovusinhvien.entity.BangDiem;
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
}
