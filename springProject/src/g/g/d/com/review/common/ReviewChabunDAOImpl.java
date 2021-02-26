package g.g.d.com.review.common;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import g.g.d.com.review.vo.ReviewVO;

@Repository
public class ReviewChabunDAOImpl implements ReviewChabunDAO {
	@Autowired(required=false)
	private SqlSession sqlSession;
	
	@Override
	public ReviewVO getReviewChabun() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("getReviewChabun");
	}

}
