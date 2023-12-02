package model;

/***  FindBoardPost 테이블과 대응됨 */
public class FindDTO {
	private String postID;
	private String isAnonymous;
	private String title;
	private String prefer;
	private String userID;
	private String content;
	
	public FindDTO() {}
	
	public FindDTO(String postID, String isAnonymous, String title, String prefer, String userID, String content) {
		super();
		this.postID = postID;
		this.isAnonymous = isAnonymous;
		this.title = title;
		this.prefer = prefer;
		this.userID = userID;
		this.content = content;
	}
	
	public String getPostID() {
		return postID;
	}
	public void setPostId(String postID) {
		this.postID = postID;
	}
	public String getIsAnonymous() {
		return isAnonymous;
	}
	public void setIsAnonymous(String isAnonymous) {
		this.isAnonymous = isAnonymous;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrefer() {
		return prefer;
	}
	public void setPrefer(String prefer) {
		this.prefer = prefer;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "FindPost [postID=" + postID + ", isAnonymous=" + isAnonymous + ", prefer=" + prefer + ", userID="
				+ userID + ", content=" + content + "]";
	}
	
}
