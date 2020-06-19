package giaovusinhvien.entity;


import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "mon")
public class Mon {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_mon")
    private int idMon;
    
    @Column(name = "tenmon")
    private String tenMon;
    
    @Column(name = "phong")
    private String phong;
    
    @Column(name = "mamon")
    private String maMon;
    
    @ManyToOne
    @JoinColumn(name="id_lop")
    private Lop lop;
    
    public Mon() {
    }

    public int getIdMon() {
        return idMon;
    }

    public void setIdMon(int idMon) {
        this.idMon = idMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public Lop getLop() {
        return lop;
    }

    public void setLop(Lop lop) {
        this.lop = lop;
    }
    
}