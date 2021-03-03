package g.g.d.com.movie.service;

import java.util.List;

import g.g.d.com.movie.vo.MovieListVO;
import g.g.d.com.movie.vo.MovieUserGenreVO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import g.g.d.com.movie.dao.MovieDao;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

	Logger logger = Logger.getLogger(MovieServiceImpl.class);
	
	@Autowired(required=false)
	private MovieDao movieDao;
	
	@Override
	public List<MovieListVO> MovieSelectListService(MovieUserGenreVO mugvo) {
		// TODO Auto-generated method stub
		logger.info("무비 서비스 시작"+mugvo.getMvaction());
		List<MovieListVO> movieList=null;
		movieList=movieDao.MovieSelectListDao(mugvo);
		
		return movieList;
	}

	@Override
	public int MovieInsertService(MovieListVO mvlvo) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int MovieCountService(String mvnum) {
		logger.info("무비랭킹서비스"+mvnum);
		int mvcnt=0;
		mvcnt=movieDao.MovieCountDao(mvnum);
		
		return mvcnt;
	}
	@Override
	public List<MovieListVO> MovieRankService(MovieListVO mvlvo){
		logger.info("무비 서비스 시작");
		List<MovieListVO>  movieListRank=null;
		movieListRank=movieDao.MovieRankDao(mvlvo);
		
		return movieListRank;
	}

}
