package controller.free;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.dao.FindDAO;
import model.dao.FreeDAO;
import model.dto.FreeDTO;
import model.manager.FreeManager;

public class FreeListController implements Controller{
		private static final Logger log = LoggerFactory.getLogger(FreeListController.class);
		
		public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {	
			FreeManager manager = FreeManager.getInstance();
			
			if (request.getMethod().equals("GET")){
				if  (request.getServletPath().equals("/free/freelist")) {
					List<FreeDTO> freeList = manager.freePostList();
					request.setAttribute("freeList", freeList);
					return "/free/FreeMainView.jsp";
				}
				else if(request.getServletPath().equals("/free/freeinfo")) {	
					List<FreeDTO> infoList = manager.freeInfoList();
					request.setAttribute("infoList", infoList);
					return "/free/FreeInfoView.jsp";
				}
				else if(request.getServletPath().equals("/free/freepurchase")) {
					List<FreeDTO> purchaseList = manager.freePurchaseList();
					request.setAttribute("purchaseList", purchaseList);
					return "/free/FreePurchaseView.jsp";
				}
				else if(request.getServletPath().equals("/free/freeshare")) {
					List<FreeDTO> shareList = manager.freeShareList();
					request.setAttribute("shareList", shareList);
					return "/free/FreeShareView.jsp";
				}
				else if(request.getServletPath().equals("/free/freeother")) {
					List<FreeDTO> otherList = manager.freeOtherList();
					request.setAttribute("otherList", otherList);
					return "/free/FreeOtherView.jsp";
				}
			}
			else {
				int postID = Integer.parseInt(request.getParameter("freepostID"));
				log.debug("postID=" + postID);
//				FreeManager manager = FreeManager.getInstance();
				manager.remove(postID);
				log.debug("controller-dao-remove 실행 완료");
				
				List<FreeDTO> freeList = manager.freePostList(); //지운것 제외 전체 리스트 반환
				
				request.setAttribute("freeList", freeList);
				return "redirect:/free/freelist";
			}
			return null;
			
	}

}
