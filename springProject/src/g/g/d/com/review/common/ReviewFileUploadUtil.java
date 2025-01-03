package g.g.d.com.review.common;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class ReviewFileUploadUtil {
	
	
	public static String fileUpload(MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {
		
		System.out.println("fileUpload file>>"+file);
		System.out.println("fileUpload file>>"+request);

		String real_name = null;
		
		String org_name = file.getOriginalFilename();
		
		if(org_name != null && (!org_name.contentEquals(""))) {
			real_name = "review_" + System.currentTimeMillis() + "_" + org_name;
			String docRoot = request.getSession().getServletContext().getRealPath("/reviewuploadstorage");
			System.out.println("docRoot>>>"+docRoot);
			File fileDir = new File(docRoot);
			if(!fileDir.exists()) {
				fileDir.mkdir();
			}
			
			File fileAdd = new File(docRoot + "/" + real_name);
			
			file.transferTo(fileAdd);
		}
		System.out.println("real_name>>>>"+real_name);
		return real_name;
	}
}
