package controller.free;

import controller.Controller;
import model.FindDTO;
import model.FreeDTO;
import model.dao.FindDAO;
import model.manager.FindManager;
import model.manager.FreeManager;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FreeUpdateController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(FreeUpdateController.class);
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
		
//		FreeDTO post = new FreeDTO(
//				Integer.parseInt(request.getParameter("freepostID")),
//				request.getParameter("title"),
//				request.getParameter("prefer"),
//				request.getParameter("content"));
//		
//		FreeManager manager = FreeManager.getInstance();
//		manager.update(post);		
		 return "redirect:/free/freelist";
	}
}
