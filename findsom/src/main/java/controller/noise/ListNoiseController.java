package controller.noise;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.manager.NoiseManager;

public class ListNoiseController implements Controller {
	// 쉿! 게시판 조회
	// 내 방의 소음 신고 개수, 현재 소음 순위(상위 3) 반환
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String myroom = request.getParameter("myroom");
		
		NoiseManager manager = NoiseManager.getInstance();

		request.setAttribute("myNoiseCount", manager.myNoiseCount(myroom));				
		request.setAttribute("noiseRanking", manager.findNoiseRank());

        return "noise/NoiseMainView.jsp";
	}
}
