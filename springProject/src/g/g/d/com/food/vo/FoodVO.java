package g.g.d.com.food.vo;

public class FoodVO {

	private String fnum;
	private String findex;
	private String fname;
	private String foneserving;
	private String fkcal;
	private String fbitterness;
	private String fumami;
	private String fsalty;
	private String fsweetness;
	private String fsourtaste;
	private String fsweetandsalty;
	private String fsource;
	private int foodcnt;
	private String foodtype;
	
	
	public FoodVO(String fnum,String findex,String fname,
					String foneserving,String fkcal,String fbitterness,
					String fumami,String fsalty,String fsweetness,
					String fsourtaste,String fsweetandsalty,String fsource, int foodcnt, String foodtype) {
		this.fnum=fnum;
		this.findex=findex;
		this.fname=fname;
		this.foneserving=foneserving;
		this.fkcal=fkcal;
		this.fbitterness=fbitterness;
		this.fumami=fumami;
		this.fsalty=fsalty;
		this.fsweetness=fsweetness;
		this.fsourtaste=fsourtaste;
		this.fsweetandsalty=fsweetandsalty;
		this.fsource=fsource;
		this.foodcnt=foodcnt;
		this.foodtype=foodtype;
	}
	
	public FoodVO() {}

	public void setFoodtype(String foodtype) {
		this.foodtype=foodtype;
	}
	
	public String getFoodtype() {
		return foodtype;
	}
	
	public void setFoodcnt(int foodcnt) {
		this.foodcnt = foodcnt;
	}
	
	public int getFoodcnt() {
		return foodcnt;
	}

	public String getFnum() {
		return fnum;
	}


	public String getFindex() {
		return findex;
	}


	public String getFname() {
		return fname;
	}


	public String getFoneserving() {
		return foneserving;
	}


	public String getFkcal() {
		return fkcal;
	}


	public String getFbitterness() {
		return fbitterness;
	}


	public String getFumami() {
		return fumami;
	}


	public String getFsalty() {
		return fsalty;
	}


	public String getFsweetness() {
		return fsweetness;
	}


	public String getFsourtaste() {
		return fsourtaste;
	}


	public String getFsweetandsalty() {
		return fsweetandsalty;
	}


	public String getFsource() {
		return fsource;
	}


	public void setFnum(String fnum) {
		this.fnum = fnum;
	}


	public void setFindex(String findex) {
		this.findex = findex;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public void setFoneserving(String foneserving) {
		this.foneserving = foneserving;
	}


	public void setFkcal(String fkcal) {
		this.fkcal = fkcal;
	}


	public void setFbitterness(String fbitterness) {
		this.fbitterness = fbitterness;
	}


	public void setFumami(String fumami) {
		this.fumami = fumami;
	}


	public void setFsalty(String fsalty) {
		this.fsalty = fsalty;
	}


	public void setFsweetness(String fsweetness) {
		this.fsweetness = fsweetness;
	}


	public void setFsourtaste(String fsourtaste) {
		this.fsourtaste = fsourtaste;
	}


	public void setFsweetandsalty(String fsweetandsalty) {
		this.fsweetandsalty = fsweetandsalty;
	}


	public void setFsource(String fsource) {
		this.fsource = fsource;
	}
	
}
