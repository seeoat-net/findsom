package controller.find;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.FindDTO;
import model.dao.FindDAO;
import model.manager.FindManager;

public class FindSearchContoller implements Controller{
	 public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
		 String keyword = request.getParameter("searchText");
		 
//			FindManager manager = FindManager.getInstance();
//			manager.search(request.getParameter("searchText"));
		try {
		    FindDAO findDAO = new FindDAO();
		    
		    // 검색어를 이용하여 구인 게시판 글 검색
		    FindDTO foundPost = findDAO.search(keyword);

		    if (foundPost != null) {
		        // 검색 결과가 있다면 해당 글을 보여주는 페이지로 이동
		        request.setAttribute("foundPost", foundPost);
		        request.getRequestDispatcher("FindSearchResultView.jsp").forward(request, response);
		    } else {
		        // 검색 결과가 없으면 메시지를 설정하여 다른 페이지로 이동
		        request.setAttribute("message", "검색 결과가 없습니다.");
		        request.getRequestDispatcher("FindMainView.jsp").forward(request, response);
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
			
		 return "find/FindSearchResultView.jsp";
	 }
}