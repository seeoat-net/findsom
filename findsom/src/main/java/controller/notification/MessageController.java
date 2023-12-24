package controller.notification;

import model.dto.User;
import model.dto.MessageDTO;
import model.dto.User;
import model.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controller.Controller;
import java.sql.SQLException;

import java.time.LocalDateTime;
import java.util.List;

public class MessageController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    MessageManager messageManager = MessageManager.getInstance();
	    HttpSession session = request.getSession();
	    
	    if ("/notification/writeMessage".equals(request.getServletPath())) {
	        if ("GET".equalsIgnoreCase(request.getMethod())) {
	            return handleWriteMessageView(request, session);
	        }
	    } else if ("/notification/messagePost".equals(request.getServletPath())) {
	        if ("POST".equalsIgnoreCase(request.getMethod())) {
	            return handleWriteMessage(request, session, messageManager);
	        }
	    } else if ("/notification/messageView".equals(request.getServletPath())) {
	        if ("GET".equalsIgnoreCase(request.getMethod())) {
	            return handleViewMessages(request, session, messageManager);
	        }
	    } else if ("/message/delete".equals(request.getServletPath())) {
	        if ("POST".equalsIgnoreCase(request.getMethod())) {
	            return handleDeleteMessage(request, messageManager);
	        }
	    }
	    return "/error.jsp";
	}

    
	//쪽지버튼 누르면 쪽지작성뷰(/notification/PostMessageMainView.jsp)
    private String handleWriteMessageView(HttpServletRequest request, HttpSession session) {
        // 로그인 상태 확인
        User user = (User)session.getAttribute("user");
        String senderID = user.getUserId(); 
        
        if (senderID == null) {
            return "/findsom/RandingView.jsp"; // 로그인하지 않았다면 로그인 페이지로 리디렉트
        }

        // 쪽지 작성 뷰로 이동
        System.out.println("쪽지작성뷰이동");
        
        return "/notification/PostMessageView.jsp";
    }
    
    //쪽지 전송 누르면 디비 저장하는 곳
    private String handleWriteMessage(HttpServletRequest request, HttpSession session, MessageManager messageManager) throws SQLException {
    	User user = (User)session.getAttribute("user"); 
    	String senderID = user.getUserId(); 
        
        if (senderID == null) {
            // 로그인하지 않았거나 userID가 없는 경우 처리
        	return "/findsom/RandingView.jsp"; // 로그인 페이지로 리디렉트
        }
        String receiverID = request.getParameter("receiverID");
        String messageText = request.getParameter("messageText");
        
        int freepostID = parseParameterAsInt(request, "freepostID");
        int findpostID = parseParameterAsInt(request, "findpostID");

        MessageDTO newMessage = new MessageDTO(0, messageText, LocalDateTime.now(), senderID, receiverID, freepostID, findpostID);
        MessageDTO writtenMessage = messageManager.writeMessage(newMessage);

        if (writtenMessage != null) {
        	System.out.println("쪽지작성뷰이동");
            request.setAttribute("message", "쪽지 작성 성공");
            request.setAttribute("sentMessage", writtenMessage); // 보낸 쪽지 정보를 속성으로 추가
            return "/notification/PostMessageMainView.jsp"; 
        } else {
            return "/error.jsp";
        }
    }
    private int parseParameterAsInt(HttpServletRequest request, String paramName) {
        try {
            String paramValue = request.getParameter(paramName);
            return paramValue != null ? Integer.parseInt(paramValue) : 0;
        } catch (NumberFormatException e) {
            return 0; // 파싱에 실패할 경우 기본값으로 0을 반환
        }
    }
	//쪽지 목록 조회//구인매칭자유 나눠야 함 
    private String handleViewMessages(HttpServletRequest request, HttpSession session, MessageManager messageManager) throws SQLException {
        String receiverID = (String) session.getAttribute("userID");
        List<MessageDTO> messages = messageManager.getMessagesForReceiver(receiverID);
        request.setAttribute("messages", messages);
        return "/notification/PostMessageMainView.jsp";
    }

    //쪽지 삭제
    private String handleDeleteMessage(HttpServletRequest request, MessageManager messageManager) throws SQLException {
        int messageID = Integer.parseInt(request.getParameter("messageID"));
        int result = messageManager.deleteMessage(messageID);

        if (result > 0) {
            request.setAttribute("message", "쪽지 삭제 성공");
            return "/message/PostMessageMainView.jsp";
        } else {
            return "/error.jsp";
        }
    }
}