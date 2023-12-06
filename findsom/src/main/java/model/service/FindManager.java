package model.service;

import java.sql.SQLException;
import java.util.List;

import model.FindDTO;
import model.FreeDTO;
import model.dao.FindDAO;
/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * UserDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
 */
 
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
		return findDAO.searchFindList();
	}
	
	public FindDAO getFindDAO() {
		return this.findDAO;
	}
}
