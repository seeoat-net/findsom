package controller.find;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.FindDTO;
import model.dto.CommentDTO;
import model.manager.FindManager;

public class FindCheckPostController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(FindCheckPostController.class);
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		//		게시물 결과 확인
		FindManager manager = FindManager.getInstance();
		int postID = Integer.parseInt(request.getParameter("findpostID"));
		log.debug("postid 입력 완료");
		FindDTO post = manager.findCheckPost(postID);
		List<CommentDTO> comments = manager.findCommentByPostId(postID);
		request.setAttribute("findpost", post);		
		request.setAttribute("comments", comments);
		
		return "/find/FindCheckPostView.jsp";       
	 }
}
