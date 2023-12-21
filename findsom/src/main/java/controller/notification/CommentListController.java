package controller.notification;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dto.CommentDTO;
import model.manager.CommentManager;

public class CommentListController implements Controller {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CommentManager manager = new CommentManager();

        String userID = request.getParameter("userID"); // 요청에서 userID 가져오기
        List<CommentDTO> commentList = manager.findCommentsByUserID(userID);

        request.setAttribute("commentList", commentList);

        return "/comment/UserCommtView.jsp"; 
    }
}
