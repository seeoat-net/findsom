package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.MessageDTO;

public class MessageDAO {
    private JDBCUtil jdbcUtil;

    public MessageDAO() {
        jdbcUtil = new JDBCUtil();
    }

    public void close() {
        jdbcUtil.close();
    }

    // 쪽지 작성
    public void writeMessage(MessageDTO message) throws SQLException {
        String query = "INSERT INTO MessageInfo(messageID, messageText, createAt, recognizeID, senderID, receiverID, postID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Object[] parameters = {message.getMessageID(), message.getMessageText(), message.getCreateAt(), message.getRecognizeID(), message.getSenderID(), message.getReceiverID(), message.getPostID()};
        jdbcUtil.setSqlAndParameters(query, parameters);

        try {
            jdbcUtil.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
    }

    // 쪽지 수정
    public void modifyMessage(MessageDTO message) throws SQLException {
        String query = "UPDATE MessageInfo SET messageText=?, createAt=?, recognizeID=?, senderID=?, receiverID=?, postID=? WHERE messageID=?";
        Object[] parameters = {message.getMessageText(), message.getCreateAt(), message.getRecognizeID(), message.getSenderID(), message.getReceiverID(), message.getPostID(), message.getMessageID()};
        jdbcUtil.setSqlAndParameters(query, parameters);

        try {
            jdbcUtil.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
    }

    // 쪽지 삭제
    public void deleteMessage(String messageID) throws SQLException {
        String query = "DELETE FROM MessageInfo WHERE messageID=?";
        Object[] parameters = {messageID};
        jdbcUtil.setSqlAndParameters(query, parameters);

        try {
            jdbcUtil.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
    }

    // 특정 콘텐츠(게시글)에 대한 쪽지 조회
    public List<MessageDTO> getMessagesForContent(String contentID) throws SQLException {
        String query = "SELECT * FROM MessageInfo WHERE postID=?";
        Object[] parameters = {contentID};
        jdbcUtil.setSqlAndParameters(query, parameters);

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            List<MessageDTO> messages = new ArrayList<>();

            while (rs.next()) {
                MessageDTO message = new MessageDTO(query, query, null, query, query, query, query);
                message.setMessageID(rs.getString("messageID"));
                message.setMessageText(rs.getString("messageText"));
                message.setCreateAt(rs.getDate("createAt"));
                message.setRecognizeID(rs.getString("recognizeID"));
                message.setSenderID(rs.getString("senderID"));
                message.setReceiverID(rs.getString("receiverID"));
                message.setPostID(rs.getString("postID"));

                messages.add(message);
            }

            return messages;
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
    }
}
