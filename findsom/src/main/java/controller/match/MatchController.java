package controller.match;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dto.MatchDTO;
import model.dto.MatchDetailDTO;
import model.dto.User;
import model.manager.MatchManager;

public class MatchController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MatchManager matchMan = MatchManager.getInstance();
		
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
				
				for(String s : lifePatterns) {
					System.out.print(s + " ");
				}

				ArrayList<MatchDTO> matchingResult = matchMan.matching(lifePatterns);
				
				request.setAttribute("matchingResult", matchingResult);
				
				return "/match/MatchView.jsp";
			}
		}
		else if (request.getServletPath().equals("/match/detail")) {
		    // 매칭 상세 기능 구현
			String userID = request.getParameter("matchingUserID");
						
			MatchDetailDTO matchingDetailResult = matchMan.matchDetail(userID);
			request.setAttribute("matchingDetailResult", matchingDetailResult );
			
			return "/match/MatchDetailView.jsp";
		}
		return null;
	}
	// matching Test
	/*
	public static void main(String[] args) throws SQLException {
		MatchManager matchMan = MatchManager.getInstance();;
		
		ArrayList<String> pattern = new ArrayList<String>();
		pattern.add("morning");
		pattern.add("smoker");
		pattern.add("yesclean");
		pattern.add("yesEatInRoom");
		pattern.add("2");	
		
		ArrayList<MatchDTO> matchingResult = matchMan.matching(pattern);
		
		for (MatchDTO u : matchingResult) {
			System.out.println(u.toString());
		}
	}
	*/
}
