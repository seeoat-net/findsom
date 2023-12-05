package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.FindDTO;
import model.dto.MatchDTO;
import model.dto.MatchDetailDTO;

public class MatchDAO {
	
	private JDBCUtil jdbcUtil = null;
	
	public MatchDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	//현재 룸메이트를 구인 중인 모든 유저의 MatchDTO
	public ArrayList<MatchDTO> searchUsers() throws SQLException {
		try {
			StringBuilder query1 = new StringBuilder();
	        query1.append("SELECT userID, nickname ");
	        query1.append("FROM userinfo ");
	        query1.append("WHERE isRecruite = ? ");
	    	jdbcUtil.setSqlAndParameters(query1.toString(), new Object[]{"모집중"});
	    	
	    	ArrayList<MatchDTO> matchDTOList = new ArrayList<MatchDTO>();
    	 
			ResultSet rs = jdbcUtil.executeQuery();
			while (rs.next()) {		// 검색 결과 존재
				MatchDTO matchDTO = new MatchDTO(rs.getString("userID"), rs.getString("nickname"));
				matchDTOList.add(matchDTO);
			}
			
			StringBuilder query2 = new StringBuilder();
	        query2.append("SELECT LifePattern ");
	        query2.append("FROM LifePatterns ");
	        query2.append("WHERE userID = ? ");
	    	
	    	for (MatchDTO m : matchDTOList) {
	    		jdbcUtil.setSqlAndParameters(query2.toString(), new Object[]{m.getUserID()});
	    		rs = jdbcUtil.executeQuery();
	    		
	    		ArrayList<String> patterns = new ArrayList<String>();
				while (rs.next()) {		
					patterns.add(rs.getString("LifePattern"));
				}
				m.setLifePatternList(patterns);
	    	}
			
			
			return matchDTOList;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 등 해제
		}
		
		return null;
	}
	
	//특정 유저의 MatchDetailDTO
	public MatchDetailDTO searchUserDetail(String userID) {
		try {
			StringBuilder query = new StringBuilder();
	        query.append("SELECT p.findpostID, p.isAnonymous, p.title, p.prefer, p.userID, p.content, u.nickname ");
	        query.append("FROM findboardpost p, userinfo u ");
	        query.append("WHERE p.userID = u.userID and u.userID = ? ");
	        
	        ResultSet rs = jdbcUtil.executeQuery();
	        ArrayList<FindDTO> finds = new ArrayList<FindDTO>();
	        String nickname = null;
	        
			while (rs.next()) {
				nickname = rs.getString("nickname");
				FindDTO dto = new FindDTO(rs.getInt("findpostID"), rs.getString("isAnonymous"), rs.getString("title"),
						rs.getString("prefer"), rs.getString("mycontent"), rs.getString("matecontent"), rs.getString("userID"));
				finds.add(dto);
			}
			MatchDetailDTO detailDTO = new MatchDetailDTO(userID, nickname, finds);
	
			return detailDTO;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 등 해제
		}
		return null;
	}

}
