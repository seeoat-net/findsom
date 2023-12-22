package controller.free;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.FindDTO;
import model.FreeDTO;
import model.dto.CommentDTO;
import model.manager.FreeManager;

public class FreeCheckPostController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(FreeUpdateController.class);
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
		 
		FreeManager manager = FreeManager.getInstance();
		int postID = Integer.parseInt(request.getParameter("freepostID"));
		log.debug("postid 입력 완료");
		FreeDTO post = manager.freeCheckPost(postID);
		List<CommentDTO> comments = manager.freeCommentByPostId(postID);
		request.setAttribute("freepost", post);
		request.setAttribute("comments", comments);
	
		return "/free/FreeCheckPostView.jsp";
	}
}
