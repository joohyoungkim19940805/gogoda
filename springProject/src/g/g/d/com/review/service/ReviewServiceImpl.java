package g.g.d.com.review.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import g.g.d.com.review.dao.ReviewDAO;
import g.g.d.com.review.vo.ReviewVO;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
	Logger logger = Logger.getLogger(ReviewServiceImpl.class);
	
	@Autowired(required=false)
	private ReviewDAO reviewDAO;
	
	@Override
	public List<ReviewVO> reviewListAll(ReviewVO rvo) {
		// TODO Auto-generated method stub
		return reviewDAO.reviewListAll(rvo);
	}

	@Override
	public List<ReviewVO> reviewList(ReviewVO rvo) {
		// TODO Auto-generated method stub
		return reviewDAO.reviewList(rvo);
	}

	@Override
	public int reviewInsert(ReviewVO rvo) {
		// TODO Auto-generated method stub
		return reviewDAO.reviewInsert(rvo);
	}

	@Override
	public int reviewUpdate(ReviewVO rvo) {
		// TODO Auto-generated method stub
		return reviewDAO.reviewUpdate(rvo);
	}

	@Override
	public int reviewDelete(ReviewVO rvo) {
		// TODO Auto-generated method stub
		return reviewDAO.reviewDelete(rvo);
	}
	
	@Override
	public List<ReviewVO> reviewRating(ReviewVO rvo){
		logger.info("서비스 시작"+rvo.getKakaoid());
		return reviewDAO.reviewRating(rvo);
	}

}
