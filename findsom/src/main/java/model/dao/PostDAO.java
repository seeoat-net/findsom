package model.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import model.dto.PostDTO;

public class PostDAO {
	
	private JDBCUtil jdbcUtil = null;
	
	public PostDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	// 아이디로 구인 게시글 간단 조회
	public ArrayList<PostDTO> findPostById(String userId) {
		ArrayList<PostDTO> userPosts = new ArrayList<PostDTO>();
		
		// 구인 게시판
		StringBuilder findQuery = new StringBuilder();
		findQuery.append("SELECT findpostid, title, mycontent, matecontent ");
		findQuery.append("FROM FINDBOARDPOST ");
		findQuery.append("WHERE userID = ? ");
        
    	try {
			
			jdbcUtil.setSqlAndParameters(findQuery.toString(), new Object[]{userId});
			ResultSet rs = jdbcUtil.executeQuery();
			while (rs.next()) {		// 검색 결과 존재
				PostDTO post = new PostDTO("구인", rs.getInt("findpostid"), 
						rs.getString("title"), rs.getString("matecontent"));
				userPosts.add(post);
			}
			return userPosts;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 등 해제
		}
		return null;
	}
	
	// 아이디로 자유 게시글 간단 조회
	public ArrayList<PostDTO> freePostById(String userId) {
		ArrayList<PostDTO> userPosts = new ArrayList<PostDTO>();
		
		// 자유 게시판
		StringBuilder freeQuery = new StringBuilder();
		freeQuery.append("SELECT freepostid, title, content ");
		freeQuery.append("FROM FREEBOARDPOST ");
		freeQuery.append("WHERE userID = ? ");
        
    	try {
    		jdbcUtil.setSqlAndParameters(freeQuery.toString(), new Object[]{userId});
			ResultSet rs = jdbcUtil.executeQuery();
			while (rs.next()) {		// 검색 결과 존재
				PostDTO post = new PostDTO("자유", rs.getInt("freepostid"), 
						rs.getString("title"), rs.getString("content"));
				userPosts.add(post);
			}
			return userPosts;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 등 해제
		}
		return null;
	}
}
