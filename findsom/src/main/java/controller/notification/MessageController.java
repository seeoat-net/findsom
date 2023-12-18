package controller.notification;

import controller.Controller;
import model.dto.MessageDTO;
import model.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(MessageController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String action = request.getParameter("action");

        if ("writeMessage".equals(action)) {
            return writeMessage(request);
		} /*
			 * else if ("deleteMessage".equals(action)) { return deleteMessage(request);
			 
        } */
        else if ("getMessagesForContent".equals(action)) {
            return getMessagesForContent(request);
        } else if ("getReceivedMessages".equals(action)) {
            return getReceivedMessages(request);
        } else {
            return "/error.jsp";
        }
    }

    private String writeMessage(HttpServletRequest request) {
        // 사용자가 입력한 메시지 내용 가져오기
		String messageText = request.getParameter("messageText");//postID를 이렇게 request로 받아오기 세션에서 받아오는게 아니라!!

		// 현재 시간을 생성일로 사용
		Date createAt = new Date(System.currentTimeMillis());

		// 기타 필요한 파라미터는 필요에 따라 추가
		int messageID = 0;  // 예시로 0을 사용, 데이터베이스의 시퀀스 또는 다른 방식으로 유니크한 ID를 생성해야 함

		// 동적으로 RecognizeID 값을 설정함
		String recognizeID = determineRecognizeID(request);

		// 동적으로 SenderID 값을 설정함
		String senderID = determineSenderID(request);

		// 동적으로 ReceiverID 값을 설정함
		String receiverID = determineReceiverID(request);

		// 동적으로 freepostID 값을 설정함
		int freepostID = determineFreepostID(request);

		// 동적으로 findpostID 값을 설정함
		int findpostID = determineFindpostID(request);

		// MessageDTO 객체를 생성함
		MessageDTO messageDTO = new MessageDTO(
		        messageID,
		        messageText,
		        createAt,
		        senderID,
		        receiverID,
		        freepostID,
		        findpostID
		);
		MessageDTO result = MessageManager.writeMessage(messageDTO);

		if (result != null) {
		    // 성공적으로 메시지가 작성되었을 때의 처리
		    log.debug("메시지 전송 성공: {}", result);
		    return "message/PostMessageMainView.jsp";
		} else {
		    // 메시지 작성이 실패했을 때의 처리
		    log.error("메시지 작성 실패");
		    return "message/error.jsp";
		}
    }

    private String determineRecognizeID(HttpServletRequest request) {
        return request.getParameter("recognizeID");
    }

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

    private String getMessagesForContent(HttpServletRequest request) {
        try {
            int messageID = Integer.parseInt(request.getParameter("messageID"));

            List<MessageDTO> messages = MessageManager.getMessagesForContent(messageID);

            request.setAttribute("messages", messages);
            log.debug("Retrieved messages for content: {}", messageID);

            return "message/PostMessageMainView.jsp";
        } catch (SQLException e) {
            log.error("Error retrieving messages: {}", e.getMessage());
            return "message/error.jsp";
        }
    }
}
