package model.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import model.dao.PostDAO;
import model.dto.PostDTO;

public class PostManager {
	private static PostManager postMan = new PostManager();
	private PostDAO postDAO;

	private PostManager() {
		try {
			postDAO = new PostDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static PostManager getInstance() {
		return postMan;
	}
	
	//내 구인 게시글 조회
	public ArrayList<PostDTO> myFindPostList(String userID) throws SQLException {
		return postDAO.findPostById(userID);
	}
	
	//내 자유 게시글 조회
	public ArrayList<PostDTO> myFreePostList(String userID) throws SQLException {
		return postDAO.freePostById(userID);
	}

}
