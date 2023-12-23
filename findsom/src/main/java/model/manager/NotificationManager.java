package model.manager;

import java.sql.SQLException;
import java.util.List;
import model.dao.NotificationDAO;
import model.dto.NotificationDTO;

public class NotificationManager {
    private static NotificationManager notificationManager = new NotificationManager();
    private NotificationDAO notificationDAO;

    private NotificationManager() {
        try {
            notificationDAO = new NotificationDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static NotificationManager getInstance() {
        return notificationManager;
    }

    public void close() {
        notificationDAO.close();
    }

    public void pushNotification(NotificationDTO notification) throws Exception {
    	notificationDAO.pushNotificationDB(notification);

    }

    public List<NotificationDTO> getNotifications(NotificationDTO notification) {
        List<NotificationDTO> notifications = null;
        try {
            notifications = notificationDAO.getNotifications(notification);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

    public int getNumberOfNotifications(NotificationDTO notification) {
        try {
            return notificationDAO.getNumberOfNotifications(notification);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void markNotificationAsChecked(NotificationDTO notification) {
        try {
            notificationDAO.markNotificationAsChecked(notification);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
