package g.g.d.com.movie.dao;

import java.util.List;
import g.g.d.com.movie.vo.MovieListVO;
import g.g.d.com.movie.vo.MovieUserGenreVO;

//딜리트 안함
//업데이트 안함
//부분 조회 안함

public interface MovieDao {

	public List<MovieListVO> MovieSelectListDao(MovieUserGenreVO mugvo);
	public int MovieInsertDao(MovieListVO mvlvo);
	public int MovieCountDao(String mvnum);
	public List<MovieListVO> MovieRankDao(MovieListVO mvlvo);
}
