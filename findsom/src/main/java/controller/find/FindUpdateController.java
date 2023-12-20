package controller.find;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.FindDTO;
import model.dao.FindDAO;
import model.manager.FindManager;

public class FindUpdateController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(FindUpdateController.class);
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {	
//		게시물 결과 확인
		if (request.getMethod().equals("GET")) {	
    		// GET request: 커뮤니티 수정 form 요청	
			FindManager manager = FindManager.getInstance();
			int postID = Integer.parseInt(request.getParameter("findpostID"));
			log.debug("postid 입력 완료");
			FindDTO post = manager.findCheckPost(postID);
			request.setAttribute("findpost", post);		
			return "/find/FindCheckPostView.jsp";       
	    }
//		else {	 // 검색한 정보를 update form으로 전송
//			List<String> lifepattern = (List<String>) request.getAttribute("lifepattern");
//			String mycontent = lifepattern.stream().collect(Collectors.joining(","));
//			
//			FindDTO post = new FindDTO(
//					Integer.parseInt(request.getParameter("findpostID")),
//					request.getParameter("title"),
//					request.getParameter("prefer"),
//					request.getParameter("matecontent"));
//			
//			FindManager manager = FindManager.getInstance();
//			manager.update(post);		
//			 return "redirect:/find/findlist";
//		}
		return null;
	 }
}
