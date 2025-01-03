package g.g.d.com.review.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import g.g.d.com.review.controller.ReviewController;
import g.g.d.com.review.vo.ReviewVO;

@Repository
public class ReviewDAOImpl implements ReviewDAO {
	Logger logger = Logger.getLogger(ReviewDAOImpl.class);
	
	@Autowired(required=false)
	private SqlSession sqlSession;
	
	@Override
	public List<ReviewVO> reviewListAll(ReviewVO rvo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("reviewListAll", rvo);
	}

	@Override
	public List<ReviewVO> reviewList(ReviewVO rvo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("reviewList", rvo);
	}

	@Override
	public int reviewInsert(ReviewVO rvo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("reviewInsert", rvo);
	}

	@Override
	public int reviewUpdate(ReviewVO rvo) {
		// TODO Auto-generated method stub
		return sqlSession.update("reviewUpdate", rvo);
	}

	@Override
	public int reviewDelete(ReviewVO rvo) {
		// TODO Auto-generated method stub
		return sqlSession.update("reviewDelete", rvo);
	}

	@Override
	public List<ReviewVO> reviewRating(ReviewVO rvo){
		logger.info("DAO 시작"+rvo.getKakaoid());
		return sqlSession.selectList("reviewRating", rvo);
	}
}
