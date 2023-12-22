package model.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.FindDTO;
import model.dao.FindDAO;
import model.dto.CommentDTO;

public class FindManager {
	private static FindManager find = new FindManager();
	private FindDAO findDAO;
	
	private FindManager() {
		try {
			findDAO = new FindDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	public static FindManager getInstance() {
		return find;
	}
	
	//작성#postWriteFindPost() = create()
	public FindDTO create(FindDTO post) throws SQLException {
		return findDAO.create(post);
	}
	
<<<<<<< HEAD
	//수정#postUpdateFindPost = update()
	public int update(FindDTO post) throws SQLException{
		return findDAO.update(post);
	}	
	 
	//삭제 findpostID로 삭제
	public int remove(String postId) throws SQLException {
		return findDAO.remove(postId);
	}
	
	//검색()#searchPost() = search()
//	public List<FindDTO> search(String keyword) throws SQLException {
//		return findDAO.search(keyword);
//	}
=======
	public FindDTO findCheckPost(int findpostID) throws SQLException{
		return findDAO.findCheckPost(findpostID);
	}
>>>>>>> db76e475bc2d191eccb977f44b093eee6671fe12
	
	public List<CommentDTO> findCommentByPostId(int postId) throws SQLException {
		return findDAO.findCommentsByPostID(postId);
	}
	
	public List<FindDTO> findPostList() throws SQLException {
		return findDAO.showFindList();
	}
	
	//수정#postUpdateFindPost = update()
	public int update(FindDTO post) throws SQLException{
		return findDAO.update(post);
	}	
	 
	//삭제 findpostID로 삭제
	public int remove(int postId) throws SQLException {
		return findDAO.remove(postId);
	}
	
	//검색()#searchPost() = search()
	public List<FindDTO> search(String keyword) throws SQLException {
		return findDAO.search(keyword);
	}
	
	
	public FindDAO getFindDAO() {
		return this.findDAO;
	}
	 
}




