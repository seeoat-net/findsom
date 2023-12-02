package controller.notification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
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
        } else if ("getUncheckedNotificationCount".equals(action)) {
            return getUncheckedNotificationCount(request);
        } else if ("markNotificationAsChecked".equals(action)) {
            return markNotificationAsChecked(request);
        }
		return "error.jsp";

 
    }

    //새 알림 투가
    private String pushNotification(HttpServletRequest request) {
        try {
            NotificationManager notificationManager = NotificationManager.getInstance();

            NotificationDTO newNotification = new NotificationDTO();
            newNotification.setUserIdx(request.getParameter("userIdx"));
            newNotification.setWriterIdx(request.getParameter("writerIdx"));
            newNotification.setContentIdx(request.getParameter("contentIdx"));
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

    //알림함 조회
    private String getNotifications(HttpServletRequest request) {
        try {
            NotificationManager notificationManager = NotificationManager.getInstance();

            NotificationDTO userNotification = new NotificationDTO();
            userNotification.setUserIdx(request.getParameter("userIdx"));

           
            request.setAttribute("userNotifications", notificationManager.getNotifications(userNotification));

            notificationManager.close();
            return "PostListView.jsp";  
        } catch (Exception e) {
            e.printStackTrace();
            return "error.jsp";  
        }
    }

    //이게 그 알림함 마크 옆에 알람 얼마나 쌓였는지 알려주는건데 이걸 하겠다고 했나요 안하겠다고 했나요?
    private String getUncheckedNotificationCount(HttpServletRequest request) {
        try {
            NotificationManager notificationManager = NotificationManager.getInstance();

            NotificationDTO userNotification = new NotificationDTO();
            userNotification.setUserIdx(request.getParameter("userIdx"));

            int uncheckedCount = notificationManager.getNumberOfNotifications(userNotification);

            request.setAttribute("uncheckedCount", uncheckedCount);

            notificationManager.close();
            return "PostListView.jsp";  
        } catch (Exception e) {
            e.printStackTrace();
            return "error.jsp";  
        }
    }
    
    //A 사용자의 알림을 확인하는건데 게시물인지 댓글인지를 구분해서 해당 페이지로 이동해야하는데 아직 구현하지 못함 
    //지금은 해당 알림이 누구의 알림인지만 구분하도록 되어 있음
    private String markNotificationAsChecked(HttpServletRequest request) {
        try {
            NotificationManager notificationManager = NotificationManager.getInstance();

            NotificationDTO notificationToCheck = new NotificationDTO();
            notificationToCheck.setUserIdx(request.getParameter("userIdx"));
            notificationToCheck.setNotIdx(request.getParameter("notIdx"));

            notificationManager.markNotificationAsChecked(notificationToCheck);

            notificationManager.close();
            return "A.jsp"; 
        } catch (Exception e) {
            e.printStackTrace();
            return "error.jsp"; 
        }
    }

}
