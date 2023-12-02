package controller.match;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class MatchController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getServletPath().equals("/matching/form")) {
		    // 매칭 기능 구현
		}
		else if (request.getServletPath().equals("/matching/detail")) {
		    // 매칭 상세 기능 구현
		}
		return null;
	}
	
}
