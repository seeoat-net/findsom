package model.dto;

public class NotificationDTO {
    private int notificationID;
    private String userID; //알람을 받을 유저
    private String writerID; //알람을 발생시킨 유저
    private int postID; //게시글의 idx
    private String notiType; //알람 종류
    private String notiTypeID; //알람을 발생시킨 댓글, 대댓글의 idx
    
    public int getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
    }

    public String getUserID() {
        return userID;
    }

    public void setuserID(String userID) {
        this.userID = userID;
    }

    public String getWriterID() {
        return writerID;
    }

    public void setWriterID(String writerID) {
        this.writerID = writerID;
    }

    public int getPostID() {//짝수인지 홀수인지 구분 (findpost=홀수 freepost=짝수 sequence)
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getNotiType() {
        return notiType;
    }

    public void setNotiType(String notiType) {
        this.notiType = notiType;
    }

    public String getNotiTypeID() {
        return notiTypeID;
    }

    public void setNotiTypeID(String notiTypeID) {
        this.notiTypeID = notiTypeID;
    }
}
