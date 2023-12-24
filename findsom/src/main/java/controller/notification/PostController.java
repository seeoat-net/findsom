package controller.notification;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.dto.PostDTO;
import model.dto.User;
import model.manager.PostManager;

public class PostController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PostManager postMan = PostManager.getInstance();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		try {
			ArrayList<PostDTO> findpostList = postMan.myFindPostList(user.getUserId());
			ArrayList<PostDTO> freepostList = postMan.myFreePostList(user.getUserId());
			
			request.setAttribute("findpostlist", findpostList);
			request.setAttribute("freepostlist", freepostList);
			
			return "/notification/PostListView.jsp";
		} catch (Exception e) {
			return "redirect:/findsom/MyPageMainView.jsp";
		}
	}

}
