package giaovusinhvien.entity;
import javax.persistence.*;


@Entity
@Table(name = "lop")
public class Lop {
    @Id @GeneratedValue
   @Column(name = "id_lop")
   private int idLop;
    
    @Column(name = "tenlop")
   private String tenLop;

    public Lop() {
    }

    public int getIdLop() {
        return idLop;
    }

    public void setIdLop(int idLop) {
        this.idLop = idLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }
    

}
