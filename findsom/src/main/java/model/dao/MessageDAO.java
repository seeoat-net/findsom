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
<<<<<<< Updated upstream

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
=======
   /*
    * public MessageDTO writeMessage(MessageDTO message) throws SQLException { //
    * 시퀀스를 사용하여 messageID를 자동으로 생성하는 INSERT 쿼리문 String insertQuery =
    * "INSERT INTO MessageInfo (MESSAGEID, MESSAGETEXT, CREATEAT, SENDERID, RECEIVERID, FREEPOSTID, FINDPOSTID) "
    * + "VALUES (Sequence_messageID.NEXTVAL, ?, ?, ?, ?, ?, ?)";
    * 
    * // LocalDateTime을 java.sql.Timestamp로 변환 java.sql.Timestamp sqlTimestamp =
    * java.sql.Timestamp.valueOf(message.getCreateAt());
    * 
    * // 파라미터 설정 Object[] parameters = new Object[6]; parameters[0] =
    * message.getMessageText(); parameters[1] = sqlTimestamp; parameters[2] =
    * message.getSenderID(); parameters[3] = message.getReceiverID(); parameters[4]
    * = (message.getFreepostID() != null && message.getFreepostID() % 2 == 0) ?
    * message.getFreepostID() : null; // findpostID가 null이 아니고 홀수인 경우, findpostID를
    * 사용 parameters[5] = (message.getFindpostID() != null &&
    * message.getFindpostID() % 2 != 0) ? message.getFindpostID() : null;
    */


    public MessageDTO writeMessage(MessageDTO message) throws SQLException {
       String insertQuery = "INSERT INTO MessageInfo (messageID, messageText, createAt, senderID, receiverID, freepostID, findpostID) " +
                "VALUES (Sequence_messageID.NEXTVAL, ?, ?, ?, ?, ?, ?)";//findpostID값에 null 넣기

        // LocalDateTime을 java.sql.Timestamp로 변환
        java.sql.Timestamp sqlTimestamp = java.sql.Timestamp.valueOf(message.getCreateAt());
        
        Object[] parameters = {
            message.getMessageText(),
            sqlTimestamp, // java.sql.Timestamp 타입으로 변환된 날짜/시간
            message.getSenderID(),
            message.getReceiverID(),
            message.getFreepostID() % 2 == 0 ? message.getFreepostID() : null, // 짝수인 경우만 설정
            message.getFindpostID() % 2 != 0 ? message.getFindpostID() : null // 홀수인 경우만 설정
            //message.getFreepostID(),
            //message.getFindpostID()
        };


        // JDBCUtil에 insert문과 parameter 배열 설정
        jdbcUtil.setSqlAndParameters(insertQuery, parameters);

        String key[] = {"messageID"}; // PK 컬럼의 이름 배열

        try {
            jdbcUtil.executeUpdate(key); // insert 문 실행
            ResultSet rs = jdbcUtil.getGeneratedKeys(); // 생성된 PK 값을 포함한 ResultSet 객체 반환

            if (rs.next()) {
                int generatedKey = rs.getInt(1); // 생성된 PK 컬럼 값 읽음
                message.setMessageID(generatedKey); // PK 값을 MessageDTO 객체에 설정
            }
            return message;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
            return null;
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
                    rs.getInt("messageID"),
                    rs.getString("messageText"),
                    rs.getDate("createAt"),  
                    rs.getString("recognizeID"),
                    rs.getString("senderID"),
                    rs.getString("receiverID")
=======
                        rs.getInt("messageID"),
                        rs.getString("messageText"),

                        rs.getTimestamp("createAt").toLocalDateTime(), // Timestamp를 LocalDateTime으로 변환
                        rs.getString("senderID"),
                        rs.getString("receiverID"),
                        rs.getInt("freepostID"),
                        rs.getInt("findpostID")
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
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

=======

}
>>>>>>> Stashed changes
