package g.g.d.com.login.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import g.g.d.com.login.service.LoginService;
import g.g.d.com.mem.vo.MemberVO;




@Controller
@RequestMapping(value = "logincontroller")
public class LoginController {

	Logger logger = Logger.getLogger(LoginController.class);
	private static final String CONTEXT_PATH = "login";
	
	@Autowired(required=false)
	private LoginService loginService;
	
	@RequestMapping(value="/loginpage",method= {RequestMethod.POST, RequestMethod.GET})
	public String comple(HttpServletRequest hsr,Model model) {
		
		return "login/mainlogin";
	}
	
	
	//아이디찾기는=조회 WHERE 이름+이메일 OK="이메일" fail= "Id_Email_Fail" 이메일 틀림(가입노)
	//비밀번호찾기는= 조회 WHERE 아이디+이메일 OK=이메일 fail= "pw_Email_Fail"
	
	@RequestMapping(value="/findid",method=RequestMethod.POST)
	@ResponseBody
	public String findid(HttpServletRequest request, MemberVO mvo) {
		mvo.setMname(request.getParameter("name"));
		mvo.setMemail(request.getParameter("email"));
		MemberVO okmvo=loginService.loginFindIdService(mvo);
		
		if(okmvo.getMemail()!=null&&okmvo.getMname()!=null) {
			
			return okmvo.getMemail().toString();
		}else {
			return "Id_Email_Fail";
		}
	}
	
	@RequestMapping(value="/findpw",method=RequestMethod.POST)
	@ResponseBody
	public String findpw(HttpServletRequest request, MemberVO mvo) {
		mvo.setMid(request.getParameter("id"));
		mvo.setMemail(request.getParameter("email"));
		MemberVO okmvo=loginService.loginFindIdService(mvo);
		
		if(okmvo.getMemail()!=null&&okmvo.getMname()!=null) {
			
			return okmvo.getMemail().toString();
		}else {
			return "Pw_Email_Fail";
		}
	}
	
	@RequestMapping(value="/loginresult",method=RequestMethod.POST)
	@ResponseBody
	public String displayLogin(HttpServletRequest request, MemberVO mvo) {
		
		
		logger.info(" mpw : ");
		logger.info(" mpw : " + mvo.getMpw());
		logger.info(" mid : " + mvo.getMid());
		System.out.println("controller");
		System.out.println(" mid : " + mvo.getMid());
		System.out.println(" mpw : " + mvo.getMpw());
		
		HttpSession session = request.getSession();
		
		MemberVO okmvo = new MemberVO();
		okmvo.setMid(mvo.getMid());
		okmvo = loginService.loginCompleteService(okmvo);
		
		if(okmvo.getMid()==null) {
			
			return "not_find_id";
		}else if(mvo.getMpw().equals(okmvo.getMpw())){
			String mnum = okmvo.getMnum();
			//String mname = _mvo.getMname();
			//String memail = _mvo.getMemail();
			String mid = okmvo.getMid();
			session.setAttribute("mnum",mnum);
			session.setAttribute("mid", mid);
			//session.setAttribute("mname", mname);
			//session.setAttribute("memail",memail);
			System.out.println(mnum+mid);
			
			return "login_ok";
		
		}else{
			
			return "discord_pw";
		}
		
	}
	
		
	
}
