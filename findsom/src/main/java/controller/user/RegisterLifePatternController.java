package controller.user;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.dto.LifePattern;
import model.dto.User;
import model.manager.ExistingUserException;
import model.manager.UserManager;

public class RegisterLifePatternController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       	if (request.getMethod().equals("GET")) {	
    		// GET request: 회원정보 등록 form 요청	
    		log.debug("RegisterForm Request");

    		return "/findsom/Signup2View.jsp";    	
	    }
       	
       	User user = (User) request.getSession().getAttribute("user");
       	
       	LifePattern lifePattern = new LifePattern(
                request.getParameter("isMorningPerson"),
                request.getParameter("isSmoker"),
                request.getParameter("employmentPeriod"),
                request.getParameter("mbti"),
                request.getParameter("showerTime"),
                request.getParameter("wakeUpTime"),
                request.getParameter("teethGrinding"),
                request.getParameter("snoring"),
                request.getParameter("ear"),
                request.getParameter("hasFriendship"),
                request.getParameter("hasEarphones"),
                request.getParameter("cleanliness"),
                request.getParameter("eatInRoom"),
                request.getParameter("age"),
                request.getParameter("bedPreference")
                );

		try {
			UserManager manager = UserManager.getInstance();
			manager.create(user);
			manager.createLifePattern(lifePattern, user);
			return "redirect:/findsom/SignupCompleteView.jsp";
	        
		} catch (ExistingUserException e) {	// 예외 발생 시 회원가입 form으로 forwarding
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("user", user);
			return "redirect:/findsom/SignupView.jsp";
		}
    }
}
