package g.g.d.com.movie.service;

import java.util.List;
import g.g.d.com.movie.vo.MovieListVO;
import g.g.d.com.movie.vo.MovieUserGenreVO;

public interface MovieService {

	public List<MovieListVO> MovieSelectListService(MovieUserGenreVO mugvo);
	public int MovieInsertService(MovieListVO mvlvo);
	public int MovieCountService(String mvnum);
	public List<MovieListVO> MovieRankService(MovieListVO mvlvo);
	
}
