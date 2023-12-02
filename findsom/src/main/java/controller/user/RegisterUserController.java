package controller.user;

<<<<<<< Updated upstream
import java.util.List;
=======
import java.util.*;
>>>>>>> Stashed changes

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
<<<<<<< Updated upstream
import model.Community;
=======
>>>>>>> Stashed changes
import model.User;
import model.service.ExistingUserException;
import model.service.UserManager;

public class RegisterUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       	if (request.getMethod().equals("GET")) {	
    		// GET request: 회원정보 등록 form 요청	
    		log.debug("RegisterForm Request");

<<<<<<< Updated upstream
    		List<Community> commList = UserManager.getInstance().findCommunityList();	// 커뮤니티 리스트 검색
			request.setAttribute("commList", commList);	
=======
//    		List<Community> commList = UserManager.getInstance().findCommunityList();	// 커뮤니티 리스트 검색
//			request.setAttribute("commList", commList);	
>>>>>>> Stashed changes
		
			return "/user/registerForm.jsp";   // 검색한 커뮤니티 리스트를 registerForm으로 전송     	
	    }	

    	// POST request (회원정보가 parameter로 전송됨)
       	User user = new User(
			request.getParameter("userId"),
<<<<<<< Updated upstream
			request.getParameter("password"),
			request.getParameter("name"),
			request.getParameter("email"),
			request.getParameter("phone"),
			Integer.parseInt(request.getParameter("commId")));
=======
			request.getParameter("email"),
			request.getParameter("password"),
			request.getParameter("phone"),
			request.getParameter("name"),
			request.getParameter("nickname"),
			request.getParameter("authentication"),
			request.getParameter("isRecruite"),
			request.getParameter("roomInfo")
//			request.getParameter("lifePatterns")
			);
>>>>>>> Stashed changes
		
        log.debug("Create User : {}", user);

		try {
			UserManager manager = UserManager.getInstance();
			manager.create(user);
<<<<<<< Updated upstream
	        return "redirect:/user/list";	// 성공 시 사용자 리스트 화면으로 redirect
=======
	        return "redirect:/findsom/RandingView.jsp";	// 성공 시 사용자 리스트 화면으로 redirect
>>>>>>> Stashed changes
	        
		} catch (ExistingUserException e) {	// 예외 발생 시 회원가입 form으로 forwarding
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("user", user);
<<<<<<< Updated upstream
			return "/user/registerForm.jsp";
=======
			return "/findsom/SignupView.jsp";
>>>>>>> Stashed changes
		}
    }
}

