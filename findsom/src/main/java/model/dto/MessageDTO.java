
package model.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageDTO {
    // 쪽지 식별자
    private int messageID;

    // 쪽지 내용
    private String messageText;

    // 쪽지 작성일
    private LocalDateTime createAt;

    // 쪽지를 보낸 사용자의 식별자
    private String senderID;

    // 쪽지를 받은 사용자의 식별자
    private String receiverID;

<<<<<<< Updated upstream
    private Integer freepostID; // 짝수
    private Integer findpostID; // 홀수, 매칭은 null

    public MessageDTO(int messageID, String messageText, LocalDateTime createAt, String senderID, String receiverID, Integer freepostID, Integer findpostID) {
        this.messageID = messageID;
        this.messageText = messageText;
        this.createAt = createAt;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.freepostID = freepostID;
        this.findpostID = findpostID;
    }
    
	/*
	 * //freepostID가 있는 DTO public void MessageFreeDTO(int messageID, String
	 * messageText, LocalDateTime createAt, String senderID, String receiverID, int
	 * freepostID) { this.messageID = messageID; this.messageText = messageText;
	 * this.createAt = createAt; this.senderID = senderID; this.receiverID =
	 * receiverID; this.freepostID = freepostID; } //findpostID가 있는 DTO public void
	 * MessageFindDTO(int messageID, String messageText, LocalDateTime createAt,
	 * String senderID, String receiverID, int findpostID) { this.messageID =
	 * messageID; this.messageText = messageText; this.createAt = createAt;
	 * this.senderID = senderID; this.receiverID = receiverID; this.findpostID =
	 * findpostID; }
	 */
=======
    private int freepostID; // 짝수
    private int findpostID; // 홀수, 매칭은 null
>>>>>>> Stashed changes

    public MessageDTO(int messageID, String messageText, LocalDateTime createAt, String senderID, String receiverID, int freepostID, int findpostID) {
        this.messageID = messageID;
        this.messageText = messageText;
        this.createAt = createAt;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.freepostID = freepostID;
        this.findpostID = findpostID;
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
        if (messageText == null || messageText.trim().isEmpty()) {
            throw new IllegalArgumentException("empty");
        }
        if (messageText.length() > 600) { // 데이터베이스 제약조건에 따라 길이 제한
            throw new IllegalArgumentException("Message text is too long");
        }
        this.messageText = messageText;
    }

<<<<<<< Updated upstream
    public Integer getFreepostID() { return freepostID; }
    public void setFreepostID(Integer freepostID) {
=======
    public int getFreepostID() { return freepostID; }
    public void setFreepostID(int freepostID) {
>>>>>>> Stashed changes
        if (freepostID % 2 == 0) {
            throw new IllegalArgumentException("FreepostID must be even");
        }
        this.freepostID = freepostID;
    }

<<<<<<< Updated upstream
	public Integer getFindpostID() { return findpostID; }
    public void setFindpostID(Integer findpostID) {
=======
	public int getFindpostID() { return findpostID; }
    public void setFindpostID(int findpostID) {
>>>>>>> Stashed changes
        if (findpostID % 2 != 0) {
            throw new IllegalArgumentException("FindpostID must be odd");
        }
        this.findpostID = findpostID;
    }
    public LocalDateTime getCreateAt() {
        return createAt;
    }

 // createAt 필드에 대한 세터
    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    // 날짜/시각 값을 문자열로 변환하여 반환하는 메서드
    public String getCreateAtFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return createAt.format(formatter);
    }

    // 문자열로부터 날짜/시각 값을 파싱하여 createAt 필드에 설정하는 메서드
    public void setCreateAtFromString(String createAtStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.createAt = LocalDateTime.parse(createAtStr, formatter);
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
	
	// toString 메서드
    @Override
    public String toString() {
        return "MessageDTO{" +
                "messageID='" + messageID + '\'' +
                ", messageText='" + messageText + '\'' +
                ", createAt=" + getCreateAtFormatted() +  
                ", senderID='" + senderID + '\'' +
                ", receiverID='" + receiverID + '\'' +
                '}';
    }
<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
