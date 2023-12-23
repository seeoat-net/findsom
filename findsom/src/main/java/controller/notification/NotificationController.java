package controller.notification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.manager.MessageManager;
import model.manager.NotificationManager;
import model.dto.NotificationDTO;

public class NotificationController implements Controller {

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String action = request.getParameter("action");

        if ("pushNotification".equals(action)) {
            return pushNotification(request);
        } else if ("getNotifications".equals(action)) {
            return getNotifications(request);
        } else if ("markNotificationAsChecked".equals(action)) {
            return markNotificationAsChecked(request);
        }
        return "error.jsp";
    }

	private String pushNotification(HttpServletRequest request) {
	    try {
	        NotificationManager notificationManager = NotificationManager.getInstance();

	        NotificationDTO newNotification = new NotificationDTO();
	        newNotification.setReceiverID(request.getParameter("receiverID"));
	        newNotification.setSenderID(request.getParameter("senderID"));
	        newNotification.setCommentID(parseParameterAsInt(request, "commentID"));
	        newNotification.setMessageID(parseParameterAsInt(request, "messageID"));
	        newNotification.setPostID(parseParameterAsInt(request, "postID"));
	        newNotification.setNotiType(request.getParameter("notiType"));
	        newNotification.setNotiTypeID(request.getParameter("notiTypeID"));

	        notificationManager.pushNotification(newNotification);

	        notificationManager.close();
	        return "/notification/list.jsp";  
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "error.jsp";  
	    }
	}

	private int parseParameterAsInt(HttpServletRequest request, String paramName) {
	    try {
	        return Integer.parseInt(request.getParameter(paramName));
	    } catch (NumberFormatException e) {
	        return 0; 
	    }
	}


	//알림함 조회
    private String getNotifications(HttpServletRequest request) {
        try {
            NotificationManager notificationManager = NotificationManager.getInstance();
            String userID = request.getParameter("userID");
            request.setAttribute("userNotifications", notificationManager.getNotifications(userID));
            notificationManager.close();
            return "PostListView.jsp";  
        } catch (Exception e) {
            e.printStackTrace();
            return "error.jsp";  
        }
    }
    

    private String markNotificationAsChecked(HttpServletRequest request) {
        try {
            NotificationManager notificationManager = NotificationManager.getInstance();
            int notificationID = Integer.parseInt(request.getParameter("notificationID"));
            String receiverID = request.getParameter("receiverID");

            // 알림 종류 확인
            String notiType = notificationManager.getNotificationType(notificationID);

            // 알림 상태 변경
            notificationManager.markNotificationAsChecked(notificationID, receiverID);

            notificationManager.close();

            // 페이지 이동 결정
            if ("comment".equals(notiType)) {
                return "CommPostListView.jsp";
            } else if ("message".equals(notiType)) {
                return "PostMessageMainView.jsp";
            } else {
                return "error.jsp";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "error.jsp"; 
        }
    }

}
