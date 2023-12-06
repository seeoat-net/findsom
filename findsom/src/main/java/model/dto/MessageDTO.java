package model.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageDTO {
	// 쪽지 식별자
	private int messageID;

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

	private int freepostID;// 짝수

	private int findpostID;// 홀수
	/*
	 * private String createAtFormatted; // 이거를 꼭 추가해야하나 고민
	 * 
	 * public String getCreateAtFormatted() { 
	 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); return sdf.format(createAt); }
	 * 
	 * public void setCreateAtFormatted(String createAtFormatted) {
	 * this.createAtFormatted = createAtFormatted; }
	 */
	// 생성자
	public MessageDTO(int messageID, String messageText, Date createAt, String recognizeID, String senderID,
			String receiverID) {
		this.messageID = messageID;
		this.messageText = messageText;
		this.createAt = createAt;
		this.recognizeID = recognizeID;
		this.senderID = senderID;
		this.receiverID = receiverID;
		//this.freepostID = freepostID;
		//this.findpostID = findpostID;
		

	}

	public int getMessageID() {
		return messageID;
	}

	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public String getCreateAtFormatted() {
        // SimpleDateFormat을 사용하여 날짜를 원하는 형식으로 포맷팅
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(createAt);
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

	/*
	 * public int getFreepostID() { return freepostID; }
	 * 
	 * public void setFreepostID(int freepostID) { this.freepostID = freepostID; }
	 * 
	 * public int getFindpostID() { return findpostID; }
	 * 
	 * public void setFindpostID(int findpostID) { this.findpostID = findpostID; }
	 */
	
	// toString 메서드
    @Override
    public String toString() {
        return "MessageDTO{" +
                "messageID='" + messageID + '\'' +
                ", messageText='" + messageText + '\'' +
                ", createAt=" + getCreateAtFormatted() +  
                ", recognizeID='" + recognizeID + '\'' +
                ", senderID='" + senderID + '\'' +
                ", receiverID='" + receiverID + '\'' +
                '}';
    }
	 
}