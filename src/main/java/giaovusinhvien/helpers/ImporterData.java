package giaovusinhvien.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import giaovusinhvien.dao.LopDAO;
import giaovusinhvien.dao.SinhVienDAO;
import giaovusinhvien.entity.Lop;
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
            listSv.add(sv);
        }
	    SinhVienDAO.addMany(listSv);
		return true;
	}
}
