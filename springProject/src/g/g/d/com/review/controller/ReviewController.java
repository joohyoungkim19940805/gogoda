package g.g.d.com.review.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import g.g.d.com.review.common.ReviewChabunUtil;
import g.g.d.com.review.common.ReviewFileUploadUtil;
import g.g.d.com.review.common.ReviewChabunService;
import g.g.d.com.review.service.ReviewService;
import g.g.d.com.review.vo.ReviewVO;

@Controller
@RequestMapping(value="review")
public class ReviewController {
	Logger logger = Logger.getLogger(ReviewController.class);
	
	private ReviewService reviewService;
	private ReviewChabunService chabunService;
	
	@Autowired(required=false)
	public ReviewController(ReviewService reviewService, ReviewChabunService chabunService) {
		this.reviewService = reviewService;
		this.chabunService = chabunService;
	}
	
	// ���� ��ü ��ȸ
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
	@RequestMapping(value="/reviewInsert", method= {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String reviewInsert(ReviewVO rvo, HttpServletRequest request) throws IllegalStateException, IOException {
		
		System.out.println("reviewInsert");
		
		String renum = "";
		renum = ReviewChabunUtil.getReviewChabun("N", chabunService.getReviewChabun().getRenum());
		rvo.setRenum(renum);
		
		System.out.println("file"+rvo.getFile());
		System.out.println("kaid"+rvo.getKakaoid());
		System.out.println("conte"+rvo.getRecontent());
	
		String kakaoid = request.getParameter("kakaoid");

		
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
	
	
	
	/*
	// insert form
	@RequestMapping(value="reviewInsert", method=RequestMethod.POST)
	//@ResponseBody
	public String reviewInsert(ReviewVO rvo, HttpServletRequest request) throws IllegalStateException, IOException {
		
		System.out.println(chabunService.getReviewChabun().getRenum());
		
		String renum = "";
		renum = ChabunUtil.getReviewChabun("N", chabunService.getReviewChabun().getRenum());
		rvo.setRenum(renum);
		
		String url = "";
		int nCnt = 0;
		String rephoto = FileUploadUtil.fileUpload(rvo.getFile(), request);
		rvo.setRephoto(rephoto);
		nCnt = reviewService.reviewInsert(rvo);
		if(nCnt == 1) {
			url = "map/test3";
		}
		return url;
	}
	*/
	
	/*
	@RequestMapping(value="reviewSelect", method=RequestMethod.GET)
	public String reviewSelect(ReviewVO rvo) {
		
	}
	*/
	
	
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
	@RequestMapping(value="reviewRating", method=RequestMethod.GET)
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
	@RequestMapping(value="test1", method=RequestMethod.GET)
	public String test1() {
		
		return "map/test1_2";
	}
	
}
