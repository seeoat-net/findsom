package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.FindDTO;
import model.dto.MatchDTO;

public class FindDAO {
	private static final Logger log = LoggerFactory.getLogger(FindDAO.class);
	private JDBCUtil jdbcUtil = null;
	
	public FindDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	/**게시글 작성페이지에 내 성향 불러오기 리스트로 만들어 전송 */
	public List<String> getLifePatternsByUserID(String userID) throws SQLException {
	    List<String> lifePatterns = new ArrayList<>();
	    String sql = "SELECT lifePattern FROM LIFEPATTERNS WHERE userID = ?";
	    jdbcUtil.setSqlAndParameters(sql, new Object[]{userID});

	    try {
	        ResultSet rs = jdbcUtil.executeQuery();
	        while (rs.next()) {
	            String lifePattern = rs.getString("lifePattern");
	            lifePatterns.add(lifePattern);
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    } finally {
	        jdbcUtil.close();
	    }
	    return lifePatterns;
	}
	/** 구인 게시판 글 작성. //내성향 DB에서 불러오기*/
	public FindDTO create(FindDTO post)  throws SQLException { //postid sequence쓰는지 확인
								
		List<String> lifepattern = getLifePatternsByUserID(post.getUserID());
		String sql = "INSERT INTO FINDBOARDPOST (findpostID, isAnonymous, title, prefer, mycontent, matecontent, userID) VALUES (Sequence_findPostID.NEXTVAL, ?, ?, ?, ?, ?, ?)";			
		Object[] param = new Object[] { post.getIsAnonymous(), post.getTitle(), post.getPrefer(), post.getMycontent(), post.getMatecontent(), post.getUserID()};
<<<<<<< HEAD
		log.debug("dao- create() 실행, object param 넣기 완료");
		
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
		log.debug("dao- jdbcUtil.setSqlAndParameters(sql, param) 완료");				
=======

		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
>>>>>>> db76e475bc2d191eccb977f44b093eee6671fe12

		String key[] = {"findpostID"};	// PK 컬럼의 이름
		try {    
			jdbcUtil.executeUpdate(key);  // insert 문 실행
		   	ResultSet rs = jdbcUtil.getGeneratedKeys();
		   	if(rs.next()) {
		   		int generatedKey = rs.getInt(2);   // 생성된 PK 값
		   		post.setFindpostID(generatedKey); 	// id필드에 저장  
		   		log.debug("sequence generatedkeys 완료");
		   	}
		   	return post;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return null;			
	}

<<<<<<< HEAD
	/*** 구인 게시판 글 수정	-제목, 우대사항, 상대성향(title,matecontent,prefer 부분 */
	public int update(FindDTO post) throws SQLException {
		String sql = "UPDATE FINDBOARDPOST "
					+ "SET title=?, matecontent=?, prefer=? " 
					+ "WHERE findpostID=?";
		Object[] param = new Object[] {post.getTitle(), post.getMatecontent(), post.getPrefer(), post.getFindpostID()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}

	/*** 구인게시판 글 삭제 */
	public int remove(String postID) throws SQLException {
		String sql = "DELETE FROM FINDBOARDPOST WHERE findpostID=?";		

		jdbcUtil.setSqlAndParameters(sql, new Object[] {postID});	// JDBCUtil에 delete문과 매개 변수 설정

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}

	/***keywork에 해당하는 구인 게시판 글 검색 */
//	public List<FindDTO> search(String keyword) throws SQLException {
//	    String sql = "SELECT * FROM FINDBOARDPOST WHERE title LIKE ? OR prefer LIKE ? OR mycontent LIKE ? OR matecontent LIKE ?";
//	    String searchTerm = "%" + keyword + "%";
//
//	    jdbcUtil.setSqlAndParameters(sql, new Object[] { searchTerm, searchTerm, searchTerm, searchTerm });
//
//	    try {
//	        ResultSet rs = jdbcUtil.executeQuery();
//	        List<FindDTO> searchResults = new ArrayList<>();
//
//	        while (rs.next()) {
////	            FindDTO post = new FindDTO(
////	                rs.getInt("findpostID"),
////	                rs.getString("isAnonymous"),
////	                rs.getString("title"),
////	                rs.getString("prefer"),
////	                rs.getString("mycontent"),
////	                rs.getString("matecontent"),
////	                rs.getString("userID")
////	            );
//
////	            searchResults.add(post);
//	        }
//
//	        return searchResults;
//	    } catch (Exception ex) {
//	        ex.printStackTrace();
//	    } finally {
//	        jdbcUtil.close();
//	    }
//
//	    return Collections.emptyList(); // 검색 결과가 없을 때 빈 리스트를 반환
//	}
	
//	/*** 전체 구인 게시글 정보를 검색하여 List에 저장 및 반환	 */
//	public List<FindDTO> totalFindList() throws SQLException {
//
//        String sql = "SELECT * "
//        		   + "FROM FINDBOARDPOST "
//        		   + "ORDER BY postID";        
//		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
//					
//		
//		try {
//			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
//			List<FindDTO> findList = new ArrayList<FindDTO>();	// 리스트 생성
//			while (rs.next()) {
//				FindDTO find = new FindDTO(			// 객체를 생성하여 현재 행의 정보를 저장
//						rs.getInt("findpostID"),
//						rs.getString("isAnonymous"),
//						rs.getString("title"),
//						rs.getString("prefer"),
//						rs.getString("mycontent"),
//						rs.getString("matecontent"),
//						rs.getString("userID"));
//				findList.add(find);				// List에 객체 저장
//			}		
//			return findList;					
//			
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			jdbcUtil.close();		// resource 반환
//		}
//		return null;
//	}
	
	 //리스트 조회에서 보여줄 제목,우대사항 리스트
	public List<FindDTO> showFindList() throws SQLException {
        String sql = "SELECT * "
        		   + "FROM FINDBOARDPOST "
        		   + "ORDER BY findpostID";        
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<FindDTO> findList = new ArrayList<FindDTO>();	// 리스트 생성
			while (rs.next()) {
				FindDTO find = new FindDTO(	
						rs.getString("title"),
						rs.getString("prefer"));
				findList.add(find);				// List에 객체 저장
			}		
			return findList;					
=======
	/*** 확인할 게시글 하나 찾아 반환 */
	public FindDTO findCheckPost(int findpostID) throws SQLException {
        String sql = "SELECT * "
        			+ "FROM FINDBOARDPOST "
        			+ "WHERE findpostID=?";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {findpostID});	// JDBCUtil에 query문과 매개 변수 설정
		FindDTO post = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			log.debug("query실행 완료");
			if (rs.next()) {						//  정보 발견
				post = new FindDTO(		// 객체를 생성하여 커뮤니티 정보를 저장
					findpostID,
					rs.getString("isAnonymous"),
					rs.getString("title"),
					rs.getString("prefer"),
					rs.getString("mycontent"),
					rs.getString("matecontent"),
					rs.getString("userID"));
			}
			log.debug("DTO 생성 완료");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return post;
	}
	
	/*** 전체 구인 게시글 정보를 검색하여 List에 저장 및 반환	 */
	public List<FindDTO> totalFindList() throws SQLException {

        String sql = "SELECT * "
        		   + "FROM FINDBOARDPOST "
        		   + "ORDER BY findpostID";        
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<FindDTO> findList = new ArrayList<FindDTO>();	// 리스트 생성
			while (rs.next()) {
				FindDTO find = new FindDTO(			// 객체를 생성하여 현재 행의 정보를 저장
						rs.getInt("findpostID"),
						rs.getString("isAnonymous"),
						rs.getString("title"),
						rs.getString("prefer"),
						rs.getString("mycontent"),
						rs.getString("matecontent"),
						rs.getString("userID"));
				findList.add(find);				// List에 객체 저장
			}		
			return findList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	/*** 구인 게시판 글 수정	-제목, 우대사항, 상대성향(title,matecontent,prefer 부분 */
	public int update(FindDTO post) throws SQLException {
		String sql = "UPDATE FINDBOARDPOST "
					+ "SET title=?, matecontent=?, prefer=? " 
					+ "WHERE findpostID=?";
		Object[] param = new Object[] {post.getTitle(), post.getMatecontent(), post.getPrefer(), post.getFindpostID()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정
>>>>>>> db76e475bc2d191eccb977f44b093eee6671fe12
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}

	/*** 구인게시판 글 삭제 */
	public int remove(int postID) throws SQLException {
		String sql = "DELETE FROM FINDBOARDPOST WHERE findpostID=?";		

		jdbcUtil.setSqlAndParameters(sql, new Object[] {postID});	// JDBCUtil에 delete문과 매개 변수 설정

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}

	/***keywork에 해당하는 구인 게시판 글 검색 */
	public List<FindDTO> search(String keyword) throws SQLException {
	    String sql = "SELECT * FROM FINDBOARDPOST WHERE title LIKE ? OR prefer LIKE ? OR mycontent LIKE ? OR matecontent LIKE ?";
	    String searchTerm = "%" + keyword + "%";

	    jdbcUtil.setSqlAndParameters(sql, new Object[] { searchTerm, searchTerm, searchTerm, searchTerm });
	    log.debug("serch query 실행 완료");
	    try {
	        ResultSet rs = jdbcUtil.executeQuery();
	        List<FindDTO> searchResults = new ArrayList<>();

	        while (rs.next()) {
	            FindDTO post = new FindDTO(
	                rs.getInt("findpostID"),
	                rs.getString("isAnonymous"),
	                rs.getString("title"),
	                rs.getString("prefer"),
	                rs.getString("mycontent"),
	                rs.getString("matecontent"),
	                rs.getString("userID")
	            );

	            searchResults.add(post);
	        }
	        log.debug("serchresult 실행 완료");
	        return searchResults;
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    } finally {
	        jdbcUtil.close();
	    }

	    return Collections.emptyList(); // 검색 결과가 없을 때 빈 리스트를 반환
	}
	

}
