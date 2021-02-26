package g.g.d.com.review.service;

import java.util.List;

import g.g.d.com.review.vo.ReviewVO;

public interface ReviewService {

	public List<ReviewVO> reviewListAll(ReviewVO rvo);
	public List<ReviewVO> reviewList(ReviewVO rvo);
	public int reviewInsert(ReviewVO rvo);
	public int reviewUpdate(ReviewVO rvo);
	public int reviewDelete(ReviewVO rvo);
	public List<ReviewVO> reviewRating(ReviewVO rvo);
}
