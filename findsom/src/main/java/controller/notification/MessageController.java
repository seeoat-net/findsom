package controller.notification;

import java.sql.SQLException;
import java.util.List;

import model.dao.MessageDAO;
import model.dto.MessageDTO;

public class MessageController {
    private MessageDAO messageDAO;

    public MessageController() {
        messageDAO = new MessageDAO();
    }

    public void close() {
        messageDAO.close();
    }

    // 쪽지 작성
    public void writeMessage(MessageDTO message) {
        try {
            messageDAO.writeMessage(message);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 쪽지 수정
    public void modifyMessage(MessageDTO message) {
        try {
            messageDAO.modifyMessage(message);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 쪽지 삭제
    public void deleteMessage(String messageID) {
        try {
            messageDAO.deleteMessage(messageID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 특정 콘텐츠(게시글)에 대한 쪽지 조회
    public List<MessageDTO> getMessagesForContent(String contentID) {
        List<MessageDTO> messages = null;
        try {
            messages = messageDAO.getMessagesForContent(contentID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }
}
