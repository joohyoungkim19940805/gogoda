package g.g.d.com.food.dao;

import java.util.ArrayList;
import java.util.List;

import g.g.d.com.food.vo.FoodVO;
//인설트 안함
//딜리트 안함
//업데이트 안함
//전체조회 안함
//선택 조회 함

public interface FoodDao {

	public List<FoodVO> FoodSelectListDao(FoodVO fdvo);
	public int FoodCountDao(String fname);
	public List<FoodVO> FoodRankDao(FoodVO fdvo);
}
