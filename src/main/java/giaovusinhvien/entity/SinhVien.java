package giaovusinhvien.entity;

import java.util.List;
import javax.persistence.*;



@Entity
@Table(name = "sinhvien")
public class SinhVien {
		@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name = "id_sv")
		private int idSV;
    
		@Column(name = "mssv")
		private int mssv;
		
		@Column(name = "hoten")
		private String hoTen;
		
		@Column(name = "gioitinh")
		private String gioiTinh;
		
	    @Column(name = "cmnd")
	    private int cmnd;
	    
	    @Column(name = "pass")
	    private String pass;
	    
	    @ManyToOne
	    @JoinColumn(name="id_lop")
	    private Lop lop;

	    public SinhVien() {
	    }
	
	    public String getPass() {
			return pass;
		}
	
		public void setPass(String pass) {
			this.pass = pass;
		}
	
		public int getIdSV() {
	        return idSV;
	    }
	
	    public void setIdSV(int idSV) {
	        this.idSV = idSV;
	    }
	
	    public int getMssv() {
	        return mssv;
	    }
	
	    public void setMssv(int mssv) {
	        this.mssv = mssv;
	    }
	
	    public String getHoTen() {
	        return hoTen;
	    }
	
	    public void setHoTen(String hoTen) {
	        this.hoTen = hoTen;
	    }
	
	    public String getGioiTinh() {
	        return gioiTinh;
	    }
	
	    public void setGioiTinh(String gioiTinh) {
	        this.gioiTinh = gioiTinh;
	    }
	
	    public int getCmnd() {
	        return cmnd;
	    }
	
	    public void setCmnd(int cmnd) {
	        this.cmnd = cmnd;
	    }
	
	    public Lop getLop() {
	        return lop;
	    }
	
	    public void setLop(Lop lop) {
	        this.lop = lop;
	    }
    
    
}

