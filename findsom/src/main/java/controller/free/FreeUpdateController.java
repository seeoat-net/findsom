package controller.free;

import controller.Controller;
import model.FindDTO;
import model.FreeDTO;
import model.dao.FindDAO;
import model.manager.FreeManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FreeUpdateController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(FreeUpdateController.class);
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
		 
//		FreeDTO post = new FreeDTO(
//				Integer.parseInt(request.getParameter("freepostID")),
//				request.getParameter("title"),
//				request.getParameter("userID"),
//				request.getParameter("isAnonymous"),
//				request.getParameter("content"),
//				request.getParameter("category"));
		if (request.getMethod().equals("GET")) {
		FreeManager manager = FreeManager.getInstance();
		int postID = Integer.parseInt(request.getParameter("freepostID"));
		FreeDTO post = manager.freeCheckPost(postID);
		log.debug("postid 입력 완료");
		request.setAttribute("freepost", post);
		 return "/free/FreeCheckPostView.jsp";
	 }
		return null;
	}
}
