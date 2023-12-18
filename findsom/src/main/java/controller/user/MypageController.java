package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.LifePattern;
import model.User;
import model.service.UserManager;

public class MypageController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = (String) request.getSession().getAttribute("userId");
		
		try {
		    UserManager manager = UserManager.getInstance();
		    
		    User user = manager.findUser(userId);
		    LifePattern lifePattern = manager.findLifePattern(userId);
      
		    System.out.println("MypageController에서 출력한 user : " + user);
		    System.out.println("MypageController에서 출력한 lifePattern : " + lifePattern);

		    request.setAttribute("user", user);
		    request.setAttribute("lifePattern", lifePattern);
      
        return "/findsom/MypageMainView.jsp";
		} catch (Exception e) {
			    request.setAttribute("exception", e);
          return "redirect:/findsom/RandingView.jsp";
		    }
    }
}
