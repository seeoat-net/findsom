package model.dto;

import java.util.ArrayList;

import model.FindDTO;

//match 상세에서 볼 수 있는 정보에 대한 DTO
//MatchDTO + 게시글
public class MatchDetailDTO {

	private String userID;
	private String nickname;
	private ArrayList<String> lifePatternList;
	private ArrayList<FindDTO> finds;
	
	public MatchDetailDTO(String userID, ArrayList<String> patterns, ArrayList<FindDTO> finds) {
		this.userID = userID;
		this.lifePatternList = patterns;
		this.finds = finds;
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
	public ArrayList<FindDTO> getFinds() {
		return finds;
	}
	public void setFinds(ArrayList<FindDTO> finds) {
		this.finds = finds;
	}
	
	@Override
	public String toString() {
		String result = userID + ", " + nickname + ", ";
		for (String s : lifePatternList) {
			result = result + s + "/";
		}
		result = result + "\n";
		for (FindDTO f : finds) {
			result = result + "\n" + f.toString();
		}
		
		return result;
	}
	
}
