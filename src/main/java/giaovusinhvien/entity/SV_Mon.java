package giaovusinhvien.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "monhoc_sinhvien")
public class SV_Mon {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name = "id_chitiet")
		private int idSV_Mon;
		
		@ManyToOne
	    @JoinColumn(name="id_mon")
	    private Mon mon;
		
		@ManyToOne
	    @JoinColumn(name="id_sv")
	    private Lop sv;

		public SV_Mon() {
		}

		public int getIdSV_Mon() {
			return idSV_Mon;
		}

		public void setIdSV_Mon(int idSV_Mon) {
			this.idSV_Mon = idSV_Mon;
		}

		public Mon getMon() {
			return mon;
		}

		public void setMon(Mon mon) {
			this.mon = mon;
		}

		public Lop getSv() {
			return sv;
		}

		public void setSv(Lop sv) {
			this.sv = sv;
		}
		
		
}
