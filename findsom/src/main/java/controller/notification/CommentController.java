package controller.notification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger log = LoggerFactory.getLogger(CommentController.class);

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        String userID = (String) session.getAttribute("userId"); 
        
        String content = request.getParameter("content");

        // freepostID와 findpostID 파싱 개선
        int freepostID = parseParameterAsInt(request, "freepostID");
        int findpostID = parseParameterAsInt(request, "findpostID");
        
        CommentManager commentManager = new CommentManager(); 

        if (request.getMethod().equals("POST")) {
            CommentDTO newComment = new CommentDTO(1, content, LocalDateTime.now(), userID, freepostID, findpostID);
            commentManager.createComment(newComment);
            createCommentNotification(newComment, userID);
            
            if (freepostID > 0) {
                return "redirect:/free/freecheck?freepostID=" + freepostID;
            } else if (findpostID > 0) {
                return "redirect:/find/findcheck?findpostID=" + findpostID;
            }  	    
        } else if (request.getMethod().equals("GET")) {
            if (freepostID > 0) {
                List<CommentDTO> comments = commentManager.freeCommentsByFreepostID(freepostID);
                request.setAttribute("comments", comments);
                return "redirect:/free/freecheck?freepostID=" + freepostID;
                } else if (findpostID > 0) {
                List<CommentDTO> comments = commentManager.findCommentsByFindpostID(findpostID);
                request.setAttribute("comments", comments);
                return "redirect:/find/findcheck?findpostID=" + findpostID;
            }       
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
	 private void createCommentNotification(CommentDTO comment, String
	 senderID) { try { NotificationManager notificationManager =
	 NotificationManager.getInstance(); NotificationDTO notification = new
	 NotificationDTO();
	 
	 String receiverID = getReceiverIDForComment(comment);
	 notification.setReceiverID(receiverID); notification.setSenderID(senderID);
	 notification.setCommentID(comment.getCommentID());
	 notification.setPostID(comment.getFreepostID());
	 notification.setPostID(comment.getFindpostID());
	 notification.setNotiType("comment");
	 notification.setNotiTypeID(String.valueOf(comment.getCommentID()));
	 notification.setIsChecked("0"); // 새 알림은 확인되지 않은 상태
	  
	 notificationManager.pushNotification(notification); } catch (Exception e) {
	 e.printStackTrace(); } } 
	  
	  
	 private String getReceiverIDForComment(CommentDTO
	 comment) { String receiverID = null; int postID = comment.getFreepostID() !=
	  0 ? comment.getFreepostID() : comment.getFindpostID(); String postType =
	  comment.getFreepostID() != 0 ? "free" : "find";
	  
	  try { JDBCUtil jdbcUtil = new JDBCUtil();
	  
	  String query; if ("free".equals(postType)) { query =
	  "SELECT userID FROM FreeBoardPost WHERE freepostID = ?"; } else { query =
	  "SELECT userID FROM FindBoardPost WHERE findpostID = ?"; }
	  
	  jdbcUtil.setSqlAndParameters(query, new Object[]{postID});
	  
	  try { ResultSet rs = jdbcUtil.executeQuery(); // 쿼리 실행 
	  if (rs.next()) {
	  receiverID = rs.getString("userID"); 
	  } } finally { jdbcUtil.close(); // 리소스반환 
	  } } catch (Exception e) { e.printStackTrace(); }
	  
	  
	  return receiverID; }
	 

}
 
