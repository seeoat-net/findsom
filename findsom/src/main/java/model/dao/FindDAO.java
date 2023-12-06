package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.FindDTO;

public class FindDAO {
	private JDBCUtil jdbcUtil = null;
	
	public FindDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	/** 구인 게시판 글 작성. //내성향 DB에서 불러오기*/
	public FindDTO create(FindDTO post)  throws SQLException { //postid sequence쓰는지 확인
		//userid 같은 곳에 lifepatterns의 lifepattern가져오기
		//userid는 session에 저장되어있음
		
		String sql = "INSERT INTO FINDBOARDPOST VALUES (Sequence_findPostID.nextval, ?, ?, ?, (SELECT l.lifePattern FROM FINDBOARDPOST f, LIFEPATTERNS l WHERE f.userID=l.userID), ?, ?)";		
		Object[] param = new Object[] { post.getIsAnonymous(), post.getTitle(),
										post.getPrefer(),post.getMycontent(), post.getMatecontent(),post.getUserID()};
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
						
		String key[] = {"findpostID"};	// PK 컬럼의 이름
		try {    
			jdbcUtil.executeUpdate(key);  // insert 문 실행
		   	ResultSet rs = jdbcUtil.getGeneratedKeys();
		   	if(rs.next()) {
		   		int generatedKey = rs.getInt(1);   // 생성된 PK 값
		   		post.setFindpostID(generatedKey); 	// id필드에 저장  
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
	public FindDTO search(String keyword) throws SQLException {
        String sql = "SELECT * "
        			+ "FROM FINDBOARDPOST "
        			+ "WHERE LIKE '%\"+searchText.trim()+\"%'";
//        			+ "WHERE title LIKE '%?%' or mycontent LIKE '%?%' or matecontent LIKE'%?%' or prefer LIKE '%?%'";              
//		jdbcUtil.setSqlAndParameters(sql, new Object[] {keyword, keyword, keyword, keyword});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						// 정보 발견
				FindDTO post = new FindDTO(		// 객체를 생성하여 학생 정보를 저장
						rs.getInt("findpostID"),
						rs.getString("isAnonymous"),
						rs.getString("title"),
						rs.getString("prefer"),
						rs.getString("mycontent"),
						rs.getString("matecontent"),
						rs.getString("userID"));
				return post;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	/*** 전체 구인 게시글 정보를 검색하여 List에 저장 및 반환	 */
	public List<FindDTO> totalFindList() throws SQLException {
        String sql = "SELECT * "
        		   + "FROM FINDBOARDPOST "
        		   + "ORDER BY postID";        
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
	
	 //리스트 조회에서 보여줄 제목,우대사항 리스트
	public List<FindDTO> showFindList() throws SQLException {
        String sql = "SELECT * "
        		   + "FROM FINDBOARDPOST "
        		   + "ORDER BY postID";        
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
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}

	
}
