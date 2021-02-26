package g.g.d.com.rboard.service;

import java.util.List;

import g.g.d.com.rboard.vo.RboardVO;

public interface RboardService {
	
	public List<RboardVO> rboardList(String bnum);
	public int rboardInsert(RboardVO rvo);
	public int rboardUpdate(RboardVO rvo);
	public int rboardDelete(String rbnum);
}
