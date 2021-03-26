package g.g.d.com.mem.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import g.g.d.com.mem.dao.MemberDAO;
import g.g.d.com.mem.vo.MemberVO;

// 현 클래스 스프링에서 관리하는 service bean 으로 등록
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	private Logger logger = Logger.getLogger(MemberServiceImpl.class);

	@Autowired(required=false)
	private MemberDAO memberDAO;
	
	// 회원 등록
	@Override
	public int memberInsert(MemberVO mvo) {
		// TODO Auto-generated method stub
		logger.info("MemberServiceImpl memberInsert >>> : ");
		logger.info("mvo >>> : " + mvo);
		
		int result =0;
		result = memberDAO.memberInsert(mvo);
		return result;
	}
	// 회원 정보 수정
	@Override
	public int memberUpdate(MemberVO mvo) {
		// TODO Auto-generated method stub
		logger.info("MemberServiceImpl memUpdate >>> : ");
		logger.info("mvo >>> : " + mvo.getMnum());
		
		int result = 0;
		result = memberDAO.memberUpdate(mvo);
		return result;
	}
	// 회원 정보 삭제
	@Override
	public int memberDelete(MemberVO mvo) {
		// TODO Auto-generated method stub
		logger.info("MemberServiceImpl memberDelete >>> : ");
		logger.info("mvo >>> : " + mvo);
		
		return memberDAO.memberDelete(mvo);
	}
	// 회원 정보 상세 조회
	@Override
	public MemberVO memberSelect(MemberVO mvo) {
		// TODO Auto-generated method stub
		logger.info("MemberServiceImpl memberSelect >>> : ");
		logger.info("mvo >>> : " + mvo);
		
		MemberVO detail = null;
		detail = memberDAO.memberSelect(mvo);
		return detail;
	}
	// 전체 회원 조회
	@Override
	public List<MemberVO> memberSelectAll(MemberVO mvo) {
		// TODO Auto-generated method stub
		logger.info("MemberServiceImpl memberSelectAll >>> : ");
		logger.info("mvo >>> : " + mvo);
		
		List<MemberVO> memList = null;
		memList = memberDAO.memberSelectAll(mvo);
		return memList;
	}
	// 아이디 중복 체크
	@Override
	public List<MemberVO> idCheck(MemberVO mvo) {
		// TODO Auto-generated method stub
		logger.info("mvo >>> : " + mvo.getMid());
		return memberDAO.idCheck(mvo);
	}
	// 회원 정보 수정 및 삭제를 위한 pw 체크
	@Override
	public int pwCheck(MemberVO mvo) {
		// TODO Auto-generated method stub
		logger.info("MemberServiceImpl pwCheck >>> : ");
		logger.info("mvo >>> : " + mvo);
		
		int result = 0;
		result = memberDAO.pwCheck(mvo);
		return result;
	}
	
	// 설문 조사 추가
	@Override
	public int surveyInsert(MemberVO mvo) {
		// TODO Auto-generated method stub
		logger.info("MemberServiceImpl surveyInsert >>> : ");
		logger.info("mvo >>> : " + mvo);
		
		int result = 0;
		result = memberDAO.surveyInsert(mvo);
		return result;
	}
	@Override
	public MemberVO memberAddress(MemberVO mvo) {
		// TODO Auto-generated method stub
		logger.info("MemberServiceImpl memberAddress >>>> : ");
		
		return memberDAO.memberAddress(mvo);
	}
}
