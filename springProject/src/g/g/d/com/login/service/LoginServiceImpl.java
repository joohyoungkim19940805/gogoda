package g.g.d.com.login.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import g.g.d.com.login.dao.LoginDAO;
import g.g.d.com.mem.vo.MemberVO;



@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	Logger logger = Logger.getLogger(LoginServiceImpl.class);
	
	@Autowired(required=false)
	private LoginDAO loginDao;
	
	@Override
	public List<MemberVO> loginCompleteService(MemberVO mvo) {
		// TODO Auto-generated method stub
		logger.info("로그인서비스 시작==="+mvo.getMpw()+mvo.getMid());
		List<MemberVO> memberList=null;
		memberList=loginDao.loginCompleteDao(mvo);
		logger.info(memberList);
		return memberList;
	}
	
}
