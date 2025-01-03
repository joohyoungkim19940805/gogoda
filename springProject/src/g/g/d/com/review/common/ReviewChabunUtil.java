package g.g.d.com.review.common;

public abstract class ReviewChabunUtil {
	
	public static final String BIZ_GUBUN_RE = "RE"; // 리뷰
	
	public static String numPad(String t, String c) {
		
		for(int i=c.length(); i<4; i++) {
			c = "0" + c;
		}
		String ymd = ReviewDateFormatUtil.ymdFormats(t);
		
		return ymd.concat(c);
	}
	
	// 리뷰 번호
	public static String getReviewChabun(String type, String reNum) {
		return BIZ_GUBUN_RE.concat(ReviewChabunUtil.numPad(type, reNum));
	}
}
