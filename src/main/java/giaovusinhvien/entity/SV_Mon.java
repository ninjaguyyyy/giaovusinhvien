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
	    private SinhVien sv;
		
		@Column(name = "actionStatus")
		private String actionStatus; // in | out
		
		public SV_Mon() {
		}

		public String getActionStatus() {
			return actionStatus;
		}

		public void setActionStatus(String actionStatus) {
			this.actionStatus = actionStatus;
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

		public SinhVien getSv() {
			return sv;
		}

		public void setSv(SinhVien sv) {
			this.sv = sv;
		}
		
		
}
