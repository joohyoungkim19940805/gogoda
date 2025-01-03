package g.g.d.com.board.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import g.g.d.com.board.dao.BoardDAO;
import g.g.d.com.board.vo.BoardVO;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {
	Logger logger = Logger.getLogger(BoardServiceImpl.class);
	
	@Autowired(required=false)
	private BoardDAO boardDAO;
	
	@Override
	public List<BoardVO> boardListAllService(BoardVO bvo){
		List<BoardVO> boardList=null;
		boardList=boardDAO.boardListAllDao(bvo);
		return boardList;
	}
	
	// 글목록 구현
	@Override
	public List<BoardVO> boardList(BoardVO bvo) {
		logger.info(bvo.getSearch()+"서비스<<<<<<<<<");
		List<BoardVO> myList = null;
		myList = boardDAO.boardList(bvo);
		return myList;
	}
	
	// 글입력 구현
	@Override
	public int boardInsert(BoardVO pvo) {
		int result =0;
		result = boardDAO.boardInsert(pvo);
		return result;
	}
	
	// 글상세 구현
	@Override
	public BoardVO boardDetail(BoardVO pvo) {
		
		BoardVO detail = null;
		detail = boardDAO.boardDetail(pvo);
		return detail;
	}
	
	// 비밀번호 확인 구현
	@Override
	public int pwdConfirm(BoardVO pvo) {
		int result = 0;
		result = boardDAO.pwdConfirm(pvo);
		return result;
	}
	
	// 글수정 구현
	@Override
	public int boardUpdate(BoardVO pvo) {
		int result = 0;
		result = boardDAO.boardUpdate(pvo);
		return result;
	}
	
	
	// 전체 레코드 수 구현
	@Override
	public int boardListCnt(BoardVO pvo) {
		return boardDAO.boardListCnt(pvo);
	}
	
	// 글삭제 구현
	@Override
	public int boardDelete(BoardVO pvo) {
		return boardDAO.boardDelete(pvo);
	}
	
	// 조회수 구현
	@Override
	public void boardHits(String bnum) {
		boardDAO.boardHits(bnum);
	}
}
