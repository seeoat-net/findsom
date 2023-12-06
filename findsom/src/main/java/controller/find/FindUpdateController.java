package controller.find;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.FindDTO;
import model.manager.FindManager;

public class FindUpdateController implements Controller{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		
		FindDTO post = new FindDTO(
				Integer.parseInt(request.getParameter("findpostID")),
				request.getParameter("isAnonymous"),
				request.getParameter("title"),
				request.getParameter("prefer"),
				request.getParameter("mycontent"),
				request.getParameter("matecontent"),
				request.getParameter("userID"));
		
		FindManager manager = FindManager.getInstance();
		manager.update(post);
		
		 return "find/FindCheckPostView.jsp";
	 }
}
