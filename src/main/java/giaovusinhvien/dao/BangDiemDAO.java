package giaovusinhvien.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import giaovusinhvien.entity.BangDiem;
import giaovusinhvien.helpers.HibernateUtils;

public class BangDiemDAO {
	public static void addMany(List<BangDiem> listDiem) {
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        try {
        	for (BangDiem diem : listDiem){
        	  session.save(diem);
        	}
        } catch (HibernateException ex) {
            System.out.println(ex);
        } finally {
            session.close();
        }
    }
}
