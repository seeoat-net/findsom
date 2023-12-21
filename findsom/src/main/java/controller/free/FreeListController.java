package controller.free;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.FreeDTO;
import model.manager.FreeManager;

public class FreeListController implements Controller{
		public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {	
			FreeManager manager = FreeManager.getInstance();
			List<FreeDTO> freeList = manager.freePostList();
			
			request.setAttribute("freeList", freeList);
				
			return "/free/FreeMainView.jsp";
	}
}
