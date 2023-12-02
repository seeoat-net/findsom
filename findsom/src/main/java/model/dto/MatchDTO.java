package model.dto;

import java.util.ArrayList;

//match시, 필요한 기본 정보
public class MatchDTO implements Comparable<MatchDTO> {
	
	private String userID;
	private String nickname;
	private ArrayList<String> lifePatternList;
	private int cnt;
	
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
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public void plusCnt() {
		this.cnt += 1;
	}
	
	
	@Override
	public String toString() {
		String result = userID + ", " + nickname + ", ";
		for (String s : lifePatternList) {
			result = result + s + "/";
		}
		result = result + Integer.toString(cnt);
		return result;
	}

	@Override
	public int compareTo(MatchDTO o) {
		return o.getCnt() - this.cnt;
	}
}
