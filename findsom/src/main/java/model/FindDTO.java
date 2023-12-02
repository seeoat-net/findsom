package model;

/***  FindBoardPost 테이블과 대응됨 */
public class FindDTO {
	private int findpostID;
	private String isAnonymous;
	private String title;
	private String prefer;
	private String mycontent;
	private String matecontent;
	private String userID;
	
	public FindDTO() {}
	
	public FindDTO(int findpostID, String isAnonymous, String title, String prefer, String mycontent, String matecontent, String userID) {
		super();
		this.findpostID = findpostID;
		this.isAnonymous = isAnonymous;
		this.title = title;
		this.prefer = prefer;
		this.mycontent = mycontent;
		this.matecontent = matecontent;
		this.userID = userID;
	}

	public int getFindpostID() {
		return findpostID;
	}

	public void setFindpostID(int findpostID) {
		this.findpostID = findpostID;
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

	public String getMycontent() {
		return mycontent;
	}

	public void setMycontent(String mycontent) {
		this.mycontent = mycontent;
	}

	public String getMatecontent() {
		return matecontent;
	}

	public void setMatecontent(String matecontent) {
		this.matecontent = matecontent;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	@Override
	public String toString() {
		return "FindDTO [findpostID=" + findpostID + ", isAnonymous=" + isAnonymous + ", title=" + title + ", prefer="
				+ prefer + ", mycontent=" + mycontent + ", matecontent=" + matecontent + ", userID=" + userID + "]";
	}
	
}
