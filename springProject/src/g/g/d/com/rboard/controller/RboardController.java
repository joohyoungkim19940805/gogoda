package g.g.d.com.rboard.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import g.g.d.com.board.common.BoardChabunService;
import g.g.d.com.board.common.BoardChabunUtil;
import g.g.d.com.rboard.service.RboardService;
import g.g.d.com.rboard.vo.RboardVO;

@RestController
@RequestMapping(value="rboard")
public class RboardController {
	Logger logger = Logger.getLogger(RboardController.class);
	
	@Autowired(required=false)
	private RboardService rboardService;
	
	@Autowired(required=false)
	private BoardChabunService chabunService;
	
	/*
	 * 댓글 글목록 구현하기
	 * @return List<ReplyVO>
	 * 참고:@PathVariable는 URI의 경로에서 원하는 데이터를 추출하는 용도로 사용.
	 * 		ResponseEntity 타입은 개발자가 직접 결과 데이터와 HTTP의 상태 코드를
	 * 		직접 제어할 수 있는 클래스이다. 404나 500 같은 상태코드를 전송하고 싶은
	 * 		데이터와 함께 전송할 수 있기 때문에 좀 더 세밀한 제어가 가능.
	 **/
	@RequestMapping(value = "all/{bnum}.ggd", method=RequestMethod.GET)
	public ResponseEntity<List<RboardVO>> list(@PathVariable("bnum")String bnum){
		
		ResponseEntity<List<RboardVO>> entity = null;
		try {
				entity = new ResponseEntity<>(rboardService.rboardList(bnum),HttpStatus.OK);
		} catch (Exception e) {
				e.printStackTrace();
				entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	/*
	 * 댓글 글쓰기 구현하기
	 * @return String
	 * 참고 : @RequestBody
	 * */
	@RequestMapping(value= "/rboardInsert")
	public ResponseEntity<String> rboardInsert(@RequestBody RboardVO rvo){
		logger.info("rboardInsert 호출 성공");
		int cnt;
		// 댓글 번호 채번
		String rbnum = BoardChabunUtil.getRboardChabun("N", chabunService.getRboardChabun().getRbnum());
		logger.info("rboardInsert 호출 성공2");
		rvo.setRbnum(rbnum);
		logger.info("rboardInsert 호출 성공3");
		ResponseEntity<String> entity = null;
		int result;
		logger.info("rboardInsert 호출 성공4");
		try {
			result = rboardService.rboardInsert(rvo);
			logger.info("rboardInsert 호출 성공5");
			if (result == 1) {
				 	entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		logger.info(rvo.getBnum());
		cnt=rboardService.rboardCntService(rvo.getBnum().toString());
		return entity;
	}
	
	/*
	 * 댓글 수정 구현하기
	 * @return
	 * 참고:REST 방식에서 UPDATE 작업은 PUT,PATCH 방식을 이용해서 처리.
	 * 	     전체 데이터를 수정하는 경우에는 PUT을 이용하고,
	 * 	     일부의 데이터를 수정하는 경우에는 PATCH를 이용.
	 **/
	@RequestMapping(value="/{rbnum}.ggd", method= {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> rboardUpdate(@PathVariable("rbnum") String rbnum,
											  @RequestBody RboardVO rvo){
		logger.info("rboardUpdate 호출 성공");
		logger.info(rvo.getRbcontent());
		ResponseEntity<String> entity = null;
		try {
			rvo.setRbnum(rbnum);
			rboardService.rboardUpdate(rvo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	/*
	 * 댓글 삭제 구현하기
	 * @return
	 * 참고:REST 방식에서 DELETE 작업은 DELETE 방식을 이용해서 처리.
	 * */
	@RequestMapping(value = "/{rbnum}.ggd", method=RequestMethod.DELETE)
	public ResponseEntity<String> rboardDelete(@PathVariable("rbnum") String rbnum){
		logger.info("rboardDelete 호출 성공");
		ResponseEntity<String> entity = null;
		try {
			rboardService.rboardDelete(rbnum);
		 	entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	

	// 안드로이드 댓글
	@ResponseBody
	@RequestMapping(value = "/all2/{bnum}.ggd", method = RequestMethod.GET, produces ="application/text; charset=utf8")
	public String list2(@PathVariable("bnum") String bnum) {

		List<RboardVO> rboards = rboardService.rboardList(bnum);
		JSONObject json = new JSONObject();
		JSONArray jArray = new JSONArray();
		for (int i=0;i<rboards.size(); i++)
		{
			RboardVO avo = rboards.get(i);
			JSONObject jobj = new JSONObject();

			jobj.put("rbname", avo.getRbname());
			jobj.put("rbcontent", avo.getRbcontent());
			jobj.put("rbdate", avo.getRbdate());

			jArray.add(i, jobj);
		}
		
		json.put("rboard", jArray);
		logger.info(json);

		return json.toString();
	}
}
