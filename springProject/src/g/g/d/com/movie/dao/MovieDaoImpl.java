package g.g.d.com.movie.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import g.g.d.com.movie.service.MovieServiceImpl;
import org.apache.log4j.Logger;

import g.g.d.com.movie.vo.MovieListVO;
import g.g.d.com.movie.vo.MovieUserGenreVO;

@Repository
public class MovieDaoImpl implements MovieDao  {
	Logger logger = Logger.getLogger(MovieDaoImpl.class);
	@Autowired(required=false)
	private SqlSession session;
	
	@Override
	public List<MovieListVO> MovieSelectListDao(MovieUserGenreVO mugvo) {
		// TODO Auto-generated method stub
		logger.info("movie select start"+mugvo.getMvaction());
		return session.selectList("MovieSelectListService", mugvo);
	}

	@Override
	public int MovieInsertDao(MovieListVO mvlvo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int MovieCountDao(String mvnum) {
		logger.info("영화 네임>>>"+mvnum);
		return session.update("MovieCountService",mvnum);
	}
	@Override
	public List<MovieListVO> MovieRankDao(MovieListVO mvlvo){
		logger.info("영화랭크시작");
		return session.selectList("MovieRankService");
	}

}
