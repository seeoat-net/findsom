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
        int freepostID = parseParameterAsInt(request, "freepostID");
        int findpostID = parseParameterAsInt(request, "findpostID");
        
        List<CommentDTO> commentList1 = manager.freeCommentsByFreepostID(freepostID);
        List<CommentDTO> commentList2 = manager.findCommentsByFindpostID(findpostID);

        request.setAttribute("commentList", commentList1);
        request.setAttribute("commentList", commentList2);
        return "/notification/CommentListView.jsp"; 
    }

    private int parseParameterAsInt(HttpServletRequest request, String paramName) {
        try {
            String paramValue = request.getParameter(paramName);
            return paramValue != null ? Integer.parseInt(paramValue) : 0;
        } catch (NumberFormatException e) {
            return 0; // 파싱에 실패할 경우 기본값으로 0을 반환
        }
    }
}
