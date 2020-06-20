package giaovusinhvien.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import giaovusinhvien.dao.BangDiemDAO;
import giaovusinhvien.dao.LopDAO;
import giaovusinhvien.dao.MonHocDAO;
import giaovusinhvien.dao.SinhVienDAO;
import giaovusinhvien.entity.BangDiem;
import giaovusinhvien.entity.Lop;
import giaovusinhvien.entity.Mon;
import giaovusinhvien.entity.SinhVien;

public class ImporterData {
	static BufferedReader inputFile;
    public ImporterData() {
		
    }
	public static boolean importLop(File fileInput, String tenLop) throws IOException {
		Lop lop = LopDAO.getByClassName(tenLop);
		List<SinhVien> listSv = new ArrayList<SinhVien>();
		inputFile = new BufferedReader(
	            new InputStreamReader(
	                new FileInputStream(fileInput), StandardCharsets.UTF_8
	            )
	    );
	    String line;
	    line = inputFile.readLine();
	    while ((line = inputFile.readLine()) != null) {
            String[] splitedLine = line.split(",");
            SinhVien sv = new SinhVien();
            sv.setMssv(Integer.parseInt(splitedLine[1]));
            sv.setHoTen(splitedLine[2]);
            sv.setGioiTinh(splitedLine[3]);
            sv.setCmnd(Integer.parseInt(splitedLine[4]));
            sv.setLop(lop);
            sv.setPass(splitedLine[1]);
            listSv.add(sv);
        }
	    SinhVienDAO.addMany(listSv);
		return true;
	}
	
	public static boolean importMon(File fileInput, String tenLop) throws IOException {
		Lop lop = LopDAO.getByClassName(tenLop);
		List<Mon> listMon = new ArrayList<Mon>();
		inputFile = new BufferedReader(
	            new InputStreamReader(
	                new FileInputStream(fileInput), StandardCharsets.UTF_8
	            )
	    );
	    String line;
	    line = inputFile.readLine();
	    while ((line = inputFile.readLine()) != null) {
            String[] splitedLine = line.split(",");
            Mon mon = new Mon();
            mon.setMaMon(splitedLine[1]);
            mon.setTenMon(splitedLine[2]);
            mon.setPhong(splitedLine[3]);
            mon.setLop(lop);
            listMon.add(mon);
        }
	    MonHocDAO.addMany(listMon);
		return true;
	}
	
	public static boolean importDiem(File fileInput, String tenLop, Mon mon) throws IOException {
		Lop lop = LopDAO.getByClassName(tenLop);
		List<BangDiem> listDiem = new ArrayList<BangDiem>();
		inputFile = new BufferedReader(
	            new InputStreamReader(
	                new FileInputStream(fileInput), StandardCharsets.UTF_8
	            )
	    );
	    String line;
	    line = inputFile.readLine();
	    while ((line = inputFile.readLine()) != null) {
            String[] splitedLine = line.split(",");
            BangDiem diem = new BangDiem();
            int mssv = Integer.parseInt(splitedLine[1]);
            SinhVien sv = SinhVienDAO.getByMssv(mssv);
            diem.setSv(sv);
            diem.setGiuaKi(Double.parseDouble(splitedLine[3]));
            diem.setCuoiKi(Double.parseDouble(splitedLine[4]));
            diem.setDiemkhac(Double.parseDouble(splitedLine[5]));
            diem.setDiemtong(Double.parseDouble(splitedLine[6]));
            diem.setMon(mon);
            listDiem.add(diem);
        }
	    BangDiemDAO.addMany(listDiem);
		return true;
	}
}
