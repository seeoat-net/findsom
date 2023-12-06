package model.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

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
		
		System.out.println("in manager, matching result!");
		for (MatchDTO u : users) {
			System.out.println(u.toString());
		}
		
		return users;
	}
	
	public MatchDetailDTO matchDetail(String userID) {
		return matchDAO.searchUserDetail(userID);
	}
	
	//DAO TEST
	/*
	public static void main(String[] args) throws SQLException {
		MatchDAO matchDao = new MatchDAO();
		Scanner scanner = new Scanner(System.in);
		
		//현재 룸메이트를 구인 중인 모든 유저의 MatchDTO
		ArrayList<MatchDTO> users = matchDao.searchUsers();
		for (MatchDTO u : users) {
			System.out.println(u.toString());
		}
		
		ArrayList<String> pattern = new ArrayList<String>();
		pattern.add("morning");
		pattern.add("morning");
		pattern.add("morning");
		pattern.add("morning");
		pattern.add("morning");
		pattern.add("morning");
		pattern.add("morning");
		pattern.add("morning");
		pattern.add("morning");
		pattern.add("morning");		
		
		//특정 유저의 MatchDetailDTO
		System.out.print("userID : ");
		String userID = scanner.next();
		MatchDetailDTO user = matchDao.searchUserDetail(userID);
		System.out.print(user.toString());

		scanner.close();
	}
	*/	
}
