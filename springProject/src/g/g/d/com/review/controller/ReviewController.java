package g.g.d.com.review.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import g.g.d.com.food.service.FoodService;
import g.g.d.com.food.vo.FoodVO;
import g.g.d.com.mem.service.MemberService;
import g.g.d.com.mem.vo.MemberVO;
import g.g.d.com.review.common.ReviewChabunService;
import g.g.d.com.review.common.ReviewChabunUtil;
import g.g.d.com.review.common.ReviewFileUploadUtil;
import g.g.d.com.review.service.ReviewService;
import g.g.d.com.review.vo.ReviewVO;

@Controller
@RequestMapping(value="review")
public class ReviewController {
	Logger logger = Logger.getLogger(ReviewController.class);
	
	private ReviewService reviewService;
	private ReviewChabunService chabunService;
	private FoodService foodService;
	private MemberService memberService;
	
	@Autowired(required=false)
	public ReviewController(ReviewService reviewService, ReviewChabunService chabunService, FoodService foodService, MemberService memberService) {
		this.reviewService = reviewService;
		this.chabunService = chabunService;
		this.foodService=foodService;
		this.memberService=memberService;
	}
	
	@RequestMapping(value="/reviewList")
	public String reviewList(ReviewVO rvo, Model model) {
		
		model.addAttribute("kakaoid", rvo.getKakaoid());
		System.out.println("kakaoid >>> : " + rvo.getKakaoid());
		return "review/reviewList_2";
	}
	
	// review SelectAll
	@RequestMapping(value="reviewListAll", method=RequestMethod.GET, produces ="application/text; charset=utf8")
	@ResponseBody
	public String reviewListAll(ReviewVO rvo, Model m) {
		
		System.out.println("kakaoid >>> : " + rvo.getKakaoid());
		String str = "";
		String listStr = "";
		List<ReviewVO> listAll = null;
		listAll = reviewService.reviewListAll(rvo);
		if(listAll != null && listAll.size() > 0) {
			for(int i=0; i<listAll.size(); i++) {
				ReviewVO _rvo = null;
				_rvo = listAll.get(i);
				String renum = _rvo.getRenum();
				String renickname = _rvo.getRenickname();
				System.out.println("renickname >>> : " + renickname);
				String recontent = _rvo.getRecontent();
				String rephoto = _rvo.getRephoto();
				String rerating = _rvo.getRerating();
				String reinsertdate = _rvo.getReinsertdate();
				listStr = renum+","+renickname+","+recontent+","+rephoto+","+rerating+","+reinsertdate;
				str += listStr + "&";
			}
		}
		
		
		
		return str;
	}
	
	
	// insert ajax
	@RequestMapping(value="reviewInsert", method=RequestMethod.POST)
	@ResponseBody
	public String reviewInsert(ReviewVO rvo, HttpServletRequest request) throws IllegalStateException, IOException {
		
		System.out.println("reviewInsert");
		
		String renum = "";
		renum = ReviewChabunUtil.getReviewChabun("N", chabunService.getReviewChabun().getRenum());
		rvo.setRenum(renum);
		
		System.out.println(rvo.getFile());
		System.out.println(rvo.getKakaoid());
		System.out.println(rvo.getRecontent());
		
		System.out.println(request.getParameter("file"));
		String kakaoid = request.getParameter("kakaoid");
		System.out.println(kakaoid);
		
		logger.info(">>>> : " + rvo.getRerating());
		if(rvo.getRerating() == null) {
			rvo.setRerating("0.0");
		}
		
		String str = "";
		str = "BAD";
		int nCnt = 0;
		String rephoto = ReviewFileUploadUtil.fileUpload(rvo.getFile(), request);
		System.out.println(rephoto);
		rvo.setRephoto(rephoto);
		nCnt = reviewService.reviewInsert(rvo);
		if(nCnt == 1) {
			str = "GOOD";
			return str;
		}
		return str;
	}
	
	
	// review Select
	@RequestMapping(value="reviewSelect", method=RequestMethod.POST, produces ="application/text; charset=utf8")
	@ResponseBody
	public String reviewSelect(ReviewVO rvo) {
		
		
		System.out.println(rvo.getKakaoid());
		System.out.println(rvo.getRenum());

		List<ReviewVO> aList = null;
		aList =	reviewService.reviewList(rvo);
		
		ReviewVO _rvo = null;
		_rvo = aList.get(0);
		System.out.println(_rvo.getRecontent());
				
		return _rvo.getRenickname() + "," + _rvo.getRecontent().toString()+ "," + _rvo.getRerating() + "," + _rvo.getRenum(); 
	}
	
	
	// review update
	@RequestMapping(value="reviewUpdate", method=RequestMethod.POST)
	@ResponseBody
	public String revivewUpdate(ReviewVO rvo, HttpServletRequest request) throws IllegalStateException, IOException {
		
		System.out.println("reviewUpdate");
		
		
		System.out.println(rvo.getFile());
		System.out.println(rvo.getKakaoid());
		System.out.println(rvo.getRecontent()+"<<<<<<<<<<<<<<<<<");
		

		System.out.println(rvo.getRenum());
		System.out.println(rvo.getRerating());
		
		String str = "";
		str = "BAD";
		int nCnt = 0;
		String rephoto = ReviewFileUploadUtil.fileUpload(rvo.getFile(), request);
		System.out.println(rephoto);
		rvo.setRephoto(rephoto);
		nCnt = reviewService.reviewUpdate(rvo);
		if(nCnt == 1) {
			str = "GOOD";
			return str;
		}
		return str;
	}
	
	
	
	
	// review delete
	@RequestMapping(value="reviewDelete", method=RequestMethod.GET)
	@ResponseBody
	public String reviewDelete(ReviewVO rvo) {
		
		String str = "";
		
		int nCnt = 0;
		nCnt = reviewService.reviewDelete(rvo);
		if(nCnt == 1) {
			str = "GOOD";
		}else {
			str = "BAD";
		}
		
		return str;
	}
	
	
	// rating select
	@RequestMapping(value="reviewRating", method=RequestMethod.POST)
	@ResponseBody
	public String reviewRating(ReviewVO rvo) {
		System.out.println("reviewRating");
		String str = "";
		List<ReviewVO> list = reviewService.reviewRating(rvo);
		String amount = list.get(0).getAmount();
		String ratingavg = list.get(0).getRatingavg();
		
		str = ratingavg + "," + amount;
		System.out.println(str);
 		return str;
	}
	
   @RequestMapping(value="reviewAndroidSelect", method=RequestMethod.POST)
   @ResponseBody
   public String reviewAndroidSelect(String kakaoid) {
      
      
      ReviewVO rvo = new ReviewVO();
      rvo.setKakaoid(kakaoid);
      
      System.out.println("kakaoid >>> : " + rvo.getKakaoid());

      List<ReviewVO> listAll = null;
      listAll = reviewService.reviewListAll(rvo);
      

      JSONObject jsonObject = new JSONObject();
      JSONArray req_array = null;
      req_array = new JSONArray();

      try {
          if(listAll != null && listAll.size()>0){
              for(int i=0; i<listAll.size(); i++){
                 ReviewVO _rvo = null;
                 JSONObject data = null; 
                 data = new JSONObject();
                 _rvo = listAll.get(i);
                 data.put("kakaoid",_rvo.getKakaoid());
                 data.put("renum",_rvo.getRenum());
                 data.put("renickname",_rvo.getRenickname());
                 data.put("repass",_rvo.getRepass());
                 data.put("recontent",_rvo.getRecontent());
                 data.put("rephoto",_rvo.getRephoto());
                 data.put("rerating",_rvo.getRerating());
                 data.put("reinsertdate",_rvo.getReinsertdate());
                 data.put("reupdatedate",_rvo.getReupdatedate());
                 

                 req_array.add(data);
              }
           }
      }catch(Exception e) {
    	  e.printStackTrace();
      }
         

      jsonObject.put("review_VO", req_array);

//	      model.addAttribute("listAll", listAll);
      
      return jsonObject.toString();
    }
	
	@RequestMapping(value="reviewAndroidInsert", method=RequestMethod.POST)
	@ResponseBody
	public String reviewAndroidInsert(ReviewVO rvo, Model model, HttpServletResponse response) {
		
//		ReviewVO _rvo = null;
//		_rvo = new ReviewVO();
//		_rvo = EncodeReview.encodeF(rvo);
		
		System.out.println("kakaoid >>> : " + rvo.getKakaoid());
		System.out.println("renickname >>> : " + rvo.getRenickname());
		
		String renum = "";
		renum = ReviewChabunUtil.getReviewChabun("N", chabunService.getReviewChabun().getRenum());
		rvo.setRenum(renum);

		String rephoto = "";
		rvo.setRephoto(rephoto);


		
		String result = "";
		result = "BAD";
		int nCnt = 0;
		nCnt = reviewService.reviewInsert(rvo);
		
		if(nCnt == 1) {
			result = "GOOD";
		}

//		model.addAttribute("result", result);
		
		return result;
	}
	
	
	@RequestMapping(value="reviewAndroidUpdate", method=RequestMethod.POST)
	@ResponseBody
	public String reviewAndroidUpdate(ReviewVO rvo, Model model, HttpServletResponse response) {
		
		
		System.out.println("kakaoid >>> : " + rvo.getKakaoid());
		System.out.println("renum >>> : " + rvo.getRenum());
		
		String result = "";
		result = "BAD";
		int nCnt = 0;
		nCnt = reviewService.reviewUpdate(rvo);
		
		if(nCnt == 1) {
			result = "GOOD";
		}

//		model.addAttribute("result", result);
		
		return result;
	}
	
	
	
	@RequestMapping(value="reviewAndroidDelete", method=RequestMethod.POST)
	@ResponseBody
	public String reviewAndroidDelete(ReviewVO rvo, Model model, HttpServletResponse response) {
		
		
		System.out.println("kakaoid >>> : " + rvo.getKakaoid());
		System.out.println("renum >>> : " + rvo.getRenum());
		
		String result = "";
		result = "BAD";
		int nCnt = 0;
		nCnt = reviewService.reviewDelete(rvo);
		
		if(nCnt == 1) {
			result = "GOOD";
		}

//		model.addAttribute("result", result);
		
		return result;
	}
	
	// 안드로이드에서 회원주소 가져오기
	@RequestMapping(value="memAddrSelect", method=RequestMethod.POST)
	@ResponseBody
	public String mapAddr(MemberVO mvo){

		String mid = "";
		String maddr = "";
		try{
			mid = new String(mvo.getMid().getBytes("8859_1"), "UTF-8");

		mvo.setMid(mid);
		logger.info("Review Controller mapAddr >>>>>> : ");
		logger.info("mvo.getMid() >>>>>>>>>> : " + mvo.getMid());

		MemberVO _mvo = null;
		_mvo = memberService.memberAddress(mvo);

			if(_mvo != null){
				maddr = new String(_mvo.getMaddr().getBytes("UTF-8"),"8859_1");;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return maddr;
	}
	
	
	@RequestMapping(value="map", method=RequestMethod.GET)
	public String map(HttpServletRequest hsr, Model model, String food, FoodVO fdvo) {
		
		logger.info(food);
		String fname=food;
		int result=0;
		result = foodService.FoodCountService(fname);

	
		HttpSession session = hsr.getSession();
		logger.info(session);
		logger.info(session.getAttribute("mid"));
		
		MemberVO mvo = null;
		mvo = new MemberVO();
		mvo.setMid(session.getAttribute("mid").toString());
		
		if(session.getAttribute("mid")!=null&&session.getAttribute("mnum")!=null) {
			String seName=session.getAttribute("mid").toString();
			String seNum=session.getAttribute("mnum").toString();
			model.addAttribute("seName", seName);
			model.addAttribute("seNum", seNum);
			model.addAttribute("memAddr", memberService.memberAddress(mvo).getMaddr());
		}
		
		return "map/test1_2";
	}
	
}
