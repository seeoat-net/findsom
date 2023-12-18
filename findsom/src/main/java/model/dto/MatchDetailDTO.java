package model.dto;

import java.util.ArrayList;

import javax.naming.ldap.PagedResultsResponseControl;

import model.FindDTO;

//match 상세에서 볼 수 있는 정보에 대한 DTO
//MatchDTO + 게시글
public class MatchDetailDTO extends MatchDTO {
	
	private ArrayList<FindDTO> finds;
	
	public MatchDetailDTO(String userID, String nickname, ArrayList<FindDTO> finds) {
		super(userID, nickname);
		this.finds = finds;
	}

	public ArrayList<FindDTO> getFinds() {
		return finds;
	}

	public void setFinds(ArrayList<FindDTO> finds) {
		this.finds = finds;
	}
	
	@Override
	public String toString() {
		String result = super.toString();
		for (FindDTO f : finds) {
			result = result + "\n" + f.toString();
		}
		return result;
	}
}
