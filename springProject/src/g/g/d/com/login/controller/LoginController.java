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
	
	@RequestMapping(value="/loginresult",method=RequestMethod.GET)
	@ResponseBody
	public String displayLogin(HttpServletRequest request, MemberVO mvo) {
		
		
		logger.info(" mpw : ");
		logger.info(" mpw : " + mvo.getMpw());
		logger.info(" mid : " + mvo.getMid());
		System.out.println("controller");
		System.out.println(" mid : " + mvo.getMid());
		System.out.println(" mpw : " + mvo.getMpw());
		
		HttpSession session = request.getSession();
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		list = loginService.loginCompleteService(mvo);
		int size=list.size();
		System.out.println("list : " + list);
		System.out.println("list.size() : " + size);
		
		if(size ==1){
			MemberVO _mvo = new MemberVO();
			_mvo = list.get(0);
			System.out.println("_mvo : " +  _mvo.toString());
			String mnum = _mvo.getMnum();
			//String mname = _mvo.getMname();
			//String memail = _mvo.getMemail();
			String mid = _mvo.getMid();
			session.setAttribute("mnum",mnum);
			session.setAttribute("mid", mid);
			//session.setAttribute("mname", mname);
			//session.setAttribute("memail",memail);
			System.out.println(mnum+mid);
			
			return "true";
		
		}else {
			
			return "false";
		}
		
	}
	
		
	
}
