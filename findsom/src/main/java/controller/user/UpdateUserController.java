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

public class UpdateUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {      
        String updateId = request.getParameter("userId");
        
        log.debug("Update Request : {}", updateId);
        
        UserManager manager = UserManager.getInstance();
        User user = manager.findUser(updateId); // 수정하려는 사용자 정보 검색
        request.setAttribute("user", user);        
        LifePattern lifePattern = manager.findLifePattern(updateId);
        request.setAttribute("lifePattern", lifePattern);
        
        User updateUser = new User(
                request.getParameter("userId"),
                request.getParameter("email"),
                request.getParameter("password"),
                request.getParameter("phone"),
                request.getParameter("name"),
                request.getParameter("nickname"),
                request.getParameter("isRecruite"));
//                request.getParameter("roomInfo"));
        
        LifePattern updateLifePattern = new LifePattern(
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
                request.getParameter("bedPreference"));
        
        log.debug("UpdateController User : {}", updateUser);
        log.debug("UpdateController LifePattern : {}", updateLifePattern);
        
        manager.updateUser(updateUser);
        manager.updateLifePattern(updateId, updateLifePattern, updateUser);
        
        return "redirect:/user/mypageMain";
    }
}
