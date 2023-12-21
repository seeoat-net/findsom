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

<<<<<<< Updated upstream
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
=======

    public MessageDTO(int messageID, String messageText, LocalDateTime createAt, String senderID, String receiverID, Integer freepostID, Integer findpostID) {
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
>>>>>>> Stashed changes

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

<<<<<<< Updated upstream
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
=======
>>>>>>> Stashed changes

	public String getRecognizeID() {
		return recognizeID;
	}

<<<<<<< Updated upstream
	public void setRecognizeID(String recognizeID) {
		this.recognizeID = recognizeID;
	}

	public String getSenderID() {
		return senderID;
	}
=======
       if (freepostID % 2 != 0) {
            throw new IllegalArgumentException("FreepostID must be even");
        }
        this.freepostID = freepostID;
    }

   public int getFindpostID() { return findpostID; }
    public void setFindpostID(int findpostID) {

        if (findpostID % 2 == 0) {
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
>>>>>>> Stashed changes

   public void setSenderID(String senderID) {
      this.senderID = senderID;
   }

   public String getReceiverID() {
      return receiverID;
   }

<<<<<<< Updated upstream
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
=======
   public void setReceiverID(String receiverID) {
      this.receiverID = receiverID;
   }
   
   // toString 메서드
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
	 
}
=======

}
>>>>>>> Stashed changes
