package controller.free;

import controller.Controller;
import model.FreeDTO;
import model.service.FreeManager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FreeUpdateController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(UpdateCommunityController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
 
		int commId = Integer.parseInt(request.getParameter("commId"));
		
		if (request.getMethod().equals("GET")) {	
    		// GET request: 커뮤니티 수정 form 요청	
    		FreeManager manager = FreeManager.getInstance();	
			
			List<FreeDTO> members = manager.
			request.setAttribute("members", members);		
			return "/community/updateForm.jsp";   // 검색한 정보를 update form으로 전송     
	    }	
 

		FreeManager manager = FreeManager.getInstance();
		manager.update(null);			
        return "redirect:/community/list";			
    }
}
