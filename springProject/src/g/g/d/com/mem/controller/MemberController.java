package g.g.d.com.mem.controller;

import java.io.IOException;
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

import g.g.d.com.mem.common.ChabunService;
import g.g.d.com.mem.common.ChabunUtil;
import g.g.d.com.mem.common.Encode;
import g.g.d.com.mem.common.MailSend;
import g.g.d.com.mem.common.TempPassword;
import g.g.d.com.mem.service.MemberService;
import g.g.d.com.mem.vo.MemberVO;

// 현 클래스를 controller bean 에 등록
@Controller
@RequestMapping(value="mem")
public class MemberController {
	Logger logger = Logger.getLogger(MemberController.class);
	
	// MemberService 객체를 스프링에서 생성하여 주입
	//@Inject
	//private MemberService memberService;
	
	@Autowired(required=false)
	private MemberService memberService;
	
	@Autowired(required=false)
	private ChabunService chabunService;
	
	@RequestMapping(value="/logout",method= {RequestMethod.GET , RequestMethod.POST})
	public String logout(HttpServletRequest hsr, Model model) {
		logger.info("로그아웃... 메인페이지로 이동");
		HttpSession session = hsr.getSession();
		session.invalidate();
		return "../../index";
	}
	
	// 회원 가입 페이지로 이동
	@RequestMapping(value="/registerForm", method = {RequestMethod.POST,RequestMethod.GET})
	public String member() {
		logger.info("registerForm 호출 성공 >>>");
		return "member/registerForm";
	}

	@RequestMapping(value="/moveSurvey", method = RequestMethod.POST)
	public String moveSurvey() {
		return "member/surveyInsert";
	}
	
	// 설문 조사 추가
	@RequestMapping(value="/surveyInsert", method = RequestMethod.POST)
	public String surveyInsert(@ModelAttribute MemberVO mvo, String positivefood, String negativefood, String movietaste) {
		logger.info("surveyInsert 호출 성공");
		logger.info("mvo.getMnum() >>> : " +  mvo.getMnum());
		logger.info("mvo.getPositivefood() >>> : " +  mvo.getPositivefood());
		logger.info("mvo.getNegativefood() >>> : " +  mvo.getNegativefood());
		logger.info("mvo.getMovietaste() >>> : " +  mvo.getMovietaste());
		logger.info("mvo.getPositivefood() >>> : " +  positivefood);
		logger.info("mvo.getNegativefood() >>> : " +  negativefood);
		logger.info("mvo.getMovietaste() >>> : " +  movietaste);
		mvo.setPositivefood(positivefood);
		mvo.setNegativefood(negativefood);
		mvo.setMovietaste(movietaste);
		
		
		int result = 0;
		String url = "";
		
		result = memberService.surveyInsert(mvo);
		logger.info("surveyInsert 호출 성공  "+ result);	
		
		// 아래 부분 수정 요 -> 화요일에 강사님 만나서 수정하겠음
		if (result == 1) {
			logger.info("surveyInsert 성공");
			url = "member/memberSelectAll"; // 수정 후 상세 페이지로 이동
			//url = "memberSelectAll.ggd"; // 수정 후 상세 페이지로 이동
			return "../../index";
		}else {
			return "../../index";
		}
	}
	
	// 회원 등록 처리 후 회원 목록으로 리다이렉트
	// @ModelAttribute 에 폼에서 입력한 데이터가 저장
	
	// 폼에서 입력한 데이터를 받아오는 3가지 방법
	// public String memberInsert(HttpServlet request) {
	// public String memberInsert(String mid, String mpw) {
	// public String memberInsert(@ModelAttribute MemberVO mvo) {
	
	@RequestMapping(value="/memberInsert", method = RequestMethod.POST)
	@ResponseBody
	public String memberInsert(@ModelAttribute MemberVO mvo, HttpServletRequest request, Model model) throws IllegalStateException, IOException {
		logger.info("memberInsert 호출 성공");

		String mnum = ChabunUtil.getMemChabun("N", chabunService.getChabun().getMnum());
		
		mvo.setMnum(mnum);
		logger.info("mname>>>"+mvo.getMname());
		logger.info("maddr>>>"+mvo.getMaddr());
		logger.info("maddrdetail>>>"+mvo.getMaddrdetail());
		
		//logger.info(request.getParameter("mname"));
		//logger.info(request.getParameter("maddr"));
		//logger.info(request.getParameter("maddrdetail"));

		
		
		
		int result = 0;
		String url = "";
		// 테이블에 레코드 입력		
		result = memberService.memberInsert(mvo);
		logger.info(">>>"+mnum);
		
		if (result == 0) {
			return mnum;
		}else {
			//return "index";			
			return mnum;
		}
		
	}
	// '/' 유무의 차이
	// /member/list.do : 루트  디렉토리를 기준
	// member/list.do : 현재 디렉토리를 기준
	// member_list.jsp 로 리다이렉트
	
	// 회원 정보 수정
	@RequestMapping(value="/memberUpdate", method = RequestMethod.POST)
	@ResponseBody
	public String memberUpdate(@ModelAttribute MemberVO mvo,HttpServletRequest request) throws IllegalStateException, IOException {
		logger.info("memberUpdate 호출 성공");
		int result = 0;
		String url = "";
		
		result = memberService.memberUpdate(mvo);
		logger.info("memberUpdate 호출 성공 = "+result);	
		if (result == 1) {
			url = "../emotion/logout.ggd"; // 수정 후 상세 페이지로 이동
		}		
		return "true";		
	}
/*
	@RequestMapping(value="memberUpdate")
	public String memberUpdate(@ModelAttribute MemberVO mvo, Model model) {
		// 비밀번호 체크
		boolean result = memberService.checkPw(vo.getMid(), vo.getMpw()); // checkPw 필드
		// 비밀번호가 맞다면, 수정 처리 후, 전체 회원 목록으로 리다이렉트
		if (result){
			memberService.memberUpdate(mid);
			return "redirect:member/memberSelectAll.do";
		}else{ // 비밀번호가 일치하지 않는다면
			// 가입일자, 수정일자 저장
			MemberVO mvo2 = memberService.memberSelect(vo.getMid());
			vo.setMupdatedate(vo2.getMupdatedate());
			model.addAtrribute("message", "비밀번호 불일치");
			model.addAtrribute("mvo", vo);
			return "member/memberSelectAll"; //
		}	
	}
	
 */
	// 회원 정보 삭제
	@RequestMapping(value="/memberDelete")
	public String memberDelete(@ModelAttribute MemberVO mvo,HttpServletRequest request) throws IllegalStateException, IOException {
		logger.info("memberDelete 호출 성공");

		// 아래 변수에는 삭제 성공에 대한 상태값 담습니다. (1 or 0)
		int result = 0;
		String url = "";
		
		result = memberService.memberDelete(mvo);
		
		if (result == 1) {
			url="registerForm.ggd";
		}		
		return "redirect:" + url;		
	}
/*	
	@RequestMapping(value="memberDelete")
	public String memberDelete(@RequestParam String mid, @RequestParam String mpw, Model model){
		// 비밀번호 체크
		boolean result = memberService.checkPw(mid, mpw); // checkPw 필드
		// 비밀번호가 맞다면, 삭제 처리 후, 전체 회원 목록으로 리다이렉트
		if (result){
			memberService.memberDelete(mid);
			return "redirect:member/memberSelectAll.do";
		}else{
			model.addAtrribute("message", "비밀번호 불일치");
			model.addAtrribute("mvo", memberService.memberSelect(mid));
			return "member/memberSelectAll"; //
		}	
	}
	
*/	
	// 회원 정보 상세조회
	@RequestMapping(value="/memberSelect", method = RequestMethod.GET)
//	public String memberSelect(@RequestParaam String mid, Model model) {
	public String memberSelect(@ModelAttribute MemberVO mvo, Model model, HttpServletRequest hsr) {
		logger.info("memberSelect 호출 성공");
		String seNum="";
		String seName="";
		HttpSession session = hsr.getSession();
		if(session.getAttribute("mid")!=null&&session.getAttribute("mnum")!=null) {
			seName=session.getAttribute("mid").toString();
			seNum=session.getAttribute("mnum").toString();
			model.addAttribute("seName", seName);
			model.addAttribute("seNum", seNum);
		}
		mvo.setMnum(seNum);
		mvo.setMid(seName);
		
		logger.info("Mnum = " + mvo.getMnum());
		String mnum=mvo.getMnum();
		MemberVO detail = new MemberVO();
		detail = memberService.memberSelect(mvo);
		
		// 회원 정보를 model 에 저장
		//detail = memberService.memberSelect(mid);
		if (detail !=null && (!detail.equals(""))) {
			model.addAttribute("detail", detail);
			model.addAttribute("mnum", mnum);
		}
		//System.out.println("클릭한 ID 확인 : " + mid);
		//logger.info("클릭한 아이디 : " + mid);
		// memberSelect.jsp 로 포워드
		return "member/memberSelect"; 
	}
	
	// 회원 목록 조회
	@RequestMapping(value="/memberSelectAll", method = RequestMethod.GET)
	public String memberSelectAll(@ModelAttribute MemberVO mvo, Model model) {
		logger.info("memberSelectAll 호출 성공");

		List<MemberVO> listAll = memberService.memberSelectAll(mvo);
		int nCnt = listAll.size();
		logger.info("MemberController memberSelectAll nCnt >>> : " + nCnt);
		
		if (nCnt > 0) {
			model.addAttribute("listAll", listAll);		
		}
		return "member/memberSelectAll";	
	}
	
	// id check 버튼
	@RequestMapping(value="/idCheck", method = RequestMethod.POST)
	@ResponseBody
	public String idCheck(MemberVO mvo) {
		logger.info("MemberController idCheck >>> : " );
		logger.info("MemberController idCheck mvo.getMid() >>> : " +  mvo.getMid());
		
		List<MemberVO> listAll = memberService.idCheck(mvo);
		if (listAll.size() == 0) {
			return "ID_GOOD";
		}else {
			return "";
		}
		
	}	
	
	@RequestMapping(value="pwCheck", method = RequestMethod.POST)
	@ResponseBody
	public boolean pwCheck(MemberVO mvo) {
		logger.info("MemberController pwCheck >>> : " );
		logger.info("MemberController pwCheck mvo.getMnum() >>> : " +  mvo.getMnum());
		logger.info("MemberController pwCheck mvo.getMpw() >>> : " +  mvo.getMpw());
		
//		List<MemberVO> listAll = memberService.idCheck(mvo);
		boolean result = false;
		//boolean bool = memberService.pwCheck(mvo);
		int nCnt = 0;
		nCnt = memberService.pwCheck(mvo);
		if(nCnt == 1) result = true;
		else result = false;
		/*
		if (bool) {
			result = true;
		}else {
			result = false;
		}
		*/
		return result;
	}
    
	// 이메일 인증 
//	@RequestMapping( value = "/member/auth.do" , method=RequestMethod.POST )
//    public ModelAndView mailSending(HttpServletRequest request, String memail, HttpServletResponse response_email) throws IOException {

	@RequestMapping(value="/mailCheck", method=RequestMethod.POST)
	@ResponseBody
	public String mailCheckGET(HttpServletRequest request, String mail, Model model) throws Exception{
		
		// 뷰(View)로부터 넘어온 데이터 확인 
		logger.info("이메일 데이터 전송 확인");
		logger.info("이메일 : " + mail);
		String key=TempPassword.setTempPassWord(10);
		new MailSend().MailSet(key, mail);
		model.addAttribute("key",key);
		/*
		// 인증번호(난수) 생성 
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;
		logger.info("인증번호 " + checkNum);
		
		// 이메일 보내기
		String setFrom = "solideo226@gmail.com";
		String toMail = mail;
		String title = "회원가입 인증 이메일 입니다.";
		String content = 
						 "홈페이지를 방문해주셔서 감사합니다." +
						 "<br><br>" + 
						 "인증 번호는 " + checkNum + "입니다." + 
						 "<br>" + 
						 "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";		
		
		// 이메일 전송을 위한 코드 삽입
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
				helper.setFrom(setFrom);
				helper.setTo(toMail);
				helper.setSubject(title);
				helper.setText(content,true);
				mailSender.send(message);			
		}catch(Exception e) {
			e.printStackTrace();
		}		
		// 인증번호 회원가입 페이지로 전송
		// ajax를 통한 요청으로 인해 뷰로 다시 반환할 때 데이터 타입은 String 타입만 가능
		 * */
		//String num = Integer.toString(authnum);
		//response.setHeader("Content-Type", "text/html;charset=utf-8");
		
		return key;
		
	}
	
	
	// androidInsert mapping
	@RequestMapping(value="/androidInsert", method=RequestMethod.POST)
	@ResponseBody
	public String androidInsert(MemberVO mvo) {
		/*
		try {
			String aa = mvo.getMname();
			System.out.println("aa <<<>>> : " + aa);
			aa = new String(aa.getBytes("8859_1"), "UTF-8");
			System.out.println("aa >>> : " + aa);
		}catch(Exception e) {}
		*/					
		
		logger.info("androidInsert >>>>>>>>>>>>> : ");
//		logger.info("mvo.getMnum() >>>> : " + mvo.getMnum());
		logger.info("mvo.getMname() >>>> : " + mvo.getMname());
		logger.info("mvo.getMid() >>>> : " + mvo.getMid());
		logger.info("mvo.getMpw() >>>> : " + mvo.getMpw());
		logger.info("mvo.getMbirth() >>>> : " + mvo.getMbirth());
		logger.info("mvo.getMgender() >>>> : " + mvo.getMgender());
		logger.info("mvo.getMhp() >>>> : " + mvo.getMhp());
		logger.info("mvo.getMemail() >>>> : " + mvo.getMemail());
		logger.info("mvo.getMaddr() >>>> : " + mvo.getMaddr());
		logger.info("mvo.getMaddrdetail() >>>> : " + mvo.getMaddrdetail());
		logger.info("mvo.getPositivefood() >>> : " + mvo.getPositivefood());
		logger.info("mvo.getNegativefood() >>> : " + mvo.getNegativefood());
		logger.info("mvo.getMovietaste() >>> : " + mvo.getMovietaste());
		//logger.info("maddr>>>"+mvo.getMaddr());
		//logger.info("maddr>>>"+mvo.getMaddr());
		mvo.setMname(Encode.utf8(mvo.getMname()));
		mvo.setMid(Encode.utf8(mvo.getMid()));
		mvo.setMpw(Encode.utf8(mvo.getMpw()));
		mvo.setMbirth(Encode.utf8(mvo.getMbirth()));
		mvo.setMgender(Encode.utf8(mvo.getMgender()));
		mvo.setMhp(Encode.utf8(mvo.getMhp()));
		mvo.setMemail(Encode.utf8(mvo.getMemail()));
		mvo.setMaddr(Encode.utf8(mvo.getMaddr()));
		mvo.setMaddrdetail(Encode.utf8(mvo.getMaddrdetail()));
		mvo.setPositivefood(Encode.utf8(mvo.getPositivefood()));
		mvo.setNegativefood(Encode.utf8(mvo.getNegativefood()));
		mvo.setMovietaste(Encode.utf8(mvo.getMovietaste()));
		
		//logger.info("mvo.getMaddr() >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: " + mvo.getMaddr().getBytes());

			
		String mnum = ChabunUtil.getMemChabun("N", chabunService.getChabun().getMnum());
		
		mvo.setMnum(mnum);
		logger.info("mvo.getMnum() >>>> : " + mvo.getMnum());
		
		logger.info("mname>>>"+mvo.getMname());
		logger.info("maddr>>>"+mvo.getMaddr());
		logger.info("maddrdetail>>>"+mvo.getMaddrdetail());
		logger.info("positivefood >>> : "+mvo.getPositivefood());
		logger.info("negativefood >>> : "+mvo.getNegativefood());
		logger.info("movietaste >>> : "+mvo.getMovietaste());
		//logger.info(request.getParameter("mname"));
		//logger.info(request.getParameter("maddr"));
		//logger.info(request.getParameter("maddrdetail"));

		
		
		
		int nCnt = 0;
		int nCnt1 = 0;
		String result = "";
		String result1 = ""; 
		
		// 테이블에 레코드 입력		
		nCnt = memberService.memberInsert(mvo);
		nCnt1 = memberService.surveyInsert(mvo);
		
		if (nCnt == 1 && nCnt1 == 1) {					 	
			result = "GOOD";
		}else {
			result = "BAD";
		}
		
		
		return result;
	}
	
	@RequestMapping(value="/androidDaum", method=RequestMethod.GET)
	public String androidDaum() {
		return "member/daum";
	}
	
	
}
