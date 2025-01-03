package g.g.d.com.review.vo;

import org.springframework.web.multipart.MultipartFile;

public class ReviewVO {

	private String kakaoid;
	private String renum;
	private String renickname;
	private String repass;
	private String recontent;
	private String rephoto;
	private String rerating;
	private String redeleteyn;
	private String reinsertdate;
	private String reupdatedate;
	private MultipartFile file;
	private String ratingavg;
	private String amount;
	
	
	public ReviewVO() {}
	
	
	
	public ReviewVO(String kakaoid, String renum, String renickname, String repass, String recontent, String rephoto,
			String rerating, String redeleteyn, String reinsertdate, String reupdatedate, MultipartFile file,
			String ratingavg, String amount) {
		super();
		this.kakaoid = kakaoid;
		this.renum = renum;
		this.renickname = renickname;
		this.repass = repass;
		this.recontent = recontent;
		this.rephoto = rephoto;
		this.rerating = rerating;
		this.redeleteyn = redeleteyn;
		this.reinsertdate = reinsertdate;
		this.reupdatedate = reupdatedate;
		this.file = file;
		this.amount = amount;
		this.ratingavg = ratingavg;
	}



	public MultipartFile getFile() {
		return file;
	}



	public String getRatingavg() {
		return ratingavg;
	}



	public void setRatingavg(String ratingavg) {
		this.ratingavg = ratingavg;
	}



	public String getAmount() {
		return amount;
	}



	public void setAmount(String amount) {
		this.amount = amount;
	}



	public void setFile(MultipartFile file) {
		this.file = file;
	}



	public String getKakaoid() {
		return kakaoid;
	}
	public void setKakaoid(String kakaoid) {
		this.kakaoid = kakaoid;
	}
	public String getRenum() {
		return renum;
	}
	public void setRenum(String renum) {
		this.renum = renum;
	}
	public String getRenickname() {
		return renickname;
	}
	public void setRenickname(String renickname) {
		this.renickname = renickname;
	}
	public String getRepass() {
		return repass;
	}
	public void setRepass(String repass) {
		this.repass = repass;
	}
	public String getRecontent() {
		return recontent;
	}
	public void setRecontent(String recontent) {
		this.recontent = recontent;
	}
	public String getRephoto() {
		return rephoto;
	}
	public void setRephoto(String rephoto) {
		this.rephoto = rephoto;
	}
	public String getRerating() {
		return rerating;
	}
	public void setRerating(String rerating) {
		this.rerating = rerating;
	}
	public String getRedeleteyn() {
		return redeleteyn;
	}
	public void setRedeleteyn(String redeleteyn) {
		this.redeleteyn = redeleteyn;
	}
	public String getReinsertdate() {
		return reinsertdate;
	}
	public void setReinsertdate(String reinsertdate) {
		this.reinsertdate = reinsertdate;
	}
	public String getReupdatedate() {
		return reupdatedate;
	}
	public void setReupdatedate(String reupdatedate) {
		this.reupdatedate = reupdatedate;
	}
	
	
}
