package g.g.d.com.mem.common;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import g.g.d.com.mem.common.ChabunDAO;
import g.g.d.com.mem.vo.MemberVO;


@Service
@Transactional
public class ChabunServiceImpl implements ChabunService {
	private Logger logger = Logger.getLogger(ChabunServiceImpl.class);
	
	private ChabunDAO chabunDAO;
	
	@Autowired(required=false)
	public ChabunServiceImpl(ChabunDAO chabunDAO) {
		this.chabunDAO = chabunDAO;
	}
	
	@Override
	public MemberVO getChabun() {
		// TODO Auto-generated method stub

		MemberVO mvo = chabunDAO.getChabun();
		
		return mvo;
	}
}
