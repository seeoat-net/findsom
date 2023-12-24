package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;  
import java.util.ArrayList;
import model.dto.NotificationDTO;

public class NotificationDAO {
    private JDBCUtil jdbcUtil;

    public NotificationDAO() {
        jdbcUtil = new JDBCUtil();
    }

    public void close() {
        jdbcUtil.close();
    }



    // 알람 DB에 추가
    //쪽지도 조인해야 함
    public void pushNotificationDB(NotificationDTO notiInfo) throws Exception {
        String query = "INSERT INTO notiInfo (notificationID, receiverID, senderID, commentID, messageID, postID, notiType, notiTypeID, isChecked) "
        		+ "VALUES (Sequence_notificationID.NEXTVAL,?, ?, ?, ?, ?, ?, ?, ?)";

        Object[] parameters = {
            notiInfo.getReceiverID(), 
            notiInfo.getSenderID(), 
            notiInfo.getCommentID() != 0 ? notiInfo.getCommentID() : null,
            notiInfo.getMessageID() != 0 ? notiInfo.getMessageID() : null,
            notiInfo.getPostID() != 0 ? notiInfo.getPostID() : null,
            notiInfo.getNotiType(), 
            notiInfo.getNotiTypeID(),
            notiInfo.getIsChecked() // 이 값은 '0' 또는 '1'로 설정, 새 알림은 기본적으로 "확인되지 않음" 상태
        };

        jdbcUtil.setSqlAndParameters(query, parameters);
        int result = -1;
        try {
            result = jdbcUtil.executeUpdate();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
        System.out.println("pushNotificationDB : " + result);
    }

    // 사용자의 알림 목록 가져오기
    public List<NotificationDTO> getNotifications(String userID) throws SQLException {
        String query = "SELECT * FROM notiInfo WHERE receiverID = ? ORDER BY notificationID DESC";
        jdbcUtil.setSqlAndParameters(query, new Object[]{userID});

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            List<NotificationDTO> notifications = new ArrayList<>();

            while (rs.next()) {
                NotificationDTO notification = new NotificationDTO();
                // ResultSet에서 데이터 추출 및 NotificationDTO 객체 설정
                notification.setNotificationID(rs.getInt("notificationID"));
                notification.setReceiverID(rs.getString("receiverID"));
                notification.setSenderID(rs.getString("senderID"));
                notification.setCommentID(rs.getInt("commentID"));
                notification.setMessageID(rs.getInt("messageID"));
                notification.setPostID(rs.getInt("postID"));
                notification.setNotiType(rs.getString("notiType"));
                notification.setNotiTypeID(rs.getString("notiTypeID"));
                notification.setIsChecked(rs.getString("isChecked"));
                
                notifications.add(notification);
            }

            return notifications;
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
    }
    public String getNotificationType(int notificationID) throws SQLException {
        String query = "SELECT notiType FROM notiInfo WHERE notificationID = ?";
        jdbcUtil.setSqlAndParameters(query, new Object[]{notificationID});

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            if (rs.next()) {
                return rs.getString("notiType");
            }
            return null;
        } finally {
            jdbcUtil.close();
        }
    }

	/*
	 * // 확인하지 않은 알림의 개수 조회 public int getNumberOfNotifications(String receiverID)
	 * throws SQLException { String query =
	 * "SELECT COUNT(*) AS count FROM notiInfo WHERE receiverID = ? AND isChecked = '1'"
	 * ; jdbcUtil.setSqlAndParameters(query, new Object[]{receiverID});
	 * 
	 * try { ResultSet rs = jdbcUtil.executeQuery(); if (rs.next()) { return
	 * rs.getInt("count"); } return 0; } finally { jdbcUtil.commit();
	 * jdbcUtil.close(); } }
	 */

    // 알림 확인 상태 변경
    public void markNotificationAsChecked(int notificationID, String receiverID) throws Exception {
        String query = "UPDATE notiInfo SET isChecked = '0' WHERE notificationID = ? AND receiverID = ?";
        jdbcUtil.setSqlAndParameters(query, new Object[]{notificationID, receiverID});

        try {
            jdbcUtil.executeUpdate();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
    }


}
