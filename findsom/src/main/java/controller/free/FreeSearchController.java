package controller.free;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.FreeDTO;
import model.dao.FreeDAO;
import model.manager.FreeManager;

public class FreeSearchController implements Controller{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
		 	String keyword = request.getParameter("searchText");
			FreeDAO freeDAO = new FreeDAO();
			 
			List<FreeDTO> searchPost = freeDAO.search(keyword);
			 
			if (searchPost != null) {
			     // 검색 결과가 있다면 해당 글을 보여주는 페이지로 이동
			     request.setAttribute("searchPost", searchPost);
//			     request.getRequestDispatcher("FindSearchResultView.jsp").forward(request, response);
			 } else {
			     // 검색 결과가 없으면 메시지를 설정하여 다른 페이지로 이동
			     request.setAttribute("message", "검색 결과가 없습니다.");
//			     request.getRequestDispatcher("FindMainView.jsp").forward(request, response);
			 }
		 
		 return "/free/FreeSearchResultView.jsp";
	 }
}
