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

    @Column(name = "username")
    private String username;

    @Column(name = "pass")
    private String pass;

    public GiaoVu() {
    }

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
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
