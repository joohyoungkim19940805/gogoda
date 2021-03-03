package g.g.d.com.rboard.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import g.g.d.com.rboard.vo.RboardVO;

@Repository
public class RboardDAOImpl implements RboardDAO {
	
	@Autowired(required=false)
	private SqlSession session;
	
	@Override
	public int rboardCntDAO(String bnum) {
		System.out.println("================="+bnum);
		return session.update("rboardCntService", bnum);
	}
	
	// 글목록 구현
	@Override
	public List<RboardVO> rboardList(String bnum) {
		return session.selectList("rboardList", bnum);
	}
	
	// 글입력 구현
	@Override
	public int rboardInsert(RboardVO rvo) {
		return session.insert("rboardInsert",rvo);
	}
	
	// 글수정 구현
	@Override
	public int rboardUpdate(RboardVO rvo) {
		return session.update("rboardUpdate",rvo);
	}
	
	// 글삭제 구현
	@Override
	public int rboardDelete(String rbnum) {
		return session.delete("rboardDelete", rbnum);
	}

}
