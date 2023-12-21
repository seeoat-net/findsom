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
	
	//내 게시글 조회
	public ArrayList<PostDTO> myPostList(String userID) throws SQLException {
		return postDAO.findPostById(userID);
	}
	
	//DAO TEST
	/*
	public static void main(String[] args) throws SQLException {
		PostDAO postDAO = new PostDAO();

		ArrayList<PostDTO> posts = postDAO.findPostById("a");
		for (PostDTO p : posts) {
			System.out.println(p.getBoardType() + ", " + Integer.toString(p.getPostId())
					+ ", " + p.getTitle() + ", " + p.getContents()); 
		}
	}
	 */
}
