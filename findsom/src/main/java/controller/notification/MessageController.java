package controller.notification;

import model.dto.MessageDTO;
import model.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
<<<<<<< Updated upstream

<<<<<<< Updated upstream
import java.sql.SQLException;
import java.sql.Date;
=======
import controller.Controller;

import java.time.LocalDateTime;
>>>>>>> Stashed changes
import java.util.List;

public class MessageController implements Controller {
<<<<<<< Updated upstream
    private static final Logger log = LoggerFactory.getLogger(MessageController.class);
=======
import controller.Controller;
import java.sql.SQLException;

import java.time.LocalDateTime;
import java.util.List;

public class MessageController implements Controller {
>>>>>>> Stashed changes

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

<<<<<<< Updated upstream
    private String determineSenderID(HttpServletRequest request) {
        return request.getParameter("senderID");
    }

    private String determineReceiverID(HttpServletRequest request) {
        return request.getParameter("receiverID");
    }

    //시은이 userID를 예림이가 받아오고 그걸 내가 senderID로 받아옴 그래서 세션에서 userID 값을 가져옴 그 userID를 내가 senderID로 저장
    private int determineFreepostID(HttpServletRequest request) {
        // 세션에서 freepostID 값을 가져오기
        HttpSession session = request.getSession();
        Integer freepostID = (Integer) session.getAttribute("freepostID");

        // 세션에 freepostID 값이 없다면 기본값 0 반환
        return freepostID != null ? freepostID : 0;
    }

    private int determineFindpostID(HttpServletRequest request) {
        // 세션에서 findpostID 값을 가져오기
        HttpSession session = request.getSession();
        Integer findpostID = (Integer) session.getAttribute("findpostID");

        // 세션에 findpostID 값이 없다면 기본값 0 반환
        return findpostID != null ? findpostID : 0;
    }

    private String getReceivedMessages(HttpServletRequest request) {
        try {
            // 받은 쪽지 목록을 가져오는 로직
            List<MessageDTO> messagesList = MessageManager.getMessagesForReceiver(request.getParameter("receiverID"));

            // 받은 쪽지 목록을 request에 attribute로 설정
            request.setAttribute("messages", messagesList);

            log.debug("Retrieved received messages for receiver: {}", request.getParameter("receiverID"));

            // PostMessageMainView.jsp로 포워딩
            return "message/PostMessageMainView.jsp";
        } catch (SQLException e) {
            log.error("Error retrieving received messages: {}", e.getMessage());
            return "message/error.jsp";
        }
    }

	/*
	 * 
	 * private String deleteMessage(HttpServletRequest request) { try { int
	 * messageID = Integer.parseInt(request.getParameter("messageID")); int
	 * deletedRows = MessageManager.deleteMessage(messageID);
	 * 
	 * if (deletedRows > 0) { log.debug("Message deleted: {}", messageID); return
	 * "message/PostMessageMainView.jsp"; } else { // 삭제된 행이 없는 경우
	 * log.error("Message deletion failed: {}", messageID); return
	 * "message/error.jsp"; } } catch (Exception e) {
	 * log.error("Error deleting message: {}", e.getMessage()); return
	 * "message/error.jsp"; } }
	 */
=======

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
=======
        if (result > 0) {
            request.setAttribute("message", "쪽지 삭제 성공");
            return "/message/PostMessageMainView.jsp";
        } else {
            return "/error.jsp";
>>>>>>> Stashed changes
        }
    }

    private String handleViewMessages(HttpServletRequest request, MessageManager messageManager) {
        // 쪽지 조회 로직
        String receiverID = request.getParameter("receiverID");
>>>>>>> Stashed changes

        //List<MessageDTO> messages = messageManager.getMessagesForReceiver(receiverID);
       // request.setAttribute("messages", messages);

        return "message_list.jsp"; // 쪽지 목록 페이지
    }
}
