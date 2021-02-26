package g.g.d.com.login.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import g.g.d.com.mem.vo.MemberVO;


public interface LoginDAO {
	public List<MemberVO> loginCompleteDao(MemberVO mvo);	

}
