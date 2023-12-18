package model.manager;

import model.dao.MessageDAO;
import model.dto.MessageDTO;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class MessageManager {
	private static MessageDAO messageDAO;

	private static MessageManager instance;

	private MessageManager() {
		messageDAO = new MessageDAO();
	}

	public static synchronized MessageManager getInstance() {
		if (instance == null) {
			instance = new MessageManager();
		}
		return instance;
	}

	public void close() {
		messageDAO.close();
	}

	public static MessageDTO writeMessage(MessageDTO messageDTO) {
	    try {
	        return messageDAO.writeMessage(messageDTO);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;  // 작성 중에 오류가 발생했을 경우 null을 반환하거나 적절한 방식으로 처리
	    }
	}


	public static void deleteMessage(int messageID) throws Exception {
		try {
			messageDAO.deleteMessage(messageID);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	 public static List<MessageDTO> getMessagesForContent(int messageID) throws SQLException {
	        return messageDAO.getMessagesForContent(messageID);
	    }

	    public static List<MessageDTO> getMessagesForReceiver(String receiverID) throws SQLException {
	        return messageDAO.getMessagesForReceiver(receiverID);
	    }
	/*MessageDTO에 createAtFormatted; 추가하면 사용가능(좀 더 고민해보는걸로)
	 * public static List<MessageDTO> getMessagesForContent(int messageID) throws
	 * SQLException { List<MessageDTO> messages =
	 * messageDAO.getMessagesForContent(messageID);
	 * 
	 * // 여기서 메시지의 createAt 값을 원하는 형식으로 변환 for (MessageDTO message : messages) {
	 * String formattedCreateAt = message.getCreateAtFormatted();
	 * message.setCreateAtFormatted(formattedCreateAt); }
	 * 
	 * return messages; }
	 */
	//테스트
	public static void main(String[] args) {
		MessageDAO messageDAO = new MessageDAO();

		try {
		    MessageDTO newMessage = new MessageDTO(3, null, null, null, null, null);
		    newMessage.setMessageID(3); 
		    newMessage.setMessageText("Test message3");

		    Date currentDate = new Date();
		    if (currentDate != null) {
		        newMessage.setCreateAt(currentDate);
		    }

		    newMessage.setRecognizeID("Recognition data3");
		    newMessage.setSenderID("a");
		    newMessage.setReceiverID("b");

		    MessageDTO resultMessage = messageDAO.writeMessage(newMessage);
		    System.out.println("Write Message Result: " + resultMessage);

            // Test deleteMessage
			/*
			 * int deletedMessageID = 1; // Provide a valid message ID to delete int
			 * deleteResult = messageDAO.deleteMessage(deletedMessageID);
			 * System.out.println("Delete Message Result: " + deleteResult);
			 * 
			 * // Test getMessagesForContent int contentMessageID = 2; // Provide a valid
			 * message ID to retrieve messages List<MessageDTO> messagesForContent =
			 * messageDAO.getMessagesForContent(contentMessageID);
			 * System.out.println("Messages for Content: " + messagesForContent);
			 */
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            messageDAO.close();
        }
    }





	
}
