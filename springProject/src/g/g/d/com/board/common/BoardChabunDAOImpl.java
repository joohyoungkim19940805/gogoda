package g.g.d.com.board.common;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import g.g.d.com.board.vo.BoardVO;
import g.g.d.com.rboard.vo.RboardVO;;

@Repository
public class BoardChabunDAOImpl implements BoardChabunDAO {
	
	@Autowired(required=false)
	private SqlSessionTemplate sqlSession;
	
	@Override
	public BoardVO getBoardChabun() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("getBoardChabun");
	}

	@Override
	public RboardVO getRboardChabun() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("getRboardChabun");
	}
}
