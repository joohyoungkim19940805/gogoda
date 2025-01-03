package g.g.d.com.movie.vo;

public class MovieVO {
	
	private String mvnum;
	private String mvname;
	private String mvlink;
	private String mvimage;
	private String mvpubDate;
	private String mvdirector;
	private String mvactor;
	private String mvuserRating;
	private String mvgenre;
	private String mvinsertdate;
	private int moviecnt;
	public MovieVO() {}
	
	public MovieVO(String mvname, String mvlink, String mvimage,
					String mvpubDate, String mvdirector,
					String mvactor, String mvuserRating, String mvgenre, 
					String mvnum, String mvinsertdate, int moviecnt) {
		this.mvname = mvname;
		this.mvlink = mvlink;
		this.mvimage = mvimage;
		this.mvpubDate = mvpubDate;
		this.mvdirector = mvdirector;
		this.mvactor = mvactor;
		this.mvuserRating = mvuserRating;
		this.mvgenre=mvgenre;
		this.mvnum=mvnum;
		this.mvinsertdate=mvinsertdate;
		this.moviecnt=moviecnt;
	}
	public int getMoviecnt() {
		return moviecnt;
	}
	public void setMoviecnt(int moviecnt) {
		this.moviecnt=moviecnt;
	}
	public String getMvinsertdate() {
		return mvinsertdate;
	}
	public String getMvnum() {
		return mvnum;
	}
	public String getMvgenre() {
		return mvgenre;
	}
	public String getMvname() {
		return mvname;
	}
	public String getMvlink() {
		return mvlink;
	}
	public String getMvimage() {
		return mvimage;
	}
	public String getMvpubDate() {
		return mvpubDate;
	}
	public String getMvdirector() {
		return mvdirector;
	}
	public String getMvactor() {
		return mvactor;
	}
	public String getMvuserRating() {
		return mvuserRating;
	}
	public void setMvname(String mvname) {
		this.mvname = mvname;
	}
	public void setMvlink(String mvlink) {
		this.mvlink = mvlink;
	}
	public void setMvimage(String mvimage) {
		this.mvimage = mvimage;
	}
	public void setMvpubDate(String mvpubDate) {
		this.mvpubDate = mvpubDate;
	}
	public void setMvdirector(String mvdirector) {
		this.mvdirector = mvdirector;
	}
	public void setMvactor(String mvactor) {
		this.mvactor = mvactor;
	}
	public void setMvuserRating(String mvuserRating) {
		this.mvuserRating = mvuserRating;
	}
	public void setMvgenre(String mvgenre) {
		this.mvgenre=mvgenre;
	}
	public void setMvnum(String mvnum) {
		this.mvnum=mvnum;
	}
	public void setMvinsertdate(String mvinsertdate) {
		this.mvinsertdate=mvinsertdate;
	}

	
}
