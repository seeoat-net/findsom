package controller.free;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FreeDTO;
import model.dao.FreeDAO;
import model.manager.FreeManager;

public class FreeSearchController {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
		 String keyword = request.getParameter("searchText");
		  
		 try {
			 FreeDAO freeDAO = new FreeDAO();
			 FreeDTO foundPost = freeDAO.search(keyword);
			 if (foundPost != null) {
			        // 검색 결과가 있다면 해당 글을 보여주는 페이지로 이동
			        request.setAttribute("foundPost", foundPost);
			        request.getRequestDispatcher("FreeSearchResultView.jsp").forward(request, response);
			    } else {
			        // 검색 결과가 없으면 메시지를 설정하여 다른 페이지로 이동
			        request.setAttribute("message", "검색 결과가 없습니다.");
			        request.getRequestDispatcher("FreeMainView.jsp").forward(request, response);
			    }
			} catch (SQLException e) {
			    e.printStackTrace();
			}
		 
		 return "free/FreeSearchResultView.jsp";
	 }
}
