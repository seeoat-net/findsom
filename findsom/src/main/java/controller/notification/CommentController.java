package controller.notification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dto.CommentDTO;
import model.manager.CommentManager;
import controller.Controller;
import java.time.LocalDateTime;
import java.util.List;

public class CommentController implements Controller {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        String userID = (String) session.getAttribute("userId"); 
        
        String content = request.getParameter("content");
        int freepostID = Integer.parseInt(request.getParameter("freepostID"));
        int findpostID = Integer.parseInt(request.getParameter("findpostID"));

        CommentManager commentManager = new CommentManager();
        
        if (request.getMethod().equals("GET")) {
            /// 댓글 조회  	
        	if (freepostID % 2 == 0) {
            	List<CommentDTO> comments = commentManager.freeCommentsByUserID(userID);
                request.setAttribute("comments", comments);
                return "CommPostListView.jsp"; 	
        	}
        	else {
           	 List<CommentDTO> comments = commentManager.findCommentsByUserID(userID);
                request.setAttribute("comments", comments);
                return "CommPostListView.jsp"; 
        	}
             
         } else if (request.getMethod().equals("POST")) {
             // 댓글 작성 로직       
             CommentDTO newComment = new CommentDTO(
                     1, content, LocalDateTime.now(), userID, freepostID, findpostID);
             CommentDTO createdComment = commentManager.createComment(newComment);
             request.setAttribute("comment", createdComment);
             if (freepostID % 2 == 0)
            	 return "/free/FreeCheckPostView.jsp"; 
             else
            	 return "/find/FindCheckPostView.jsp"; // 댓글 작성 확인	
         } else if (request.getMethod().equals("DELETE")) {
             int commentID = Integer.parseInt(request.getParameter("commentID"));
             boolean isDeleted = commentManager.deleteComment(commentID);

             if (isDeleted) {
                 request.setAttribute("message", "Comment deleted successfully.");
             } else {
                 request.setAttribute("error", "Failed to delete the comment.");
             }
             if (freepostID % 2 == 0)
            	 return "/free/FreeCheckPostView.jsp"; 
             else
            	 return "/find/FindCheckPostView.jsp";
         }
         return "/error.jsp"; 
     }
 }
