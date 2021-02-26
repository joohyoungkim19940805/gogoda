package g.g.d.com.movie.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import g.g.d.com.movie.service.MovieServiceImpl;
import org.apache.log4j.Logger;

import g.g.d.com.movie.vo.MovieListVO;

@Repository
public class MovieDaoImpl implements MovieDao  {
	Logger logger = Logger.getLogger(MovieDaoImpl.class);
	@Autowired(required=false)
	private SqlSession session;
	
	@Override
	public List<MovieListVO> MovieSelectListDao(MovieListVO mvlvo) {
		// TODO Auto-generated method stub
		logger.info("movie select start"+mvlvo.getMvgenre());
		return session.selectList("MovieSelectListService", mvlvo);
	}

	@Override
	public int MovieInsertDao(MovieListVO mvlvo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
