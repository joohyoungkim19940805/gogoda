package g.g.d.com.movie.dao;

import java.util.List;
import g.g.d.com.movie.vo.MovieListVO;


//딜리트 안함
//업데이트 안함
//부분 조회 안함

public interface MovieDao {

	public List<MovieListVO> MovieSelectListDao(MovieListVO mvlvo);
	public int MovieInsertDao(MovieListVO mvlvo);
}
