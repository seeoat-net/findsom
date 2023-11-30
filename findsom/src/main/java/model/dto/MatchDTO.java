package model.dto;

import java.util.ArrayList;

//match시, 필요한 기본 정보
public class MatchDTO {
	
	private String userID;
	private String nickname;
	private ArrayList<String> lifePatternList;
	
	public MatchDTO(String userID, String nickname) {
		this.userID = userID;
		this.nickname = nickname;
	}
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public ArrayList<String> getLifePatternList() {
		return lifePatternList;
	}
	public void setLifePatternList(ArrayList<String> lifePatternList) {
		this.lifePatternList = lifePatternList;
	}
}
