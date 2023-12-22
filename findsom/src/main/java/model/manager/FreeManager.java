package model.manager;

import java.sql.SQLException;
import java.util.List;

import model.FindDTO;
import model.FreeDTO;
import model.dao.FindDAO;
import model.dao.FreeDAO;
import model.dto.CommentDTO;

/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * UserDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
 */
////#findPostList() #postCreatePost() #postUpdateFreePost()#searchPost()
public class FreeManager {
	private static FreeManager free = new FreeManager();
	private FreeDAO freeDAO;
	
	private FreeManager() {
		try {
			freeDAO = new FreeDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	public static FreeManager getInstance() {
		return free;
	}
	
	//작성#postWriteFindPost() = create()
	public FreeDTO create(FreeDTO post) throws SQLException{
		return freeDAO.create(post);
	}
	
	public FreeDTO freeCheckPost(int findpostID) throws SQLException{
		return freeDAO.freeCheckPost(findpostID);
	}
	
	public List<CommentDTO> findCommentByPostId(int postId) throws SQLException {
		return freeDAO.freeCommentsByPostID(postId);
	}
	
	public List<CommentDTO> freeCommentByPostId(int postId) throws SQLException {
		return freeDAO.freeCommentsByPostID(postId);
	}
	//수정#postUpdateFindPost = update() //제목, 내용 수정
	public int update(FreeDTO post) throws SQLException{
		return freeDAO.update(post);
	}	
	//삭제 -> freepostID로 삭제
	public int remove(int postID) throws SQLException{
		return freeDAO.remove(postID);

	}
	//검색()#searchPost() = search()
	public List<FreeDTO> search(String keyword) throws SQLException {
		return freeDAO.search(keyword);
	}
	
	public List<FreeDTO> freePostList() throws SQLException {
		return freeDAO.totalFreeList();
	}
	
	public List<FreeDTO> freeInfoList() throws SQLException {
		return freeDAO.infoFreeList();
	}
	public List<FreeDTO> freePurchaseList() throws SQLException {
		return freeDAO.purchaseFreeList();
	}
	public List<FreeDTO> freeShareList() throws SQLException {
		return freeDAO.shareFreeList();
	}
	public List<FreeDTO> freeOtherList() throws SQLException {
		return freeDAO.otherFreeList();
	}
	
	
	public FreeDAO getFindDAO() {
		return this.freeDAO;
	}
}
