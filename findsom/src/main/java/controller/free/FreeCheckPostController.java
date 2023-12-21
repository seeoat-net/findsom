package controller.free;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.FreeDTO;
import model.manager.FreeManager;

public class FreeCheckPostController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(FreeUpdateController.class);
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
		 
		FreeManager manager = FreeManager.getInstance();
		int postID = Integer.parseInt(request.getParameter("freepostID"));
		FreeDTO post = manager.freeCheckPost(postID);
		log.debug("postid 입력 완료");
		request.setAttribute("freepost", post);
	
		return "/free/FreeCheckPostView.jsp";
	}
}
