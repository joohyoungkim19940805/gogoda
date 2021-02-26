package g.g.d.com.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import g.g.d.com.board.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Autowired(required=false)
	private SqlSession session;
	
	// 글목록 구현
	@Override
	public List<BoardVO> boardList(BoardVO pvo) {
		return session.selectList("boardList");
	}
	
	// 글입력 구현
	@Override
	public int boardInsert(BoardVO pvo) {
		return session.insert("boardInsert",pvo);
	}
	
	// 글상세 구현
	@Override
	public BoardVO boardDetail(BoardVO pvo) {
		return (BoardVO)session.selectOne("boardDetail",pvo);
	}
	
	// 비밀번호 확인 구현
	@Override
	public int pwdConfirm(BoardVO pvo) {
		return (Integer)session.selectOne("pwdConfirm",pvo);
	}
	
	// 글수정 구현
	@Override
	public int boardUpdate(BoardVO pvo) {
		return session.update("boardUpdate",pvo);
	}
	
	// 전체 레코드 건수 구현
	@Override
	public int boardListCnt(BoardVO pvo) {
		return (Integer)session.selectOne("boardListCnt");
	}
	
	// 글삭제 구현
	@Override
	public int boardDelete(BoardVO pvo) {
		return (Integer)session.delete("boardDelete", pvo);
	}

	// 조회수 구현
	@Override
	public void boardHits(String bnum) {
		session.update("boardHits", bnum);
	}
}
