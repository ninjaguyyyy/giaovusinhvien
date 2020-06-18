package giaovusinhvien.entity;
import javax.persistence.*;


@Entity
@Table(name = "bangdiem")
public class BangDiem {
    @Id @GeneratedValue
    @Column(name = "id_diem")
    private int idDiem;
    
    @Column(name = "giuaki")
    private double giuaKi;
    
    @Column(name = "cuoiki")
    private double cuoiKi;
    
    @Column(name = "diemkhac")
    private double diemkhac;
    
    @Column(name = "diemtong")
    private double diemtong;
    
    @ManyToOne
    @JoinColumn(name="id_sv")
    private SinhVien sv;
    
    @ManyToOne
    @JoinColumn(name="id_mon")
    private Mon mon;

    public BangDiem() {
    }

    public int getIdDiem() {
        return idDiem;
    }

    public void setIdDiem(int idDiem) {
        this.idDiem = idDiem;
    }

    public double getGiuaKi() {
        return giuaKi;
    }

    public void setGiuaKi(double giuaKi) {
        this.giuaKi = giuaKi;
    }

    public double getCuoiKi() {
        return cuoiKi;
    }

    public void setCuoiKi(double cuoiKi) {
        this.cuoiKi = cuoiKi;
    }

    public double getDiemkhac() {
        return diemkhac;
    }

    public void setDiemkhac(double diemkhac) {
        this.diemkhac = diemkhac;
    }

    public double getDiemtong() {
        return diemtong;
    }

    public void setDiemtong(double diemtong) {
        this.diemtong = diemtong;
    }

    public SinhVien getSv() {
        return sv;
    }

    public void setSv(SinhVien sv) {
        this.sv = sv;
    }

    public Mon getMon() {
        return mon;
    }

    public void setMon(Mon mon) {
        this.mon = mon;
    }
    
    
}

