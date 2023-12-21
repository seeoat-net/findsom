package controller.find;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.free.FreeListController;
import model.FindDTO;
import model.manager.FindManager;
import model.manager.FreeManager;

public class FindListController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(FindListController.class);
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {
			FindManager manager = FindManager.getInstance();
			List<FindDTO> findList = manager.findPostList();
			
			request.setAttribute("findList", findList);
				
			return "/find/FindMainView.jsp";
		}
		else {
			int postID = Integer.parseInt(request.getParameter("findpostID"));
			log.debug("postID=" + postID);
			FindManager manager = FindManager.getInstance();
			manager.remove(postID);
			log.debug("controller-dao-remove 실행 완료");
			
			List<FindDTO> findList = manager.findPostList(); //지운것 제외 전체 리스트 반환
			
			request.setAttribute("findList", findList);
			return "redirect:/find/findlist";
		}
	}
}
