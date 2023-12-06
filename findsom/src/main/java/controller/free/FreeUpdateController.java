package controller.free;

import controller.Controller;
import model.FreeDTO;
import model.manager.FreeManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FreeUpdateController implements Controller{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
		 
		FreeDTO post = new FreeDTO(
				Integer.parseInt(request.getParameter("freepostID")),
				request.getParameter("title"),
				request.getParameter("userID"),
				request.getParameter("isAnonymous"),
				request.getParameter("content"),
				request.getParameter("category"));
		
		FreeManager manager = FreeManager.getInstance();
		manager.update(post);
		
		 return "/free/FreeCheckPostView.jsp";
	 }
}
