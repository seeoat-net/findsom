package model.manager;

import java.sql.SQLException;
import java.util.List;
import model.dao.NotificationDAO;
import model.dto.NotificationDTO;

public class NotificationManager {
    private static NotificationManager notificationManager = new NotificationManager();
    private NotificationDAO notificationDAO;

    private NotificationManager() {
        notificationDAO = new NotificationDAO();
    }

    public static NotificationManager getInstance() {
        return notificationManager;
    }

    public void close() {
        notificationDAO.close();
    }

    public void pushNotification(NotificationDTO notification) {
        try {
            notificationDAO.pushNotificationDB(notification);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<NotificationDTO> getNotifications(String userID) {
        List<NotificationDTO> notifications = null;
        try {
            notifications = notificationDAO.getNotifications(userID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

    /*
    public int getNumberOfNotifications(String receiverID) {
        try {
            return notificationDAO.getNumberOfNotifications(receiverID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    */
    
    public String getNotificationType(int notificationID) {
        try {
            return notificationDAO.getNotificationType(notificationID);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void markNotificationAsChecked(int notificationID, String receiverID) {
        try {
            notificationDAO.markNotificationAsChecked(notificationID, receiverID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
