package controller.noise;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.manager.NoiseManager;

public class ReportNoiseController implements Controller{
	// 쉿! 게시판 신고
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String myroom = request.getParameter("myroom");
		String residence = request.getParameter("residence");
		String floor = request.getParameter("floor");
		String room = request.getParameter("room");
		String reportInfo = residence + floor + room;
				
		NoiseManager manager = NoiseManager.getInstance();

		request.setAttribute("reportResult", manager.noiseReport(reportInfo));
		request.setAttribute("myNoiseCount", manager.myNoiseCount(myroom));				
		request.setAttribute("noiseRanking", manager.findNoiseRank());

        return "noise/NoiseMainView.jsp";
	}

}
