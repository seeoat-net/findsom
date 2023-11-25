package model.manager;

import java.sql.SQLException;
import java.util.List;

import model.dao.NoiseDAO;

public class NoiseManager {
	private static NoiseManager noiseMan = new NoiseManager();
	private NoiseDAO noiseDAO;

	private NoiseManager() {
		try {
			noiseDAO = new NoiseDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static NoiseManager getInstance() {
		return noiseMan;
	}
	
	//내 방의 소음 신고 개수
	public int myNoiseCount(String roomInfo) throws SQLException {
		return noiseDAO.myNoise(roomInfo);
	}
	
	//현재 소음 순위 (상위 3)
	public List<String> findNoiseRank() throws SQLException {
		return noiseDAO.noiseRank();
	}
	
	// 소음 신고 (-1: 에러, 0: 신고 실패)
	public int noiseReport(String roomInfo) throws SQLException {
		return noiseDAO.noiseReport(roomInfo);
	}


}