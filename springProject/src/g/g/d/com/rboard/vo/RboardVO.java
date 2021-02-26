package g.g.d.com.rboard.vo;

public class RboardVO {
	
	private String rbnum;		// 댓글번호
	private String bnum;		// 글번호
	private String rbname;		// 작성자
	private String rbcontent;	// 내용
	private String rbpw;		// 비밀번호
	private String rbdate;		// 작성일

	public RboardVO() {
		
	}

	public RboardVO(String rbnum, String bnum, String rbname, String rbcontent, String rbpw, String rbdate) {
		
		this.rbnum = rbnum;
		this.bnum = bnum;
		this.rbname = rbname;
		this.rbcontent = rbcontent;
		this.rbpw = rbpw;
		this.rbdate = rbdate;
	}

	public String getRbnum() {
		return rbnum;
	}

	public void setRbnum(String rbnum) {
		this.rbnum = rbnum;
	}

	public String getBnum() {
		return bnum;
	}

	public void setBnum(String bnum) {
		this.bnum = bnum;
	}

	public String getRbname() {
		return rbname;
	}

	public void setRbname(String rbname) {
		this.rbname = rbname;
	}

	public String getRbcontent() {
		return rbcontent;
	}

	public void setRbcontent(String rbcontent) {
		this.rbcontent = rbcontent;
	}

	public String getRbpw() {
		return rbpw;
	}

	public void setRbpw(String rbpw) {
		this.rbpw = rbpw;
	}

	public String getRbdate() {
		return rbdate;
	}

	public void setRbdate(String rbdate) {
		this.rbdate = rbdate;
	}
}
