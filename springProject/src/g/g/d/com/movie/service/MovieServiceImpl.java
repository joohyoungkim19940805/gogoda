package g.g.d.com.movie.service;

import java.util.List;

import g.g.d.com.movie.vo.MovieListVO;
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
	public List<MovieListVO> MovieSelectListService(MovieListVO mvlvo) {
		// TODO Auto-generated method stub
		logger.info("무비 서비스 시작"+mvlvo.getMvgenre());
		List<MovieListVO>  movieList=null;
		movieList=movieDao.MovieSelectListDao(mvlvo);
		
		return movieList;
	}

	@Override
	public int MovieInsertService(MovieListVO mvlvo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
