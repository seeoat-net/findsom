package model.dao;

import model.dto.MessageDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageDAO {
    private JDBCUtil jdbcUtil;
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public MessageDAO() {
        jdbcUtil = new JDBCUtil();
    }

    public void close() {
        jdbcUtil.close();
    }
<<<<<<< Updated upstream
<<<<<<< Updated upstream
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


>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
        // 파라미터 설정
        Object[] parameters = new Object[6];
        parameters[0] = message.getMessageText();
        parameters[1] = sqlTimestamp;
        parameters[2] = message.getSenderID();
        parameters[3] = message.getReceiverID();
        parameters[4] = (message.getFreepostID() != null && message.getFreepostID() % 2 == 0) ? message.getFreepostID() : null;
        // findpostID가 null이 아니고 홀수인 경우, findpostID를 사용
        parameters[5] = (message.getFindpostID() != null && message.getFindpostID() % 2 != 0) ? message.getFindpostID() : null;
=======

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
            message.getFreepostID(),
            message.getFindpostID()
        };
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes

        // JDBCUtil에 insert문과 parameter 배열 설정
        jdbcUtil.setSqlAndParameters(insertQuery, parameters);

<<<<<<< Updated upstream
<<<<<<< Updated upstream
        // INSERT 쿼리를 실행하고 생성된 키(여기서는 messageID)를 반환 받습니다.
        try {
            int affectedRows = jdbcUtil.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating message failed, no rows affected.");
            }
=======
        String key[] = {"messageID"}; // PK 컬럼의 이름 배열
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
            jdbcUtil.close(); // 리소스를 반환합니다.
=======
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
>>>>>>> Stashed changes
=======
            jdbcUtil.commit();
            jdbcUtil.close();
>>>>>>> Stashed changes
        }
    }

    // 수신된 쪽지 가져오기 기능 구현 
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
                        LocalDateTime.parse(rs.getString("createAt"), formatter), // 문자열을 LocalDateTime으로 파싱
                        rs.getString("senderID"),
                        rs.getString("receiverID"),
                        rs.getInt("freepostID"),
                        rs.getInt("findpostID")
                );
                messages.add(message);
            }
            return messages;
        } finally {
            jdbcUtil.close(); // 리소스 반환
        }
    }
    
    // 쪽지 삭제
    public int deleteMessage(int messageID) throws SQLException {
        String query = "DELETE FROM MessageInfo WHERE messageID=?";
        jdbcUtil.setSqlAndParameters(query, new Object[]{messageID});

        try {
            int result = jdbcUtil.executeUpdate();
            return result;
        } catch (Exception e) {
            jdbcUtil.rollback();
            e.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();    // resource 반환
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
<<<<<<< Updated upstream
<<<<<<< Updated upstream
                        rs.getTimestamp("createAt").toLocalDateTime(), // Timestamp를 LocalDateTime으로 변환
                        rs.getString("senderID"),
                        rs.getString("receiverID"),
                        rs.getObject("freepostID") != null ? rs.getInt("freepostID") : null, // null 가능성 처리
                        rs.getObject("findpostID") != null ? rs.getInt("findpostID") : null
=======
                        LocalDateTime.parse(rs.getString("createAt"), formatter), // 문자열을 LocalDateTime으로 파싱
                        rs.getString("senderID"),
                        rs.getString("receiverID"),
                        rs.getInt("freepostID"),
                        rs.getInt("findpostID")
>>>>>>> Stashed changes
=======

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
        } finally {
            jdbcUtil.close(); // 리소스 반환
        }
    }
<<<<<<< Updated upstream
<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
=======

}

>>>>>>> Stashed changes
