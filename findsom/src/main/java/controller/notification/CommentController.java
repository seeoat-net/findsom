package controller.notification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.dto.CommentDTO;
import model.manager.CommentManager;
import controller.Controller;
import java.time.LocalDateTime;
import java.util.List;

public class CommentController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(CommentController.class);
public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        String userID = (String) session.getAttribute("userId"); 
        
        String content = request.getParameter("content");
        int freepostID = Integer.parseInt(request.getParameter("freepostID"));
        int findpostID = Integer.parseInt(request.getParameter("findpostID"));
        
        CommentManager commentManager = new CommentManager(); 

        if (request.getMethod().equals("POST")) {
            // 댓글 작성 로직
            try {
                if (request.getParameter("freepostID") != null) {
                    freepostID = Integer.parseInt(request.getParameter("freepostID"));
                }
                if (request.getParameter("findpostID") != null) {
                    findpostID = Integer.parseInt(request.getParameter("findpostID"));
                }
            } catch (NumberFormatException e) {
                return "/error.jsp";
            }

            CommentDTO newComment = new CommentDTO(1, content, LocalDateTime.now(), userID, freepostID, findpostID);
            commentManager.createComment(newComment);

        	if (freepostID % 2 == 0) {
        		log.debug("freecomments 입력 완료");
        		return "redirect:/free/comment"; 
        	}
        	    
        	else {
        		log.debug("findcomments 입력 완료");
        		return "redirect:/find/comment"; // 댓글 작성 후 페이지 이동
        	}       	    
         }
        
        else if (request.getMethod().equals("GET")) {
            /// 댓글 조회  	
        	 if (freepostID % 2 == 0) {
        		 List<CommentDTO> comments = commentManager.freeCommentsByUserID(userID);
        	     request.setAttribute("comments", comments);
        		 log.debug("freecomments 입력 완료");
        		 return "redirect:/free/comment"; 
        	 }
            	
             else{
            	 List<CommentDTO> comments = commentManager.findCommentsByUserID(userID);
        	     request.setAttribute("comments", comments);
         		log.debug("findcomments 입력 완료");
         		return "redirect:/find/comment";
         	}       
         } 
        
        else if (request.getMethod().equals("DELETE")) {
        	//댓글 삭제 로직
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
