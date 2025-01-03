package g.g.d.com.mem.common;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import g.g.d.com.mem.vo.MemberVO;


@Repository
public class ChabunDAOImpl implements ChabunDAO {
	private Logger logger = Logger.getLogger(ChabunDAOImpl.class);
	
	@Autowired(required=false)
	private SqlSessionTemplate sqlSession;
	
	// 
	@Override
	public MemberVO getChabun() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("getChabun");
	}	
}
