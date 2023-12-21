package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.service.UserManager;
import model.LifePattern;
import model.User;

public class UpdateFormController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {    	
    	String userId = request.getParameter("userId");
    	
    	log.debug("Update Request : {}", userId);
    	
    	UserManager manager = UserManager.getInstance();
      User user = manager.findUser(userId); // 수정하려는 사용자 정보 검색
      request.setAttribute("user", user);
      LifePattern lifePattern = manager.findLifePattern(userId);
      request.setAttribute("lifePattern", lifePattern);
    	
    	log.debug("UpdateForm User : {}", user);
    	log.debug("UpdateForm LifePattern : {}", lifePattern);
    	request.getSession().setAttribute("user", user); // User 객체를 세션에 저장
    	request.setAttribute("user", user);
    	request.getSession().setAttribute("lifePattern", lifePattern); // User 객체를 세션에 저장
      request.setAttribute("lifePattern", lifePattern);
      
    	return "/findsom/MypageUpdateView.jsp";
    }
}