package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.dto.CommentDTO;
import model.dto.FreeDTO;

public class FreeDAO {
	private static final Logger log = LoggerFactory.getLogger(FreeDAO.class);
	private JDBCUtil jdbcUtil = null;
		
	public FreeDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}

	/** 자유 게시판 글 작성.*/ //Sequence_freePostID
	public FreeDTO create(FreeDTO post) throws SQLException {
		String sql = "INSERT INTO FREEBOARDPOST VALUES (Sequence_freePostID.nextval, ?, ?, ?, ?, ?)";		
		Object[] param = new Object[] { post.getTitle(), post.getUserID(),
										post.getIsAnonymous(), post.getContent(), post.getCategory()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
		log.debug("dao- create() 실행, object param 넣기 완료");
		
		String key[] = {"freepostID"};	// PK 컬럼의 이름   
		try {				
			jdbcUtil.executeUpdate(key);  // insert 문 실행
			log.debug("executeUpdate 완료");
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
	
	/*** 확인할 게시글 하나 찾아 반환 */
	public FreeDTO freeCheckPost(int freepostID) throws SQLException {
        String sql = "SELECT * "
        			+ "FROM FREEBOARDPOST "
        			+ "WHERE freepostID=?";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {freepostID});	// JDBCUtil에 query문과 매개 변수 설정
		FreeDTO post = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						//  정보 발견
				post = new FreeDTO(		// 객체를 생성하여 커뮤니티 정보를 저장
					freepostID,
					rs.getString("title"),
					rs.getString("userID"),
					rs.getString("isAnonymous"),
					rs.getString("content"),
					rs.getString("category"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return post;
	}
	
	// 특정 포스트아이디로 댓글 조회
    public List<CommentDTO> freeCommentsByPostID(int postId) throws SQLException {
        List<CommentDTO> comments = new ArrayList<CommentDTO>();
        String sql = "SELECT * FROM CommentInfo WHERE freepostID = ?";
        jdbcUtil.setSqlAndParameters(sql, new Object[]{ postId });

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            while (rs.next()) {
                CommentDTO comment = new CommentDTO(
                    rs.getInt("commentID"),
                    rs.getString("content"),
                    rs.getTimestamp("commentDate").toLocalDateTime(),
                    rs.getString("userID"),
                    rs.getInt("freepostID"),
                    rs.getInt("findpostID")
                );
                comments.add(comment);
            }
        } catch (SQLException ex) {
            log.error("fail", ex);
            throw ex;
        } finally {
            jdbcUtil.close();
        }
        return comments;
    }

	/*** 자유 게시판 글 수정	-제목, 내용(title,content)*/
	public int update(FreeDTO post) throws SQLException {
		String sql = "UPDATE FREEBOARDPOST "
					+ "SET title=?, contents=? "
					+ "WHERE freepostID=?";
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
	public int remove(int postID) throws SQLException {
		String sql = "DELETE "
     			+ "FROM FREEBOARDPOST "
     			+ "WHERE freepostID=?";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {postID});	// JDBCUtil에 delete문과 매개 변수 설정
		
		try {				
			int result = jdbcUtil.executeUpdate();	// delete 문 실행
			log.debug("freeDAO-dao-remove 실행 완료");
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
	public List<FreeDTO> search(String keyword) throws SQLException {
		String sql = "SELECT * "
	    			+ "FROM FREEBOARDPOST "
	    			+ "WHERE title LIKE ? or content LIKE ?"; 
		String searchTerm = "%" + keyword +"%";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {searchTerm, searchTerm});	// JDBCUtil에 query문과 매개 변수 설정
		log.debug("serch query 실행 완료");
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			List<FreeDTO> searchResults = new ArrayList<>();
			
			while (rs.next()) {						// 정보 발견
				FreeDTO post = new FreeDTO(		// 객체를 생성하여 정보를 저장
						rs.getInt("freepostID"),
						rs.getString("title"),
						rs.getString("userID"),
						rs.getString("isAnonymous"),
						rs.getString("content"),
						rs.getString("category"));
				searchResults.add(post);
			};
			return searchResults;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		
		 return Collections.emptyList(); // 검색 결과가 없을 때 빈 리스트를 반환
	}
	
	/*** 전체 자유 게시글 정보를 검색하여 List에 저장 및 반환	 */
	public List<FreeDTO> totalFreeList() throws SQLException {
        String sql = "SELECT * "
        		   + "FROM FREEBOARDPOST "
        		   + "ORDER BY freepostID";        
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
	
	/*** info 자유 게시글 정보를 검색하여 List에 저장 및 반환	 */
	public List<FreeDTO> infoFreeList() throws SQLException {
        String sql = "SELECT * "
        		   + "FROM FREEBOARDPOST "
        		   + "WHERE category='info' "
        		   + "ORDER BY freepostID";        
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
			log.debug("freeDAO-info 실행 완료");
			return freeList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	public List<FreeDTO> purchaseFreeList() throws SQLException {
        String sql = "SELECT * "
        		   + "FROM FREEBOARDPOST "
        		   + "WHERE category='purchase' "
        		   + "ORDER BY freepostID";        
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
			log.debug("FreeDAO-purchase 실행 완료");
			return freeList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	public List<FreeDTO> shareFreeList() throws SQLException {
        String sql = "SELECT * "
        		   + "FROM FREEBOARDPOST "
        		   + "WHERE category='share' "
        		   + "ORDER BY freepostID";        
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
			log.debug("FreeDAO-share 실행 완료");
			return freeList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	public List<FreeDTO> otherFreeList() throws SQLException {
        String sql = "SELECT * "
        		   + "FROM FREEBOARDPOST "
        		   + "WHERE category='other' "
        		   + "ORDER BY freepostID";        
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
			log.debug("FreeDAO-other 실행 완료");
			return freeList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
//	/*** catogery별 게시글들 검색하여 List에 저장 및 반환	 */ //category: info/purchase/share/other
	
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
