package giaovusinhvien.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import giaovusinhvien.entity.Lop;
import giaovusinhvien.helpers.HibernateUtils;

public class LopDAO {
	public static List<Lop> getAll() {
        List<Lop> ds = new ArrayList<Lop>();
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        try {
            String hql = "FROM Lop";
            Query query = session.createQuery(hql);
            ds = query.list(); 
        } catch (HibernateException ex) {
            System.out.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
}
