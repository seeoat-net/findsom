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
    	String updateId = request.getParameter("userId");
    	
    	log.debug("Update Request : {}", updateId);
    	
    	UserManager manager = UserManager.getInstance();
        User user = manager.findUser(updateId); // 수정하려는 사용자 정보 검색
        request.setAttribute("user", user);        
        LifePattern lifePattern = manager.findLifePattern(updateId);
        request.setAttribute("lifePattern", lifePattern);
    	
    	log.debug("UpdateForm User : {}", user);
//    	request.getSession().setAttribute("user", user); // User 객체를 세션에 저장
    	request.setAttribute("user", user); 
    	// ↑ UpdateFormController에서는 사용자 및 LifePattern 속성을 요청 범위에 설정하고 있습니다.
    	// 그러나 세션 범위에도 사용자 속성을 설정하고 있습니다. 세션에 설정하지 말고 요청 범위에만 설정하세요

    	return "/findsom/MypageUpdateView.jsp";
    }
}
