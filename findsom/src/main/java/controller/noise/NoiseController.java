package controller.noise;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.User;
import model.manager.NoiseManager;

public class NoiseController implements Controller {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		if (user.getRoomInfo().equals("00000")) {
			// 방배정X인 상태
			return "/noise/ErrorNoiseView.jsp";
		}
		
		if (request.getMethod().equals("GET")) {	// 쉿! 게시판 조회
			try {
				NoiseManager manager = NoiseManager.getInstance();
	
				request.setAttribute("myNoiseCount", manager.myNoiseCount(user.getUserId()));				
				request.setAttribute("noiseRanking", manager.findNoiseRank());
	
		        return "/noise/NoiseView.jsp";
			} catch (Exception e) {  
				 return "redirect:/findsom/RandingView.jsp";			
			}
		}
		else {	//쉿! 게시판 신고
			String residence = request.getParameter("residence");
			String floor = request.getParameter("floor");
			String room = request.getParameter("room");
			String reportInfo = residence + floor + room;
			
			try {						
				NoiseManager manager = NoiseManager.getInstance();
		
				request.setAttribute("reportResult", manager.noiseReport(reportInfo));
				request.setAttribute("myNoiseCount", manager.myNoiseCount(user.getUserId()));				
				request.setAttribute("noiseRanking", manager.findNoiseRank());
		
		        return "/noise/NoiseView.jsp";
			} catch (Exception e) {  
				 return "redirect:/findsom/RandingView.jsp";			
			}
		}
	}
}
