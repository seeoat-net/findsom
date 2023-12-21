package controller.free;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.FreeDTO;
import model.dao.FindDAO;
import model.dao.FreeDAO;
import model.manager.FreeManager;

public class FreeListController implements Controller{
		private static final Logger log = LoggerFactory.getLogger(FreeListController.class);
		public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {	
			if (request.getMethod().equals("GET")) {
			FreeManager manager = FreeManager.getInstance();
			List<FreeDTO> freeList = manager.freePostList();
			
			List<FreeDTO> infoList = manager.freeInfoList();
			List<FreeDTO> purchaseList = manager.freePurchaseList();
			List<FreeDTO> shareList = manager.freeShareList();
			List<FreeDTO> otherList = manager.freeOtherList();
			
			request.setAttribute("freeList", freeList);
			
			request.setAttribute("infoList", infoList);
			request.setAttribute("purchaseList", purchaseList);
			request.setAttribute("shareList", shareList);
			request.setAttribute("otherList", otherList);
			

			return "/free/FreeMainView.jsp";
			}
			else {
				int postID = Integer.parseInt(request.getParameter("freepostID"));
				log.debug("postID=" + postID);
				FreeManager manager = FreeManager.getInstance();
				manager.remove(postID);
				log.debug("controller-dao-remove 실행 완료");
				
//				FreeManager manager = FreeManager.getInstance();
				List<FreeDTO> freeList = manager.freePostList(); //지운것 제외 전체 리스트 반환
				
				request.setAttribute("freeList", freeList);
				return "redirect:/free/freelist";
			}
			
	}

}
