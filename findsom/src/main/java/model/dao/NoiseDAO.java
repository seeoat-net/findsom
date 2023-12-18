package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.NoiseDTO;

public class NoiseDAO {
	//방의 신고 개수, 순위, 신고
	private JDBCUtil jdbcUtil = null;
	
	public NoiseDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	// 내 방의 신고 개수
	public int myNoise(String userID) throws SQLException {
		StringBuilder query = new StringBuilder();
        query.append("SELECT count ");
        query.append("FROM room ");
        query.append("WHERE roominfo = (SELECT roominfo FROM USERINFO WHERE userID = ? ) ");
    	jdbcUtil.setSqlAndParameters(query.toString(), new Object[]{userID});	
    	
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {		// 검색 결과 존재
				int myRoomCnt = -1;
				myRoomCnt = rs.getInt("count");
				return myRoomCnt;
			}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 등 해제
		}
		return -1;
	}

	// 소음 순위
	public ArrayList<NoiseDTO> noiseRank() throws SQLException {
		StringBuilder query = new StringBuilder();
	    query.append("SELECT roominfo, count ");
	    query.append("FROM ( SELECT roominfo, count FROM room ORDER BY count DESC ) ");
	    query.append("WHERE ROWNUM < 10");
	    jdbcUtil.setSqlAndParameters(query.toString(), null);
	    
	    	 
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			ArrayList<NoiseDTO> ranking = new ArrayList<NoiseDTO>();
			while (rs.next()) {		// 검색 결과 존재
				NoiseDTO dto = new NoiseDTO(rs.getString("roominfo"), rs.getInt("count"));
				ranking.add(dto);
			}
			return ranking;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 등 해제
		}
		return null;
	}

	// 소음 신고
	// 원래 있는 방에 대해서만
	public int noiseReport(String roomInfo) throws SQLException {
		StringBuilder query = new StringBuilder();
	    query.append("UPDATE room SET count = ");
	    query.append("(SELECT count FROM room WHERE roominfo = ?) + 1 ");
	    query.append("WHERE roominfo = ?");
	    jdbcUtil.setSqlAndParameters(query.toString(), new Object[]{roomInfo, roomInfo});
	    	 
		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 등 해제
		}
		return -1;
	}

}
