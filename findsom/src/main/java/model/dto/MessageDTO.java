package model.dto;

import java.util.Date;

public class MessageDTO {
    // 쪽지 식별자
    private String messageID;

    // 쪽지 내용
    private String messageText;

    // 쪽지 작성일
    private Date createAt;

    // 쪽지를 인식하는 식별자
    private String recognizeID;

    // 쪽지를 보낸 사용자의 식별자
    private String senderID;

    // 쪽지를 받은 사용자의 식별자
    private String receiverID;

    // 쪽지가 속한 콘텐츠(게시글) 식별자
    private String postID;

    // 생성자
    public MessageDTO(String messageID, String messageText, Date createAt, String recognizeID, String senderID, String receiverID, String postID) {
        this.messageID = messageID;
        this.messageText = messageText;
        this.createAt = createAt;
        this.recognizeID = recognizeID;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.postID = postID;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getRecognizeID() {
        return recognizeID;
    }

    public void setRecognizeID(String recognizeID) {
        this.recognizeID = recognizeID;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    // toString 메서드
    @Override
    public String toString() {
        return "MessageDTO{" +
                "messageID='" + messageID + '\'' +
                ", messageText='" + messageText + '\'' +
                ", createAt=" + createAt +
                ", recognizeID='" + recognizeID + '\'' +
                ", senderID='" + senderID + '\'' +
                ", receiverID='" + receiverID + '\'' +
                ", postID='" + postID + '\'' +
                '}';
    }
}