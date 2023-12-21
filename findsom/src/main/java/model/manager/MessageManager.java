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

<<<<<<< Updated upstream
	public static MessageDTO writeMessage(MessageDTO messageDTO) {
	    try {
	        return messageDAO.writeMessage(messageDTO);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;  // 작성 중에 오류가 발생했을 경우 null을 반환하거나 적절한 방식으로 처리
	    }
	}
=======
 // 쪽지 삭제하기 기능
    public int deleteMessage(int messageID) {
        try {
            return messageDAO.deleteMessage(messageID);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;  // 오류 발생 시 0 반환
        }
    }
>>>>>>> Stashed changes


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

<<<<<<< Updated upstream
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





	
=======
    //테스트
   public static void main(String[] args) {
       MessageManager messageManager = MessageManager.getInstance();

        try {
           LocalDateTime now = LocalDateTime.now();
           MessageDTO newMessage = new MessageDTO(1,"저는 솜솜이인데요", now, "c", "d", 2, 4);

            MessageDTO writtenMessage = messageManager.writeMessage(newMessage);
            System.out.println("쪽지 작성 결과: " + writtenMessage);
            
          //쪽지 조회에서 
            // 수신자 ID를 기반으로 쪽지 목록 조회 테스트 
            String receiverID = "d"; // 수신자 ID
            List<MessageDTO> receivedMessages = messageManager.getMessagesForReceiver(receiverID);
            System.out.println("수신된 쪽지 목록: " + receivedMessages);
            
         // 쪽지 삭제 테스트
            if (writtenMessage != null && writtenMessage.getMessageID() != 0) {
                int deleteResult = messageManager.deleteMessage(writtenMessage.getMessageID());
                if (deleteResult > 0) {
                    System.out.println("쪽지가 성공적으로 삭제되었습니다. 삭제된 쪽지 ID: " + writtenMessage.getMessageID());
                } else {
                    System.out.println("쪽지 삭제에 실패했습니다.");
                }
            }
            

            // 특정 쪽지에 대한 쪽지 목록 조회 테스트
            if (writtenMessage != null && writtenMessage.getMessageID() != 0) {
                int messageID = writtenMessage.getMessageID(); // 새로 작성된 쪽지의 ID
                List<MessageDTO> messages = messageManager.getMessagesByMessageID(messageID);
                System.out.println("특정 쪽지에 대한 쪽지 목록: " + messages);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            messageManager.close();
        }
    }

>>>>>>> Stashed changes
}
