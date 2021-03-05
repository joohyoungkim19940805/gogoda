package g.g.d.com.login.service;

import java.util.List;

import g.g.d.com.mem.vo.MemberVO;


public interface LoginService {
	public MemberVO loginCompleteService(MemberVO mvo);
	public MemberVO loginFindIdService(MemberVO mvo);	
	public MemberVO loginFindPwService(MemberVO mvo);	
}
