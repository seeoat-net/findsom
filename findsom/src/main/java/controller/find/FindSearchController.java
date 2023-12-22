package controller.find;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.FindDTO;
import model.dao.FindDAO;

public class FindSearchController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(FindSearchController.class);
	 public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		 
		if(request.getMethod().equals("GET")) {
			 return null;
		}
		else {
			 String keyword = request.getParameter("searchText");
			 FindDAO findDAO = new FindDAO();
			 // 검색어를 이용하여 구인 게시판 글 검색
			 List<FindDTO> searchPost = findDAO.search(keyword);
	
			 if (searchPost != null) {
			     // 검색 결과가 있다면 해당 글을 보여주는 페이지로 이동
				 log.debug("search 결과 존재");
			     request.setAttribute("searchPost", searchPost);
//			     request.getRequestDispatcher("FindSearchResultView.jsp").forward(request, response);
			 } else {
				 log.debug("search 결과 없음");
			     // 검색 결과가 없으면 메시지를 설정하여 다른 페이지로 이동
			     request.setAttribute("message", "검색 결과가 없습니다.");
//			     request.getRequestDispatcher("FindMainView.jsp").forward(request, response);
			 }
				
		 return "/find/FindSearchResultView.jsp";
		}
	 }
}
