package model.dto;

public class PostDTO {
	private String boardType;
	private int postId;
	private String title;
	private String contents;
	
	public PostDTO (String boardType, int postId, String title, String contents) {
		this.boardType = boardType;
		this.postId = postId;
		this.title = title;
		this.contents = contents;
	}
	public String getBoardType() {
		return boardType;
	}
	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
}
