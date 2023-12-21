package model.dto;

public class NotificationDTO {
    private int notificationID;
    private String receiverID; //알람을 받을 유저
    private String senderID; //알람을 발생시킨 유저
    private int commentID; //댓글 idx
    private int messageID;//쪽지 idx
    private int postID; //게시글의 idx 짝수면 free, 홀수면 find
    private String notiType; //알람 종류
    private String notiTypeID; //알람을 발생시킨 댓글, 대댓글의 idx
    private String isChecked;//유저가 알람 확인 여부 ( '0' or '1')
    
    public int getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }
    
    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
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
    
    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }
}
