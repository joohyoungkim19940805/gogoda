package g.g.d.com.movie.service;

import java.util.List;
import g.g.d.com.movie.vo.MovieListVO;

public interface MovieService {

	public List<MovieListVO> MovieSelectListService(MovieListVO mvlvo);
	public int MovieInsertService(MovieListVO mvlvo);
	
}
