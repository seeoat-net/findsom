package controller.user;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.dto.User;
import model.manager.ExistingUserException;
import model.manager.UserManager;

public class RegisterUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	// POST request (회원정보가 parameter로 전송됨)
       	User user = new User(
			request.getParameter("userId"),
			request.getParameter("email"),
			request.getParameter("password"),
			request.getParameter("phone"),
			request.getParameter("name"),
			request.getParameter("nickname"),
			request.getParameter("authentication"),
			request.getParameter("isRecruite"),
			request.getParameter("roomInfo")
			);
		
        log.debug("Create User : {}", user);

        request.getSession().setAttribute("user", user); // User 객체를 세션에 저장
        
        return "redirect:/findsom/Signup2View.jsp";
    }
}
