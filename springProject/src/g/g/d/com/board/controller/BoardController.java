package g.g.d.com.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import g.g.d.com.board.common.BoardChabunService;
import g.g.d.com.board.common.BoardChabunUtil;
import g.g.d.com.board.common.BoardFileUploadUtil;
import g.g.d.com.board.common.BoardPaging;
import g.g.d.com.board.common.BoardUtil;
import g.g.d.com.board.service.BoardService;
import g.g.d.com.board.vo.BoardVO;

@Controller
@RequestMapping(value="board")
public class BoardController {
	Logger logger = Logger.getLogger(BoardController.class);
	
	@Autowired(required=false)
	private BoardService boardService;
	
	@Autowired(required=false)
	private BoardChabunService chabunService;
	
	// 글 목록 구현하기
	@RequestMapping(value="/boardList", method=RequestMethod.GET)
	public String boardList(@ModelAttribute BoardVO bvo, Model model,HttpServletRequest hsr) {
		logger.info("boardList 호출 성공");
		
		// 정렬에 대한 기본값 설정
		if(bvo.getOrder_by()==null) bvo.setOrder_by("bnum");
		if(bvo.getOrder_sc()==null) bvo.setOrder_sc("DESC");
		
		// 정렬에 대한 데이터 확인
		logger.info("order_by = "+bvo.getOrder_by());
		logger.info("order_sc = "+bvo.getOrder_sc());
		
		// 검색에 대한 데이터 확인
		logger.info("search = "+bvo.getSearch());
		logger.info("keyword = "+bvo.getKeyword());
		
		// 페이징 세팅
		BoardPaging.setPage(bvo);
		
		// 전체 레코드수 구현
		int total = boardService.boardListCnt(bvo);
		logger.info("total = "+total);
		
		// 글번호 재설정
		int count = total - (BoardUtil.nvl(bvo.getPage())-1)*BoardUtil.nvl(bvo.getPageSize());
		logger.info("count = "+count);
		
		List<BoardVO> boardList = boardService.boardList(bvo);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("count",count);
		model.addAttribute("total",total);
		model.addAttribute("data", bvo);
		
		HttpSession session = hsr.getSession();
		logger.info(session);
		logger.info(session.getAttribute("mid"));
		if(session.getAttribute("mid")!=null&&session.getAttribute("mnum")!=null) {
			String seName=session.getAttribute("mid").toString();
			String seNum=session.getAttribute("mnum").toString();
			model.addAttribute("seName", seName);
			model.addAttribute("seNum", seNum);
		}
		
		
		return "board/boardList";
	}
	
	// 글쓰기 폼 출력하기
	@RequestMapping(value="/writeForm", method=RequestMethod.GET)
	public String writeForm(HttpServletRequest hsr, Model model) {
		logger.info("writeForm 호출 성공");
		
		HttpSession session = hsr.getSession();
		String seName=session.getAttribute("mid").toString();
		String seNum=session.getAttribute("mnum").toString();
		model.addAttribute("seName", seName);
		model.addAttribute("seNum", seNum);
		
		return "board/writeForm";
	}
	
	// 글쓰기 구현하기
	@RequestMapping(value="/boardInsert", method=RequestMethod.POST)
	public String boardInsert(@ModelAttribute BoardVO bvo,HttpServletRequest request
							 ) throws IllegalStateException, IOException {
		logger.info("boardInsert 호출 성공");
		logger.info("fileName : " + bvo.getFile().getOriginalFilename());
		logger.info("bsubject : " + bvo.getBsubject());
		logger.info("bnum : " + bvo.getBname());
		logger.info("============================================ " + bvo.getBcontent());
		// 글번호 채번
		String bnum = BoardChabunUtil.getBoardChabun("N", chabunService.getBoardChabun().getBnum());
		
		bvo.setBnum(bnum);
		logger.info("bnum : " + bvo.getBnum());
		int result = 0;
		String url = "";
		
		String bfile = BoardFileUploadUtil.fileUpload(bvo.getFile(), request);
		bvo.setBfile(bfile);
		
		result = boardService.boardInsert(bvo);
		
		if (result == 1) {
			 	url = "/board/boardList.ggd";
		}
		return "redirect:" + url;
	}
	
	// 글 상세보기 구현
	@RequestMapping(value="/boardDetail", method=RequestMethod.GET)
	public String boardDetail(@ModelAttribute BoardVO pvo, String bnum, Model model,HttpServletRequest hsr) {
		logger.info("boardDetail 호출 성공");
		logger.info("bnum = " + pvo.getBnum());
		
		BoardVO detail = new BoardVO();
		detail = boardService.boardDetail(pvo);

		if (detail !=null && (!detail.equals(""))) {
			detail.setBcontent(detail.getBcontent().toString().replaceAll("\n", "<br>"));
			detail.setBcontent(detail.getBcontent().toString().replaceAll("？", ""));
		}
		HttpSession session = hsr.getSession();
		logger.info(session);
		logger.info(session.getAttribute("mid"));
		if(session.getAttribute("mid")!=null&&session.getAttribute("mnum")!=null) {
			String seName=session.getAttribute("mid").toString();
			String seNum=session.getAttribute("mnum").toString();
			model.addAttribute("seName", seName);
			model.addAttribute("seNum", seNum);
		}
		// 조회수
		boardService.boardHits(bnum);
		
		model.addAttribute("detail", detail);
		return "board/boardDetail";
	}
	
	/*
	 * 비밀번호 확인
	 * @param bnum
	 * @param bpwd
	 * @return int
	 * 참고 : @ResponseBody는 전달된 뷰를 통해서 출력하는 것이 아니라
	 * 		HTTP Response Body에 직접 출력하는 방식
	 * */
	@ResponseBody
	@RequestMapping(value="/pwdConfirm", method=RequestMethod.POST)
	public String pwdConfirm(@ModelAttribute BoardVO bvo) {
		logger.info("pwdConfirm 호출 성공");
		// 아래 변수에는 입력 성공에 대한 상태값 저장 (1 or 0)
		int result = 0;
		result = boardService.pwdConfirm(bvo);
		
		logger.info("result = " + result);
		return result + "";
	}
	
	/*
	 * 글수정 폼 출력하기
	 * @param bnum
	 * @return BoardVO
	 * */
	@RequestMapping(value="/updateForm", method=RequestMethod.POST)
	public String updateForm(@ModelAttribute BoardVO pvo, Model model, HttpServletRequest hsr) {
		logger.info("updateForm 호출 성공");
		logger.info("bnum = " + pvo.getBnum());
		
		BoardVO updateData = new BoardVO();
		updateData = boardService.boardDetail(pvo);
		
		HttpSession session = hsr.getSession();
		logger.info(session);
		logger.info(session.getAttribute("mid"));
		if(session.getAttribute("mid")!=null&&session.getAttribute("mnum")!=null) {
			String seName=session.getAttribute("mid").toString();
			String seNum=session.getAttribute("mnum").toString();
			model.addAttribute("seName", seName);
			model.addAttribute("seNum", seNum);
		}
		
		model.addAttribute("updateData", updateData);
		return "board/updateForm";
	}
	
	// 글수정 구현하기
	@RequestMapping(value="/boardUpdate", method=RequestMethod.POST)
	public String boardUpdate(@ModelAttribute BoardVO bvo,HttpServletRequest request) throws IllegalStateException, IOException {
		logger.info("boardUpdate 호출 성공");
		
		int result = 0;
		String url = "";
		String bfile = "";
		
		if (!bvo.getFile().isEmpty()) {
			BoardFileUploadUtil.fileDelete(bvo.getBfile(), request);
			bfile = BoardFileUploadUtil.fileUpload(bvo.getFile(), request);
			bvo.setBfile(bfile);
		}else {
			logger.info("첨부파일 없음");
			bvo.setBfile("");
			
		}	
			logger.info("bfile = " + bvo.getBfile());
			result = boardService.boardUpdate(bvo);
			
			if (result == 1) {
				url = "/board/boardList.ggd"; // 수정 후 목록으로 이동
				// 아래 url은 수정 후 상세 페이지로 이동
				// url = "/board/boardDetail.ggd?bnum=" + bvo.getBnum();
			}
			return "redirect:" + url;
	}
	
	/*
	 * 글삭제 구현하기
	 * @throws IOException
	 * */
	@RequestMapping(value="/boardDelete")
	public String boardDelete(@ModelAttribute BoardVO bvo, HttpServletRequest request) throws IOException {
		logger.info("boardDelete 호출 성공");
		
		// 아래 변수에는 삭제 성공에 대한 상태값 담습니다. (1 or 0)
		int result = 0;
		String url = "";
		
		BoardFileUploadUtil.fileDelete(bvo.getBfile(), request);
		result = boardService.boardDelete(bvo);
		if (result == 1) {
				url="/board/boardList.ggd";
		}
		
		return "redirect:" + url;
	} 
	
	// 안드로이드 게시판
	// 안드로이드 게시판
	@ResponseBody
	@RequestMapping(value="/boardList2", method=RequestMethod.GET, produces ="application/text; charset=utf8")
	public String androidboard(BoardVO bvo) {
		
		JSONObject json = new JSONObject();
		
		List<BoardVO> items = boardService.boardList(bvo);
		JSONArray jArray = new JSONArray();
		try {
			for (int i = 0; i < items.size(); i++) {
				BoardVO avo = items.get(i);
				String bname = "";
				String bsubject = "";
				String bfile = "";

				JSONObject jobj = new JSONObject();
				jobj.put("bnum", avo.getBnum());
				jobj.put("bname", avo.getBname());
				jobj.put("bsubject", avo.getBsubject());
				//jobj.put("bhit", avo.getBhit());
				jobj.put("bfile", avo.getBfile());
				jobj.put("binsertdate", avo.getBinsertdate());
				jobj.put("bupdatedate", avo.getBupdatedate());
				
				jArray.add(i, jobj);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		json.put("board", jArray);
		logger.info(json);
		
		return json.toString();
	}
	
	// 글 상세보기 구현 (안드로이드)
	@ResponseBody
	@RequestMapping(value="/boardDetail2", method=RequestMethod.GET, produces ="application/text; charset=utf8")
	public String boardDetail2(@ModelAttribute BoardVO pvo, String bnum, Model model) {
		logger.info("boardDetail 호출 성공");
		logger.info("bnum = " + pvo.getBnum());
		
		BoardVO detail = boardService.boardDetail(pvo);
		
		String bname = "";
		String bsubject = "";
		String bfile = "";
		JSONObject json = new JSONObject();
		try {
			json.put("bnum", detail.getBnum());
			json.put("bname", detail.getBname());
			json.put("bsubject", detail.getBsubject());
			json.put("bfile", detail.getBfile());
			json.put("bcontent", detail.getBcontent());
			json.put("binsertdate", detail.getBinsertdate());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		


		
		logger.info(json);
		return json.toString();
	}
	
	
	// 글쓰기 구현하기(안드로이드)
	@RequestMapping(value="/boardInsert2", method=RequestMethod.POST)
	public String boardInsert2(@ModelAttribute BoardVO bvo,HttpServletRequest request
							 ) throws IllegalStateException, IOException {
		logger.info("boardInsert2 호출 성공");
		bvo.getFile().getOriginalFilename();
		logger.info("fileName : " + bvo.getFile().getOriginalFilename());
		logger.info("bsubject : " + bvo.getBsubject());
		logger.info("bnum : " + bvo.getBname());
		logger.info("============================================ " + bvo.getBcontent());
		// 글번호 채번
		String bnum = BoardChabunUtil.getBoardChabun("N", chabunService.getBoardChabun().getBnum());
		
		bvo.setBnum(bnum);
		logger.info("bnum : " + bvo.getBnum());
		int result = 0;
		String url = "";
		
		String bfile = BoardFileUploadUtil.fileUpload(bvo.getFile(), request);
		bvo.setBfile(bfile);
		
		result = boardService.boardInsert(bvo);
		
		if (result == 1) {
			 	url = "/board/boardList.ggd";
		}
		return "redirect:" + url;
	}

}