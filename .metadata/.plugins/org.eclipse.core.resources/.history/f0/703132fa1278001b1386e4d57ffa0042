package g.g.d.com.board.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import g.g.d.com.board.vo.BoardVO;
import g.g.d.com.board.common.BoardChabunDAO;
import g.g.d.com.rboard.vo.RboardVO;

@Service
@Transactional
public class BoardChabunServiceImpl implements BoardChabunService {
	
	private BoardChabunDAO chabunDAO;
	
	@Autowired(required=false)
	public BoardChabunServiceImpl(BoardChabunDAO chabunDAO) {
		this.chabunDAO = chabunDAO;
	}
	
	@Override
	public BoardVO getBoardChabun() {
		// TODO Auto-generated method stub
		return chabunDAO.getBoardChabun();
	}

	@Override
	public RboardVO getRboardChabun() {
		// TODO Auto-generated method stub
		return chabunDAO.getRboardChabun();
	}
}
