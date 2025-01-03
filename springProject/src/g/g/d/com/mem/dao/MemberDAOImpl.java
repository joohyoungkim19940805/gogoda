package g.g.d.com.mem.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import g.g.d.com.mem.vo.MemberVO;


@Repository
public class MemberDAOImpl implements MemberDAO{
	
	private Logger logger = Logger.getLogger(MemberDAOImpl.class);
	
	@Autowired
	private SqlSession session;

	@Override
	public int memberInsert(MemberVO mvo) {
		// TODO Auto-generated method stub
		logger.info("MemberDAOImpl memInsert >>> : ");
		logger.info("mvo >>> : " + mvo);
//		return session.insert("MemberDAO.memberInsert", mvo);
		return session.insert("memberInsert", mvo);
	}

	@Override
	public int memberUpdate(MemberVO mvo) {
		// TODO Auto-generated method stub
		logger.info("MemberDAOImpl memberUpdate  >>> : ");
		logger.info("mvo >>> : " + mvo.getMaddrdetail());
		
//		return session.update("MemberDAO.memberUpdate", mvo);
		return session.update("memberUpdate", mvo);
	}

	@Override
	public int memberDelete(MemberVO mvo) {
		// TODO Auto-generated method stub
		logger.info("MemberDAOImpl memberDelete >>> : ");
		logger.info("mvo >>> : " + mvo);
		
//		return (Integer)session.delete("MemberDAO.memberDelete", mvo);
		return (Integer)session.delete("memberDelete", mvo);
	}

	@Override
	public MemberVO memberSelect(MemberVO mvo) {
		// TODO Auto-generated method stub
		logger.info("MemberDAOImpl memberSelect >>> : ");
		logger.info("mvo >>> : " + mvo);
//		return (MemberVO)session.selectOne("MemberDAO.memberSelect", mvo);
		return (MemberVO)session.selectOne("memberSelect",mvo);
	}

	@Override
	public List<MemberVO> memberSelectAll(MemberVO mvo) {
		// TODO Auto-generated method stub
		
		logger.info("MemberDAOImpl memberSelectAll >>> : ");
		logger.info("mvo >>> : " + mvo);
//		return session.selectList("MemberDAO.memberSelectAll", mvo);
		return session.selectList("memberSelectAll");
	}

	@Override
	public List<MemberVO> idCheck(MemberVO mvo) {
		// TODO Auto-generated method stub
//		return session.selectList("MemberDAO.idCheck", mvo);
		return session.selectList("idCheck",mvo);
	}
/*	
	// 회원 정보 수정 및 삭제를 위한 pw 체크
	@Override
	public boolean pwCheck(String mid, String mpw) {
		// TODO Auto-generated method stub
		boolean result = false;
		Map<String, String> map = new HashMap<String, String>();
		map.put("mid", mid);
		map.put("mpw", mpw);
//		return session.selectOne("MemberDAO.pwCheck", map);
		int count = session.selectOne("pwCheck");
		if(count == 1) result = true;
		return result;
	}
	
*/
	@Override
	public int pwCheck(MemberVO mvo) {
		// TODO Auto-generated method stub
		logger.info("MemberDAOImpl pwCheck >>> : ");
		logger.info("mvo >>> : " + mvo);
		
		//boolean result = false;
		
		//int count = session.selectOne("pwCheck");
		//if(count == 1) result = true;
		//return result;
		//return count;
		return (Integer)session.selectOne("pwCheck", mvo);
	}	
	
	// 설문 조사 추가
	@Override
	public int surveyInsert(MemberVO mvo) {
		// TODO Auto-generated method stub
		logger.info("MemberDAOImpl surveyInsert >>> : ");
		logger.info("mvo >>> : " + mvo);
		logger.info("mvo.getMnum() >>> : " +  mvo.getMnum());
		logger.info("mvo.getMnum() >>> : " +  mvo.getPositivefood());
		logger.info("mvo.getMnum() >>> : " +  mvo.getNegativefood());
		logger.info("mvo.getMnum() >>> : " +  mvo.getMovietaste());
		
		return (Integer)session.update("surveyInsert",mvo);
	}

	@Override
	public MemberVO memberAddress(MemberVO mvo) {
		// TODO Auto-generated method stub
		logger.info("MemberDAOImpl memberAddress >>> : ");
		return (MemberVO)session.selectOne("memberAddress", mvo);
	}

}
