package g.g.d.com.food.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import g.g.d.com.food.service.FoodServiceImpl;
import g.g.d.com.food.vo.FoodVO;
import org.apache.log4j.Logger;

@Repository
public class FoodDaoImpl implements FoodDao{
	Logger logger = Logger.getLogger(FoodDaoImpl.class);
	@Autowired(required=false)
	private SqlSession session;
	
	@Override
	public List<FoodVO> FoodSelectListDao(FoodVO fdvo){
		logger.info("푸드DAO 시작");
		return session.selectList("FoodSelectListService",fdvo);
	}
	@Override
	public int FoodCountDao(String fname) {
		logger.info("푸드랭킹DAO"+fname);
		return session.update("FoodCountService",fname);
	}
	@Override
	public List<FoodVO> FoodRankDao(FoodVO fdvo){
		logger.info("푸드랭킹산정DAO");
		return session.selectList("FoodRankService");
	}
	
}
