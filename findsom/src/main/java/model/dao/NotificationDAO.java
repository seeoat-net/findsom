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
    public void pushNotificationDB(NotificationDTO notification) throws Exception {
        String query = "INSERT INTO notification(userID, writerID, postID, notiType, notiTypeID) VALUES (?, ?, ?, ?, ?)";
        Object[] parameters = {notification.getUserID(), notification.getWriterID(), notification.getPostID(), notification.getNotiType(), notification.getNotiTypeID()};
        jdbcUtil.setSqlAndParameters(query, parameters);

        try {
            jdbcUtil.executeUpdate();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
    }

    public List<NotificationDTO> getNotifications(NotificationDTO notification) throws SQLException {
        // postID가 홀수인지 짝수인지 확인
        boolean isOddPostID = notification.getPostID() % 2 != 0;

        // postID에 따라 어떤 테이블에 조인할지 결정
        String joinTable = isOddPostID ? "FindBoardPost" : "FreeBoardPost";

        String query = "SELECT NT.notificationID, INFO.nickname, FB.title, FB.userID AS content_idx, CMT.content, "
                + "CMT.response_to AS commentID FROM notification AS NT "
                + "INNER JOIN UserInfo AS INFO ON NT.writer_idx=INFO.userID "
                + "INNER JOIN " + joinTable + " AS FB ON NT.content_idx = FB.idx "
                + "INNER JOIN comment AS CMT ON NT.notiTypeID = CMT.userID WHERE NT.userID=? ORDER BY NT.notificationID DESC";
                
        Object[] parameters = {notification.getUserID()};
        jdbcUtil.setSqlAndParameters(query, parameters);

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            List<NotificationDTO> notifications = new ArrayList<>();

            while (rs.next()) {
                // ResultSet에서 데이터를 읽어와서 NotificationDTO 객체를 생성
                int idx = rs.getInt("notificationID");
                String nickname = rs.getString("nickname");                
                int contentIdx = rs.getInt("content_idx");
                String comment = rs.getString("content");
                int commentIdx = rs.getInt("commentID");

                // NotificationDTO 객체 생성
                NotificationDTO notificationItem = new NotificationDTO();
                notificationItem.setNotIdx(String.valueOf(idx));
                notificationItem.setNickname(nickname);                             
                notificationItem.setContentIdx(String.valueOf(contentIdx));
                notificationItem.setComment(comment);
                notificationItem.setCommentIdx(String.valueOf(commentIdx));

                // 리스트에 추가
                notifications.add(notificationItem);
            }

            return notifications;
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
    }


    // 유저가 확인하지 않은 알람 개수 조회
    public int getNumberOfNotifications(NotificationDTO notification) throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM notification WHERE user_idx=? AND isChecked='1'";
        //COUNT(*) AS count: 확인하지 않은 알림의 개수를 셈, isChecked='1': 확인되지 않은 알림을 필터링하는 조건
        Object[] parameters = {notification.getUserIdx()};
        jdbcUtil.setSqlAndParameters(query, parameters);

        try {
            ResultSet rs = jdbcUtil.executeQuery();

            if (rs.next()) {
                return rs.getInt("count");
            }

            return 0;
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
    }

    // 유저가 알람 확인했을 때 isChecked를 0으로 변경
    public void markNotificationAsChecked(NotificationDTO notification) throws Exception {
        String query = "UPDATE notification SET isChecked='0' WHERE user_idx=? AND idx=?";//알림이 속한 사용자의 인덱스가 주어진 매개변수 값과 일치하는 경우, 알림의 인덱스가 주어진 매개변수 값과 일치하는 경우
        Object[] parameters = {notification.getUserIdx(), notification.getNotIdx()};
        jdbcUtil.setSqlAndParameters(query, parameters);

        try {
            jdbcUtil.executeUpdate();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
    }

}
