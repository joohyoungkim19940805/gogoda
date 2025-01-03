package g.g.d.com.board.vo;

import org.springframework.web.multipart.MultipartFile;

import g.g.d.com.board.common.BoardCommonVO;

public class BoardVO extends BoardCommonVO{
	
	private String bnum;		// 글번호	
	private String bsubject;	// 제목
	private String bcontent;	// 내용
	private String bname;		// 작성자
	private String bpw;			// 비밀번호
	private String bdelyn;		// 삭제여부
	private String binsertdate;	// 작성일
	private String bupdatedate;	// 수정일
	
	// 파일 업로드를 위한 속성
	private MultipartFile file;	// 첨부파일
	private String bfile;		// 실제 서버에 저장한 파일명
	
	
	// 조회수를 위한 속성
	private int bhit;
	
	public BoardVO(int bhit) {
	
		this.bhit = bhit;
	}

	public int getBhit() {
		return bhit;
	}

	public void setBhit(int bhit) {
		this.bhit = bhit;
	}
	
	private int cnt;

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	public BoardVO() {
		
	}
	
	public BoardVO(String bnum, String bsubject, String bcontent, String bname, String bpw, String bdelyn,
			String binsertdate, String bupdatedate, MultipartFile file, String bfile, int cnt) {
		
		this.bnum = bnum;
		this.bsubject = bsubject;
		this.bcontent = bcontent;
		this.bname = bname;
		this.bpw = bpw;
		this.bdelyn = bdelyn;
		this.binsertdate = binsertdate;
		this.bupdatedate = bupdatedate;
		this.file = file;
		this.bfile = bfile;
		this.cnt=cnt;
	}

	public String getBnum() {
		return bnum;
	}

	public void setBnum(String bnum) {
		this.bnum = bnum;
	}

	public String getBsubject() {
		return bsubject;
	}

	public void setBsubject(String bsubject) {
		this.bsubject = bsubject;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getBpw() {
		return bpw;
	}

	public void setBpw(String bpw) {
		this.bpw = bpw;
	}

	public String getBdelyn() {
		return bdelyn;
	}

	public void setBdelyn(String bdelyn) {
		this.bdelyn = bdelyn;
	}

	public String getBinsertdate() {
		return binsertdate;
	}

	public void setBinsertdate(String binsertdate) {
		this.binsertdate = binsertdate;
	}

	public String getBupdatedate() {
		return bupdatedate;
	}

	public void setBupdatedate(String bupdatedate) {
		this.bupdatedate = bupdatedate;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getBfile() {
		return bfile;
	}

	public void setBfile(String bfile) {
		this.bfile = bfile;
	}
}
	
	