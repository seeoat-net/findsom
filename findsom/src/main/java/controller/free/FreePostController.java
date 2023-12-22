package controller.free;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.FreeDTO;
import model.dao.FreeDAO;
import model.manager.FreeManager;

public class FreePostController implements Controller{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {	
        
        FreeDAO freeDAO = new FreeDAO();
        if(request.getMethod().equals("GET")) {
			
			return "/free/FreePostView.jsp";
		}
		else {
			FreeDTO post = new FreeDTO(
					2,
					request.getParameter("title"),
					request.getParameter("userID"),
					request.getParameter("isAnonymous"),
					request.getParameter("content"),
					request.getParameter("category"));
			
			FreeManager manager = FreeManager.getInstance();
			manager.create(post);	//키워드 넣기
			
			request.setAttribute("freepost", post);
			
			return "redirect:/free/freelist";
		}
	 }
}
