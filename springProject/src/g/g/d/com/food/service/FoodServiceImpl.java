package g.g.d.com.food.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import g.g.d.com.food.vo.FoodVO;
import g.g.d.com.board.vo.BoardVO;
import g.g.d.com.food.dao.FoodDao;

@Service
@Transactional
public class FoodServiceImpl implements FoodService {
	Logger logger = Logger.getLogger(FoodServiceImpl.class);
	
	@Autowired(required=false)
	private FoodDao foodDao;
	
	@Override
	public List<FoodVO> FoodSelectListService(FoodVO fdvo) {
		// TODO Auto-generated method stub
		logger.info("푸드서비스 시작");
		List<FoodVO> foodList=null;
		foodList=foodDao.FoodSelectListDao(fdvo);
		logger.info(foodList);
		return foodList;
	}
	
	@Override
	public int FoodCountService(String fname) {
		logger.info(fname);
		int fcnt=0;
		fcnt=foodDao.FoodCountDao(fname);
		logger.info(fname);
		return fcnt;
	}
	@Override
	public List<FoodVO> FoodRankService(FoodVO fdvo){
		logger.info("푸드랭크서비스 시작");
		List<FoodVO> foodRankList=null;
		foodRankList=foodDao.FoodRankDao(fdvo);
		return foodRankList;
		
	}
	
	
}
