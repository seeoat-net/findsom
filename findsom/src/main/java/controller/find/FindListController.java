package controller.find;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FindDTO;
import model.manager.FindManager;

public class FindListController {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		FindManager manager = FindManager.getInstance();
		List<FindDTO> findList = manager.findPostList();
		
		request.setAttribute("findList", findList);
			
		return "/find/FindMainView.jsp";
	}
}
