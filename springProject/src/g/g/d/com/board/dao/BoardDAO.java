package g.g.d.com.board.dao;

import java.util.List;

import g.g.d.com.board.vo.BoardVO;

public interface BoardDAO {
	
	public List<BoardVO> boardList(BoardVO pvo);
	public int boardListCnt(BoardVO pvo);
	public int boardInsert(BoardVO pvo);
	public BoardVO boardDetail(BoardVO pvo);
	public int pwdConfirm(BoardVO pvo);
	public int boardUpdate(BoardVO pvo);
	public int boardDelete(BoardVO pvo);
	public void boardHits(String bnum);
}
