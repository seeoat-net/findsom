package controller.notification;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.User;
import model.dto.PostDTO;
import model.manager.PostManager;

public class PostController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PostManager postMan = PostManager.getInstance();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		try {
			ArrayList<PostDTO> postList = postMan.myPostList(user.getUserId());
			request.setAttribute("postlist", postList);
			
			return "/notification/PostListView.jsp";
		} catch (Exception e) {
			return "redirect:/findsom/RandingView.jsp";
		}
	}

}
