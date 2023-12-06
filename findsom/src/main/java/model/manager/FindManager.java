package model.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.FindDTO;
import model.dao.FindDAO;

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
	
	//수정#postUpdateFindPost = update()
	public int update(FindDTO post) throws SQLException{
		return findDAO.update(post);
	}	
	 
	//삭제 findpostID로 삭제
	public int remove(String postId) throws SQLException {
		return findDAO.remove(postId);
	}
	
	//검색()#searchPost() = search()
	public FindDTO search(String keyword) throws SQLException {
		return findDAO.search(keyword);
	}
	
	public List<FindDTO> findPostList() throws SQLException {
		return findDAO.totalFindList();
	}
	
	public FindDAO getFindDAO() {
		return this.findDAO;
	}
	
	 
}




