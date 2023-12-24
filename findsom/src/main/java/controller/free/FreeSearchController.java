package controller.free;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.FreeDAO;
import model.dto.FreeDTO;
import model.manager.FreeManager;

public class FreeSearchController implements Controller{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
		 	String keyword = request.getParameter("searchText");
			FreeDAO freeDAO = new FreeDAO();
			 
			List<FreeDTO> searchPost = freeDAO.search(keyword);
			request.setAttribute("searchPost", searchPost);
		 
		 return "/free/FreeSearchResultView.jsp";
	 }
}
