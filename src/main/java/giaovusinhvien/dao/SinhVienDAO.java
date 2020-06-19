package giaovusinhvien.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
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
}
