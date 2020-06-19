package giaovusinhvien.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import giaovusinhvien.entity.GiaoVu;
import giaovusinhvien.helpers.HibernateUtils;

public class GiaoVuDAO {
	public static boolean checkLoginTrue(String username, String pass) {
		Session session = HibernateUtils.getSessionFactory()
                .openSession();
		GiaoVu gv = null;
        try {
            String hql = "FROM GiaoVu gv WHERE gv.username = :username";
            gv = (GiaoVu) session.createQuery(hql).setParameter("username", username).uniqueResult();
            if (gv != null && gv.getPass().equals(pass)) {
                return true;
            }
        } catch (HibernateException ex) {
            System.out.println(ex);
        } finally {
            session.close();
        }
		return false;
	}
}
