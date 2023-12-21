package controller.notification;

import model.dto.MessageDTO;
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

        if ("/message/write".equals(request.getServletPath())) {
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                return handleWriteMessage(request, session, messageManager);
            }
        } else if ("/message/view".equals(request.getServletPath())) {
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
    //쪽지작성
    private String handleWriteMessage(HttpServletRequest request, HttpSession session, MessageManager messageManager) throws SQLException {
        String senderID = (String) session.getAttribute("userID");
        String receiverID = request.getParameter("receiverID");
        String messageText = request.getParameter("messageText");

        MessageDTO newMessage = new MessageDTO(0, messageText, LocalDateTime.now(), senderID, receiverID, null, null);
        MessageDTO writtenMessage = messageManager.writeMessage(newMessage);

        if (writtenMessage != null) {
            request.setAttribute("message", "쪽지 작성 성공");
            return "/message/PostMessageView.jsp";
        } else {
            return "/error.jsp";
        }
    }
    //쪽지 목록 조회
    private String handleViewMessages(HttpServletRequest request, HttpSession session, MessageManager messageManager) throws SQLException {
        String receiverID = (String) session.getAttribute("userID");
        List<MessageDTO> messages = messageManager.getMessagesForReceiver(receiverID);
        request.setAttribute("messages", messages);
        return "/message/PostMessageMainView.jsp";
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