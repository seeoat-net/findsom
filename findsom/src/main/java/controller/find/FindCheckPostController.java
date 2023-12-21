package controller.find;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.FindDTO;
import model.manager.FindManager;

public class FindCheckPostController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(FindCheckPostController.class);
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {	
//		게시물 결과 확인
    	// GET request: 커뮤니티 수정 form 요청	
		FindManager manager = FindManager.getInstance();
		int postID = Integer.parseInt(request.getParameter("findpostID"));
		log.debug("postid 입력 완료");
		FindDTO post = manager.findCheckPost(postID);
		request.setAttribute("findpost", post);		
		
		return "/find/FindCheckPostView.jsp";       
	 }
}
