package controller.free;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.FreeDTO;
import model.service.FreeManager;

public class FreeListContrller implements Controller{
	 public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
			
		 	FreeManager manager = FreeManager.getInstance();
			List<FreeDTO> postList = manager.
			
			// commList 객체를 request에 저장하여 커뮤니티 리스트 화면으로 이동(forwarding)
			request.setAttribute("commList", commList);				
			return "/community/list.jsp";        
	    }
}
