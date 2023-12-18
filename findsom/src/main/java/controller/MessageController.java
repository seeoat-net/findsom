package controller;

import model.dto.MessageDTO;
import model.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

public class MessageController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MessageManager messageManager = MessageManager.getInstance();

        String path = request.getServletPath();
        
        if ("/message/write".equals(path)) {
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                return handleWriteMessage(request, messageManager);
            }
        } else if ("/message/view".equals(path)) {
            if ("GET".equalsIgnoreCase(request.getMethod())) {
                return handleViewMessages(request, messageManager);
            }
        }
        
        // 기본적으로 오류 페이지나 적절한 메시지 페이지로 리디렉션
        return "error.jsp";
    }

    private String handleWriteMessage(HttpServletRequest request, MessageManager messageManager) {
        // 쪽지 작성 로직
        String senderID = request.getParameter("senderID");
        String receiverID = request.getParameter("receiverID");
        String messageText = request.getParameter("messageText");
        int freepostID = Integer.parseInt(request.getParameter("freepostID")); // 예시 파라미터
        int findpostID = Integer.parseInt(request.getParameter("findpostID")); // 예시 파라미터

        MessageDTO messageDTO = new MessageDTO(
            0, messageText, LocalDateTime.now(), senderID, receiverID
        );

        MessageDTO result = messageManager.writeMessage(messageDTO);

        if (result != null) {
            request.setAttribute("message", "쪽지가 성공적으로 전송되었습니다.");
            return "message_success.jsp"; // 쪽지 전송 성공 페이지
        } else {
            request.setAttribute("error", "쪽지 전송에 실패했습니다.");
            return "message_error.jsp"; // 쪽지 전송 실패 페이지
        }
    }

    private String handleViewMessages(HttpServletRequest request, MessageManager messageManager) {
        // 쪽지 조회 로직
        String receiverID = request.getParameter("receiverID");

        //List<MessageDTO> messages = messageManager.getMessagesForReceiver(receiverID);
       // request.setAttribute("messages", messages);

        return "message_list.jsp"; // 쪽지 목록 페이지
    }
}
