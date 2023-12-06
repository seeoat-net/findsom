package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.FreeDTO;

public class FreeDAO {
	private JDBCUtil jdbcUtil = null;
		
	public FreeDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	//search
	/** 자유 게시판 글 작성.*/ //Sequence_freePostID
	public FreeDTO create(FreeDTO post) throws SQLException {
		String sql = "INSERT INTO FREEBOARDPOST VALUES (Sequence_freePostID, ?, ?, ?, ?, ?)";		
		Object[] param = new Object[] { post.getTitle(), post.getUserID(),
										post.getIsAnonymous(), post.getContent(), post.getCategory()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
						
		String key[] = {"freepostID"};	// PK 컬럼의 이름   
		try {				
			jdbcUtil.executeUpdate(key);  // insert 문 실행
		   	ResultSet rs = jdbcUtil.getGeneratedKeys();
		   	if(rs.next()) {
		   		int generatedKey = rs.getInt(1);   // 생성된 PK 값
		   		post.setFreepostID(generatedKey); 	// id필드에 저장  
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

	/*** 자유 게시판 글 수정	-제목, 내용(title,content)*/
	public int update(FreeDTO post) throws SQLException {
		String sql = "UPDATE FREEBOARDPOST "
					+ "SET title=?, contents=? "
					+ "WHERE postID=?";
		Object[] param = new Object[] {post.getTitle(), post.getContent(), post.getFreepostID()};				
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

	/*** 자유 게시판 글 삭제	 */
	public int remove(String postID) throws SQLException {
		String sql = "DELETE FROM FREEBOARDPOST WHERE postID=?";		
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

	/***keywork에 해당하는 자유 게시판 글 검색 */
	public FreeDTO search(String keyword) throws SQLException {
		String sql = "SELECT * "
    			+ "FROM FREEBOARDPOST "
    			+ "WHERE title LIKE '%?%' or contents LIKE '%?%'";              
	jdbcUtil.setSqlAndParameters(sql, new Object[] {keyword, keyword, keyword});	// JDBCUtil에 query문과 매개 변수 설정

	try {
		ResultSet rs = jdbcUtil.executeQuery();		// query 실행
		if (rs.next()) {						// 정보 발견
			FreeDTO post = new FreeDTO(		// 객체를 생성하여 학생 정보를 저장
					rs.getInt("freepostID"),
					rs.getString("title"),
					rs.getString("userID"),
					rs.getString("isAnonymous"),
					rs.getString("content"),
					rs.getString("category"));
			return post;
		}
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		jdbcUtil.close();		// resource 반환
	}
	return null;
}
	
	/*** 전체 자유 게시글 정보를 검색하여 List에 저장 및 반환	 */
	public List<FreeDTO> searchFreeList() throws SQLException {
        String sql = "SELECT * "
        		   + "FROM FREEBOARDPOST "
        		   + "ORDER BY postId";        
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
	try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<FreeDTO> freeList = new ArrayList<FreeDTO>();	// 리스트 생성
			while (rs.next()) {
				FreeDTO free = new FreeDTO(			// 객체를 생성하여 현재 행의 정보를 저장
						rs.getInt("freepostID"),
						rs.getString("title"),
						rs.getString("userID"),
						rs.getString("isAnonymous"),
						rs.getString("content"),
						rs.getString("category"));
				freeList.add(free);				// List에 객체 저장
			}		
			return freeList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
//	/*** catogery별 게시글들 검색하여 List에 저장 및 반환	 */ //category: info/purchase/share/other
//	public List<FreeDTO> categoryClassification(String category) throws SQLException {
//        String sql = "SELECT * FROM FREEBOARDPOST "
//     				+ "WHERE category = ?";                         
//		jdbcUtil.setSqlAndParameters(sql, new Object[] {category});	// JDBCUtil에 query문과 매개 변수 설정
//		
//		try {
//			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
//			List<FreeDTO> postList = new ArrayList<FreeDTO>();	// 게시들을의 리스트 생성
//			while (rs.next()) {
//				FreeDTO post = new FreeDTO(			// 객체를 생성하여 현재 행의 정보를 저장
//					rs.getInt("freepostID"),
//					rs.getString("title"),
//					rs.getString("userID"),
//					rs.getString("isAnonymous"),
//					rs.getString("content"),
//					rs.getString("category"));
//				postList.add(post);			// List에 객체 저장
//			}		
//			return postList;					
//				
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			jdbcUtil.close();		// resource 반환
//		}
//		return null;
//	}
	//카테고리 4개 분류해서 각각 리스트에 저장
	public Map<String, List<FreeDTO>> categoryClassification() throws SQLException {
	    Map<String, List<FreeDTO>> categoryMap = new HashMap<>(); // 카테고리별 게시물을 저장할 맵 생성

	    String[] categories = { "info", "purchase", "share", "other" }; // 카테고리 목록

	    for (String category : categories) {
	        String sql = "SELECT * FROM FREEBOARDPOST WHERE category = ?";
	        jdbcUtil.setSqlAndParameters(sql, new Object[] { category }); // JDBCUtil에 query문과 매개 변수 설정

	        try {
	            ResultSet rs = jdbcUtil.executeQuery(); // query 실행
	            List<FreeDTO> postList = new ArrayList<FreeDTO>(); // 카테고리별 게시물의 리스트 생성
	            while (rs.next()) {
	                FreeDTO post = new FreeDTO( // 객체를 생성하여 현재 행의 정보를 저장
	                    rs.getInt("freepostID"),
	                    rs.getString("title"),
	                    rs.getString("userID"),
	                    rs.getString("isAnonymous"),
	                    rs.getString("content"),
	                    rs.getString("category"));
	                postList.add(post); // List에 객체 저장
	            }
	            categoryMap.put(category, postList); // 카테고리별 게시물 리스트를 맵에 저장

	        } catch (Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            jdbcUtil.close(); // resource 반환
	        }
	    }

	    return categoryMap;
	}
}
