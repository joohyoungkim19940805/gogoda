package g.g.d.com.login.controller;

import java.io.IOException;
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
import g.g.d.com.mem.common.NaverLoginBO;
import g.g.d.com.mem.vo.MemberVO;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.scribejava.core.model.OAuth2AccessToken;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping(value = "logincontroller")
public class LoginController {

	Logger logger = Logger.getLogger(LoginController.class);
	private static final String CONTEXT_PATH = "login";
	
	@Autowired(required=false)
	private LoginService loginService;
	@Autowired(required=false)
	private NaverLoginBO naverLoginBO;
	@Autowired(required=false)
	private String apiResult = null;
	

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
	
	
	
	
	//로그인 첫 화면 요청 메소드
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(Model model, HttpSession session) {
		/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		//redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
		System.out.println("네이버:" + naverAuthUrl);
		//네이버
		model.addAttribute("url", naverAuthUrl);
		return "login/mainlogin";
	}
	//네이버 로그인 성공시 callback호출 메소드
	@RequestMapping(value = "/callback", method = { RequestMethod.GET, RequestMethod.POST })
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session) throws IOException, ParseException {
		System.out.println("여기는 callback");
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		//1. 로그인 사용자 정보를 읽어온다.
		apiResult = naverLoginBO.getUserProfile(oauthToken); //String형식의 json데이터
		/** apiResult json 구조
		{"resultcode":"00",
		"message":"success",
		"response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"sh@naver.com","name":"\uc2e0\ubc94\ud638"}}
		**/
		//2. String형식인 apiResult를 json형태로 바꿈
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		//3. 데이터 파싱
		//Top레벨 단계 _response 파싱
		JSONObject response_obj = (JSONObject)jsonObj.get("response");
		//response의 nickname값 파싱
		String nickname = (String)response_obj.get("nickname");
		System.out.println(nickname);
		//4.파싱 닉네임 세션으로 저장
		session.setAttribute("sessionId",nickname); //세션 생성
		model.addAttribute("result", apiResult);
		return "../view/login/naverlogin";
	}
	//로그아웃
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session)throws IOException {
		System.out.println("여기는 logout");
		session.invalidate();
		return "../../index";
	}

	
		
	
}
