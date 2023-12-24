package controller.find;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.dao.FindDAO;
import model.dto.FindDTO;
import model.dto.MatchDTO;
import model.dto.MatchDetailDTO;
import model.manager.FindManager;

//create
public class FindPostController implements Controller{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		// 세션에서 userID 가져오기
        HttpSession session = request.getSession();
        String userID = (String) session.getAttribute("userId");
        
        FindDAO findDAO = new FindDAO();
        // 데이터 검색
        List<String> lifepattern = findDAO.getLifePatternsByUserID(userID);// JSP 페이지로 데이터 전달
        request.setAttribute("lifepattern", lifepattern);
        
		if(request.getMethod().equals("GET")) {
			
			return "/find/FindPostView.jsp";
		}
		else {
			// 사용자가 입력한 데이터 가져오기
	        String isAnonymous = request.getParameter("isAnonymous");
	        String title = request.getParameter("title");
	        String prefer = request.getParameter("prefer");
	        String mycontent = lifepattern.stream().collect(Collectors.joining(", "));
	        String matecontent = request.getParameter("matecontent");
	        // FindDTO 객체 생성
			FindDTO post = new FindDTO(1,
//					Integer.parseInt(request.getParameter("findpostID")),
					isAnonymous,
					title,
					prefer,
					mycontent,
					matecontent,
					userID);
	
	        // FindManager를 통해 데이터 저장
	        FindManager manager = FindManager.getInstance();
	        manager.create(post);
	
	        request.setAttribute("findpost", post);
	        // 저장이 완료된 후 다음 화면으로 이동
//	        return "/find/FindCheckPostView.jsp";
	        return "redirect:/find/findlist";
		}
    }
}
