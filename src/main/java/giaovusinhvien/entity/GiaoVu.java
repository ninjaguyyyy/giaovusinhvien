package giaovusinhvien.entity;
import javax.persistence.*;

@Entity
@Table(name = "giaovu")
public class GiaoVu {
    @Id @GeneratedValue
    @Column(name = "id_gv")
    private int idGV;
    
    @Column(name = "hoten")
    private String hoTen;

    public GiaoVu() {
    }

    public int getIdGV() {
        return idGV;
    }

    public void setIdGV(int idGV) {
        this.idGV = idGV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    
}
