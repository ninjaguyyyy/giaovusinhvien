package giaovusinhvien.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import giaovusinhvien.entity.Mon;
import giaovusinhvien.helpers.HibernateUtils;

public class MonHocDAO {
	public static void addMany(List<Mon> listMon) {
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        try {
        	for (Mon mon : listMon){
        	  session.save(mon);
        	}
        } catch (HibernateException ex) {
            System.out.println(ex);
        } finally {
            session.close();
        }
    }
}
