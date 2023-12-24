package controller.noise;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
<<<<<<< Updated upstream
=======
import model.dto.User;
>>>>>>> Stashed changes
import model.manager.NoiseManager;

public class NoiseController implements Controller {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {	// 쉿! 게시판 조회
			HttpSession session = request.getSession();
			String userID = (String)session.getAttribute("userId");
			
			NoiseManager manager = NoiseManager.getInstance();

			request.setAttribute("myNoiseCount", manager.myNoiseCount(userID));				
			request.setAttribute("noiseRanking", manager.findNoiseRank());

	        return "/noise/NoiseMainView.jsp";
		}
		else {	//쉿! 게시판 신고
			HttpSession session = request.getSession();
			String userID = (String)session.getAttribute("userId");
			
			String residence = request.getParameter("residence");
			String floor = request.getParameter("floor");
			String room = request.getParameter("room");
			String reportInfo = residence + floor + room;
					
			NoiseManager manager = NoiseManager.getInstance();
	
			request.setAttribute("reportResult", manager.noiseReport(reportInfo));
			request.setAttribute("myNoiseCount", manager.myNoiseCount(userID));				
			request.setAttribute("noiseRanking", manager.findNoiseRank());
	
	        return "/noise/NoiseMainView.jsp";
		}
	}
}
