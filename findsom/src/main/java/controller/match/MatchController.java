package controller.match;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.dto.User;
import model.dto.MatchDTO;
import model.dto.MatchDetailDTO;
import model.dto.User;
import model.manager.MatchManager;

public class MatchController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MatchManager matchMan = MatchManager.getInstance();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		if (request.getServletPath().equals("/match/matching")) {
			if (request.getMethod().equals("GET")) {
				return "/match/MatchView.jsp"; 			
			}
			else {
				// 매칭 기능 구현
					ArrayList<String> lifePatterns = new ArrayList<String>();
					String[] strs = request.getParameterValues("lifePatterns");
					String mbti = request.getParameter("mbti");
					lifePatterns.addAll(Arrays.asList(strs));
					lifePatterns.add(mbti);
					
				try {
					for(String s : lifePatterns) {
						System.out.print(s + " ");
					}
	
					ArrayList<MatchDTO> matchingResult = matchMan.matching(lifePatterns, user.getUserId());
					
					request.setAttribute("matchingResult", matchingResult);
					
					return "/match/MatchView.jsp";
				} catch (Exception e) {  
					 return "redirect:/findsom/RandingView.jsp";			
				}
			}
		}
		else if (request.getServletPath().equals("/match/detail")) {
		    // 매칭 상세 기능 구현
			
			String userID = request.getParameter("matchingUserID");
			System.out.print(userID);
			
			try {
				MatchDetailDTO matchingDetailResult = matchMan.matchDetail(userID);
				matchingDetailResult.setNickname(request.getParameter("matchingUserNickname"));
				request.setAttribute("matchingDetail", matchingDetailResult );
				System.out.println( matchingDetailResult.toString() );
				
				return "/match/MatchDetailView.jsp";
			
			} catch (Exception e) {  
				 return "redirect:/match/matching";			
			}
		}
		return "redirect:/user/mypageMain";
	}

}
