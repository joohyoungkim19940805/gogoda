package g.g.d.com.login.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.apache.log4j.Logger;

import g.g.d.com.board.vo.BoardVO;
import g.g.d.com.mem.vo.MemberVO;

@Repository
public class LoginDAOImpl implements LoginDAO {
	Logger logger = Logger.getLogger(LoginDAOImpl.class);
	@Autowired(required=false)
	private SqlSession session;
	
	@Override
	public MemberVO loginCompleteDao(MemberVO mvo) {
		logger.info("로그인 DAO 시작==="+mvo.getMpw()+mvo.getMid());
		return (MemberVO)session.selectOne("loginCompleteService", mvo);//서비스에 있는 해당 함수 입력
	}
	@Override
	public MemberVO loginFindIdDao(MemberVO mvo) {
		logger.info("아이디 찾기 시작"+mvo.getMname()+"<>"+mvo.getMemail());
		return (MemberVO)session.selectOne("loginFindIdService",mvo);
	}
	@Override
	public MemberVO loginFindPwDao(MemberVO mvo) {
		logger.info("비번 찾기 시작"+mvo.getMid()+"<>"+mvo.getMemail());
		return (MemberVO)session.selectOne("loginFindPwService",mvo);
	}
	
	

}
