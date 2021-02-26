package g.g.d.com.movie.vo;

public class MovieListVO {

	private String mvnum;
	private String mvname;
	private String mvgenre;
	private String mvpubdate;
	
	public MovieListVO() {}

	public MovieListVO(String mvnum, String mvname, String mvgenre, String mvpubdate) {
		this.mvnum = mvnum;
		this.mvname = mvname;
		this.mvgenre = mvgenre;
		this.mvpubdate=mvpubdate;
	}
	
	public String getMvpubdate() {
		return mvpubdate;
	}
	
	public String getMvnum() {
		return mvnum;
	}

	public String getMvname() {
		return mvname;
	}

	public String getMvgenre() {
		return mvgenre;
	}

	public void setMvnum(String mvnum) {
		this.mvnum = mvnum;
	}

	public void setMvname(String mvname) {
		this.mvname = mvname;
	}

	public void setMvgenre(String mvgenre) {
		this.mvgenre = mvgenre;
	}
	
	public void setMvpubdate(String mvpubdate) {
		this.mvpubdate=mvpubdate;
	}
	
}
