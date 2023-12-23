package controller.notification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.JDBCUtil;
import model.dto.CommentDTO;
import model.dto.NotificationDTO;
import model.manager.CommentManager;
import model.manager.NotificationManager;
import controller.Controller;

import java.sql.ResultSet;
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
            /// 알림함 댓글 조회  	
        	if (freepostID % 2 == 0) {
            	List<CommentDTO> comments = commentManager.freeCommentsByFreepostID(freepostID);
                request.setAttribute("comments", comments);
                return "CommPostListView.jsp"; 	
        	}
        	else {
           	 List<CommentDTO> comments = commentManager.findCommentsByFindpostID(findpostID);
                request.setAttribute("comments", comments);
                return "CommPostListView.jsp"; 
        	}
             
         } 
        
        else if (request.getMethod().equals("POST")) {
             // 댓글 작성 로직       
             CommentDTO newComment = new CommentDTO(
                     1, content, LocalDateTime.now(), userID, freepostID, findpostID);
             CommentDTO createdComment = commentManager.createComment(newComment);
             request.setAttribute("comment", createdComment);
             
             if (freepostID % 2 == 0)
            	 return "/free/FreeCheckPostView.jsp"; 
             else
            	 return "/find/FindCheckPostView.jsp"; // 댓글 작성 확인	
         }
        
        else if (request.getMethod().equals("GET")) {
            /// 댓글 조회  	
        	 if (freepostID % 2 == 0)
            	 return "/free/FreeCheckPostView.jsp"; 
             else
            	 return "/find/FindCheckPostView.jsp";
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


    private int parseParameterAsInt(HttpServletRequest request, String paramName) {
        try {
            String paramValue = request.getParameter(paramName);
            return paramValue != null ? Integer.parseInt(paramValue) : 0;
        } catch (NumberFormatException e) {
            return 0; // 파싱에 실패할 경우 기본값으로 0을 반환
        }
    }
    
	
	 //댓글 알림 
	 private void createCommentNotification(CommentDTO comment, String senderID) { 
		 try { 
			 NotificationManager notificationManager = NotificationManager.getInstance(); 
			 NotificationDTO notification = new NotificationDTO();
			 
			 String receiverID = getReceiverIDForComment(comment);
			 notification.setReceiverID(receiverID); 
			 notification.setSenderID(senderID);
			 notification.setCommentID(comment.getCommentID());
			 
			 int postID = (comment.getFreepostID() != 0 ? comment.getFreepostID() : comment.getFindpostID());
			 notification.setPostID(postID);
			 notification.setMessageID(0);
			 notification.setNotiType("comment");
			 notification.setNotiTypeID(String.valueOf(comment.getCommentID()));
			 notification.setIsChecked("0"); // 새 알림은 확인되지 않은 상태
			  
			 notificationManager.pushNotification(notification); 
			 
		} catch (Exception e) {
			e.printStackTrace(); 
		} 
	} 
	  
	  
	 private String getReceiverIDForComment(CommentDTO comment) { 
		 String receiverID = null; 
		 int postID = comment.getFreepostID() != 0 ? comment.getFreepostID() : comment.getFindpostID(); 
		 String postType = comment.getFreepostID() != 0 ? "free" : "find"; 
		 
		 JDBCUtil jdbcUtil = new JDBCUtil();
			  
		 String query = ""; 
		 if ("free".equals(postType)) { 
			query = "SELECT userID FROM FreeBoardPost WHERE freepostID = ?"; 
		 } else { 
			query = "SELECT userID FROM FindBoardPost WHERE findpostID = ?"; 
		 }
		 
		 jdbcUtil.setSqlAndParameters(query, new Object[]{postID});
		 try { 
			 ResultSet rs = jdbcUtil.executeQuery(); // 쿼리 실행 
			 if (rs.next()) {
				 receiverID = rs.getString("userID"); 
			 } 
		 } catch (Exception e) { 
			 e.printStackTrace(); 
	 	 } finally { jdbcUtil.close(); }// 리소스반환 
		 
		 System.out.println("getReceiverIDForComment: " + receiverID);
		 return receiverID; 
	  }
	 

}
 

