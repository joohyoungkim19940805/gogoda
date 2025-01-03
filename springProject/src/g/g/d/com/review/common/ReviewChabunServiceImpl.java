package g.g.d.com.review.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import g.g.d.com.review.common.ReviewChabunDAO;
import g.g.d.com.review.vo.ReviewVO;

@Service
@Transactional
public class ReviewChabunServiceImpl implements ReviewChabunService {

	@Autowired(required=false)
	private ReviewChabunDAO chabunDAO;
	
	@Override
	public ReviewVO getReviewChabun() {
		// TODO Auto-generated method stub
		return chabunDAO.getReviewChabun();
	}

}
