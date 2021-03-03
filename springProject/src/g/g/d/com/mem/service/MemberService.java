package g.g.d.com.mem.service;

import java.util.List;

import g.g.d.com.mem.vo.MemberVO;


public interface MemberService {

	public int memberInsert(MemberVO vo);  // 회원 등록
	public int memberUpdate(MemberVO vo);  // 회원 정보 수정
	public int memberDelete(MemberVO vo);  // 회원 정보 삭제
	public MemberVO memberSelect(MemberVO vo);  // 회원 정보 상세 조회
	public List<MemberVO> memberSelectAll(MemberVO vo);  // 전체 회원 목록 조회
	
	public List<MemberVO> idCheck(MemberVO mvo);  // 아이디 중복 체크
	public int pwCheck(MemberVO mvo); // 회원 정보 수정 및 삭제를 위한 pw 체크
	public int surveyInsert(MemberVO mvo); // 설문 조사 추가
}
