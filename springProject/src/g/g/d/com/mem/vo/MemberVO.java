package g.g.d.com.mem.vo;

// VO(Value Object)
public class MemberVO {

	private String mnum;
	private String mname;
	private String mid;
	private String mpw;
	private String mbirth;
	private String mgender;
	private String mhp;
	private String memail;
	private String mzipcode;
	private String maddr;
	private String maddrdetail;
	private String mdeleteyn;
	private String minsertdate;
	private String mupdatedate;
	
	private String pagesize;
	private String groupsize;
	private String curpage;
	private String totalcount;
	
	public MemberVO() {}

	public MemberVO(String mnum, String mname, String mid, String mpw, String mbirth, String mgender, String mhp,
			String memail, String mzipcode, String maddr, String maddrdetail, String mdeleteyn, String minsertdate,
			String mupdatedate, String pagesize, String groupsize, String curpage, String totalcount) {
		super();
		this.mnum = mnum;
		this.mname = mname;
		this.mid = mid;
		this.mpw = mpw;
		this.mbirth = mbirth;
		this.mgender = mgender;
		this.mhp = mhp;
		this.memail = memail;
		this.mzipcode = mzipcode;
		this.maddr = maddr;
		this.maddrdetail = maddrdetail;
		this.mdeleteyn = mdeleteyn;
		this.minsertdate = minsertdate;
		this.mupdatedate = mupdatedate;
		this.pagesize = pagesize;
		this.groupsize = groupsize;
		this.curpage = curpage;
		this.totalcount = totalcount;
	}

	public String getMnum() {
		return mnum;
	}

	public void setMnum(String mnum) {
		this.mnum = mnum;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMpw() {
		return mpw;
	}

	public void setMpw(String mpw) {
		this.mpw = mpw;
	}

	public String getMbirth() {
		return mbirth;
	}

	public void setMbirth(String mbirth) {
		this.mbirth = mbirth;
	}

	public String getMgender() {
		return mgender;
	}

	public void setMgender(String mgender) {
		this.mgender = mgender;
	}

	public String getMhp() {
		return mhp;
	}

	public void setMhp(String mhp) {
		this.mhp = mhp;
	}

	public String getMemail() {
		return memail;
	}

	public void setMemail(String memail) {
		this.memail = memail;
	}

	public String getMzipcode() {
		return mzipcode;
	}

	public void setMzipcode(String mzipcode) {
		this.mzipcode = mzipcode;
	}

	public String getMaddr() {
		return maddr;
	}

	public void setMaddr(String maddr) {
		this.maddr = maddr;
	}

	public String getMaddrdetail() {
		return maddrdetail;
	}

	public void setMaddrdetail(String maddrdetail) {
		this.maddrdetail = maddrdetail;
	}

	public String getMdeleteyn() {
		return mdeleteyn;
	}

	public void setMdeleteyn(String mdeleteyn) {
		this.mdeleteyn = mdeleteyn;
	}

	public String getMinsertdate() {
		return minsertdate;
	}

	public void setMinsertdate(String minsertdate) {
		this.minsertdate = minsertdate;
	}

	public String getMupdatedate() {
		return mupdatedate;
	}

	public void setMupdatedate(String mupdatedate) {
		this.mupdatedate = mupdatedate;
	}

	public String getPagesize() {
		return pagesize;
	}

	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}

	public String getGroupsize() {
		return groupsize;
	}

	public void setGroupsize(String groupsize) {
		this.groupsize = groupsize;
	}

	public String getCurpage() {
		return curpage;
	}

	public void setCurpage(String curpage) {
		this.curpage = curpage;
	}

	public String getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(String totalcount) {
		this.totalcount = totalcount;
	}
}
