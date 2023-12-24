package controller.notification;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dto.MessageDTO;
import model.dto.User;
import model.manager.MessageManager;

public class PostMessageListController {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    HttpSession session = request.getSession();
	    User user = (User) session.getAttribute("user");
	    String senderID = user.getUserId();
	    String receiverID = user.getUserId();

	    MessageManager messageManager = MessageManager.getInstance();

	    // 사용자가 받은 쪽지 조회
	    List<MessageDTO> receivedMessages = messageManager.getMessagesForReceiver(receiverID);
	    request.setAttribute("receivedMessages", receivedMessages);

	    // 사용자가 보낸 쪽지 조회
	    List<MessageDTO> sentMessages = messageManager.getMessagesForSender(senderID); // 이 메소드는 구현 필요
	    request.setAttribute("sentMessages", sentMessages);

	    return "/notification/PostMessageMainView.jsp";
	}


}
