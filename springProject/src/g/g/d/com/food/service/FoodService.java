package g.g.d.com.food.service;
import java.util.ArrayList;
import java.util.List;

import g.g.d.com.food.vo.FoodVO;

public interface FoodService {

	public List<FoodVO> FoodSelectListService(FoodVO fdvo);
	public int FoodCountService(String fname);
	public List<FoodVO> FoodRankService(FoodVO fdvo);
}