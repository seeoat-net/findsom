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
    public MessageDTO writeMessage(MessageDTO message) throws SQLException {
        String query = "INSERT INTO MessageInfo(messageID, messageText, createAt, recognizeID, senderID, receiverID) VALUES (?, ?, ?, ?, ?, ?)";
        Object[] parameters = {message.getMessageID(), message.getMessageText(),
                                message.getCreateAt(), message.getRecognizeID(), 
                                message.getSenderID(), message.getReceiverID(), 
                                };
        jdbcUtil.setSqlAndParameters(query, parameters);
        String key[] = {"messageID"};	// PK 컬럼의 이름
        try {
            jdbcUtil.executeUpdate(key);
            ResultSet rs = jdbcUtil.getGeneratedKeys();
		   	if(rs.next()) {
		   		int generatedKey = rs.getInt(1);   // 생성된 PK 값
		   		message.setMessageID(generatedKey); 	// id필드에 저장  
		   	}
		   	return message;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}
		return null;		
    }
    
    //쪽지 받음
    public List<MessageDTO> getMessagesForReceiver(String receiverID) throws SQLException {
        String query = "SELECT * FROM MessageInfo WHERE receiverID=?";
        Object[] parameters = {receiverID};

        try {
            jdbcUtil.setSqlAndParameters(query, parameters);
            ResultSet rs = jdbcUtil.executeQuery();
            List<MessageDTO> messages = new ArrayList<>();

            while (rs.next()) {
                MessageDTO message = new MessageDTO(
                        rs.getInt("messageID"),
                        rs.getString("messageText"),
                        rs.getDate("createAt"),
                        rs.getString("recognizeID"),
                        rs.getString("senderID"),
                        rs.getString("receiverID")
                );

                messages.add(message);
            }

            return messages;
        } catch (SQLException e) {
            throw e;
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
    }

 // 쪽지 삭제
    public int deleteMessage(int messageID) throws SQLException {
        String query = "DELETE FROM MessageInfo WHERE messageID=?";
        jdbcUtil.setSqlAndParameters(query, new Object[] {messageID});
        //Object[] parameters = {messageID};

        try {
            int result = jdbcUtil.executeUpdate();
            return result;
        } catch (Exception e) {
            jdbcUtil.rollback();
            e.printStackTrace();
        } finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}

    // 특정 콘텐츠(게시글)에 대한 쪽지 조회
    public List<MessageDTO> getMessagesForContent(int messageID) throws SQLException {
        String query = "SELECT * FROM MessageInfo WHERE messageID=?";
        Object[] parameters = {messageID};

        try {
            jdbcUtil.setSqlAndParameters(query, parameters);
            ResultSet rs = jdbcUtil.executeQuery();
            List<MessageDTO> messages = new ArrayList<>();

            while (rs.next()) {
                MessageDTO message = new MessageDTO(
                    rs.getInt("messageID"),
                    rs.getString("messageText"),
                    rs.getDate("createAt"),  
                    rs.getString("recognizeID"),
                    rs.getString("senderID"),
                    rs.getString("receiverID")
                );

                messages.add(message);
            }

            return messages;
        } catch (SQLException e) {
            throw e;
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
    }
}

/*import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
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
    
    // 데이터베이스 연결을 위한 메서드
    private Connection getConnection() throws SQLException {
        return ((Statement) jdbcUtil).getConnection();
    }


    // 쪽지 전송을 위한 메서드
    public void sendMessage(MessageDTO message) {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO MessageInfo VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {

            pstmt.setInt(1, message.getMessageID());
            pstmt.setString(2, message.getMessageText());
            pstmt.setDate(3, new java.sql.Date(message.getCreateAt().getTime()));
            pstmt.setString(4, message.getRecognizeID());
            pstmt.setString(5, message.getSenderID());
            pstmt.setString(6, message.getReceiverID());
            pstmt.setString(7, message.getPostID());
            pstmt.setInt(8, message.getFreepostID());
            pstmt.setInt(9, message.getFindpostID());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // 예외 처리 추가
        }
    }

    // 쪽지 수신을 위한 메서드
    public List<MessageDTO> receiveMessages(String userID) {
        List<MessageDTO> messages = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM MessageInfo WHERE receiverID = ?")) {

            pstmt.setString(1, userID);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int messageID = rs.getInt("messageID");
                    String messageText = rs.getString("messageText");
                    Date createAt = rs.getDate("createAt");
                    String recognizeID = rs.getString("recognizeID");
                    String senderID = rs.getString("senderID");
                    String receiverID = rs.getString("receiverID");
                    String postID = rs.getString("postID");
                    int freepostID = rs.getInt("freepostID");
                    int findpostID = rs.getInt("findpostID");

                    MessageDTO message = new MessageDTO(messageID, messageText, createAt, recognizeID, senderID, receiverID, postID);
                    message.setFreepostID(freepostID);
                    message.setFindpostID(findpostID);

                    messages.add(message);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // 예외 처리 추가
        }

        return messages;
    }
}*/

