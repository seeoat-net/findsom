package model;

/***  FreeBoardPost 테이블과 대응됨 */
public class FreeDTO {
	private int freepostID;
	private String title;
	private String userID;
	private String isAnonymous;
	private String content;
	private String category;
	
	public FreeDTO() {}
	
	public FreeDTO(int freepostID, String title, String userID, String isAnonymous, String content, String category) {
		super();
		this.freepostID = freepostID;
		this.title = title;
		this.userID = userID;
		this.isAnonymous = isAnonymous;
		this.content = content;
		this.category = category;
	}

	public int getFreepostID() {
		return freepostID;
	}

	public void setFreepostID(int freepostID) {
		this.freepostID = freepostID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getIsAnonymous() {
		return isAnonymous;
	}

	public void setIsAnonymous(String isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "FreeDTO [freepostID=" + freepostID + ", title=" + title + ", userID=" + userID + ", isAnonymous="
				+ isAnonymous + ", content=" + content + ", category=" + category + "]";
	}
		
	
}
