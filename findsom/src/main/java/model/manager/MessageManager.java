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
    public void deleteMessage(int messageID) throws SQLException {
        messageDAO.deleteMessage(messageID);
    }
<<<<<<< Updated upstream

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
        	// messageID는 데이터베이스에서 자동으로 생성되므로 여기서는 설정하지 않습니다.
        	MessageDTO newMessage = new MessageDTO(13, "안녕하세요", now, "e", "c", 999, 1000);
=======
>>>>>>> Stashed changes

    // 수신된 쪽지 목록 가져오기 기능
    public List<MessageDTO> getMessagesForReceiver(String receiverID) throws SQLException {
        return messageDAO.getMessagesForReceiver(receiverID);
    }

<<<<<<< Updated upstream
            MessageDTO writtenMessage = messageManager.writeMessage(newMessage);
            System.out.println("쪽지 작성 결과: " + writtenMessage);
            
            //쪽지 조회에서 
            // 수신자 ID를 기반으로 쪽지 목록 조회 테스트 
            String receiverID = "e"; // 수신자 ID
            List<MessageDTO> receivedMessages = messageManager.getMessagesForReceiver(receiverID);
            System.out.println("수신된 쪽지 목록: " + receivedMessages);

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

}
=======
    // 특정 쪽지에 대한 쪽지 목록 가져오기 기능
    public List<MessageDTO> getMessagesByMessageID(int messageID) throws SQLException {
        return messageDAO.getMessagesForContent(messageID);
    }

    //테스트
	public static void main(String[] args) {
    	MessageManager messageManager = MessageManager.getInstance();

        try {
            // 쪽지 작성 테스트 여기서 그걸 홀수 짝수로 나눠줘야하나?
        	LocalDateTime now = LocalDateTime.now();
        	MessageDTO newMessage = new MessageDTO(1,"안녕하세요", now, "e", "c");

            MessageDTO writtenMessage = messageManager.writeMessage(newMessage);
            System.out.println("쪽지 작성 결과: " + writtenMessage);
            
            //쪽지 조회에서 
            // 수신자 ID를 기반으로 쪽지 목록 조회 테스트 
            String receiverID = "e"; // 수신자 ID
            List<MessageDTO> receivedMessages = messageManager.getMessagesForReceiver(receiverID);
            System.out.println("수신된 쪽지 목록: " + receivedMessages);

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

}
>>>>>>> Stashed changes
