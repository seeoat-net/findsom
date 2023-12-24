package controller.find;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.dao.FindDAO;
import model.dto.FindDTO;

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
			 
			 request.setAttribute("searchPost", searchPost);
				
		 return "/find/FindSearchResultView.jsp";
		}
	 }
}
