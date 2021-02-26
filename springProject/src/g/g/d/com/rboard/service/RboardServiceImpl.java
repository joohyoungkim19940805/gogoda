package g.g.d.com.rboard.service;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import g.g.d.com.rboard.dao.RboardDAO;
import g.g.d.com.rboard.vo.RboardVO;

@Service
@Transactional
public class RboardServiceImpl implements RboardService {
	Logger logger = Logger.getLogger(RboardServiceImpl.class);
	
	@Autowired(required=false)
	private RboardDAO rboardDAO;
	
	// 글목록 구현
	@Override
	public List<RboardVO> rboardList(String bnum) {
		List<RboardVO> myList = null;
		myList = rboardDAO.rboardList(bnum);
		return myList;
	}
	
	// 글입력 구현
	@Override
	public int rboardInsert(RboardVO rvo) {
		int result = 0;
		result = rboardDAO.rboardInsert(rvo);
		return result;
	}
	
	// 글수정 구현
	@Override
	public int rboardUpdate(RboardVO rvo) {
		int result = 0;
		result = rboardDAO.rboardUpdate(rvo);
		return result;
	}
	
	// 글삭제 구현
	@Override
	public int rboardDelete(String rbnum) {
		int result = 0;
		result = rboardDAO.rboardDelete(rbnum);
		return result;
	}
}
