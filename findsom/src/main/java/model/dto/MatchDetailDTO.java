package model.dto;

import java.util.ArrayList;
import model.dto.FindDTO;
//match 상세에서 볼 수 있는 정보에 대한 DTO
//MatchDTO + 게시글
public class MatchDetailDTO extends MatchDTO {

    //private String userId;
	private ArrayList<FindDTO> finds;
	
	public MatchDetailDTO(String userID, ArrayList<String> patterns, ArrayList<FindDTO> finds) {
		super(userID, patterns);
		 //this.userId = userID;
		this.finds = finds;
	}
	
	//public String getUserId() {
     //   return userId;
    //}

    //public void setUserId(String userId) {
     //   this.userId = userId;
    //}

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
