package model.manager;

import model.dao.MessageDAO;
import model.dto.MessageDTO;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class MessageManager {
    private MessageDAO messageDAO;
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

    // 쪽지 보내기 기능
    public MessageDTO writeMessage(MessageDTO messageDTO) {
        try {
            return messageDAO.writeMessage(messageDTO);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;  // 오류 발생 시 null 반환
        }
    }

 // 쪽지 삭제하기 기능
    public int deleteMessage(int messageID) {
        try {
            return messageDAO.deleteMessage(messageID);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;  // 오류 발생 시 0 반환
        }
    }

    // 수신된 쪽지 목록 가져오기 기능
    public List<MessageDTO> getMessagesForReceiver(String receiverID) throws SQLException {
        return messageDAO.getMessagesForReceiver(receiverID);
    }

    // 특정 쪽지에 대한 쪽지 목록 가져오기 기능
    public List<MessageDTO> getMessagesByMessageID(int messageID) throws SQLException {
        return messageDAO.getMessagesForContent(messageID);
    }

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

   public List<MessageDTO> getMessagesForSender(String senderID) throws SQLException {
	   return messageDAO.getMessagesForSender(senderID);
   }


}
