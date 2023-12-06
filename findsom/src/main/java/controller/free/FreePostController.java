package controller.free;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FreeDTO;
import model.manager.FreeManager;

public class FreePostController {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		
		FreeDTO post = new FreeDTO(
				Integer.parseInt(request.getParameter("freepostID")),
				request.getParameter("title"),
				request.getParameter("userID"),
				request.getParameter("isAnonymous"),
				request.getParameter("content"),
				request.getParameter("category"));
		
		FreeManager manager = FreeManager.getInstance();
		manager.create(post);	//키워드 넣기
		
		 return "/free/FreePostView.jsp";
	 }
}
