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
	public MemberVO loginCompleteService(MemberVO mvo) {
		// TODO Auto-generated method stub
		logger.info("로그인서비스 시작==="+mvo.getMpw()+mvo.getMid());
		MemberVO memberData=null;
		memberData=loginDao.loginCompleteDao(mvo);
		logger.info(memberData);
		return memberData;
	}
	@Override
	public MemberVO loginFindIdService(MemberVO mvo) {
		
		logger.info("아이디찾기 서비스 시ㅈ"+mvo.getMname()+"<>"+mvo.getMemail());
		MemberVO findIdData=null;
		findIdData=loginDao.loginFindIdDao(mvo);
		logger.info(findIdData);
		return findIdData;
	}
	@Override
	public MemberVO loginFindPwService(MemberVO mvo) {
		logger.info("아이디찾기 서비스 시ㅈ"+mvo.getMid()+"<>"+mvo.getMemail());
		MemberVO findPwData=null;
		findPwData=loginDao.loginFindIdDao(mvo);
		logger.info(findPwData);
		return findPwData;
	}
}
