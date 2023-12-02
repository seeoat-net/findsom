package model.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import model.dao.MatchDAO;
import model.dto.MatchDTO;
import model.dto.MatchDetailDTO;

public class MatchManager {
	private static MatchManager matchMan = new MatchManager();
	private MatchDAO matchDAO;

	private MatchManager() {
		try {
			matchDAO = new MatchDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static MatchManager getInstance() {
		return matchMan;
	}

	public ArrayList<MatchDTO> matching(ArrayList<String> lifePatterns) throws SQLException {
		ArrayList<MatchDTO> users = matchDAO.searchUsers();
		
		for (MatchDTO user : users) {
			for (String pattern : user.getLifePatternList()) {
				if (lifePatterns.contains(pattern)) { // contains() : List안에 같은 값이 있으면  true, 없으면 false
					user.plusCnt();
				}
			}
		}
		Collections.sort(users);
		
		return users;
	}
	
	public MatchDetailDTO matchDetail(String userID, String nickname) {
		return matchDAO.searchUserDetail(userID, nickname);
	}
	
}
