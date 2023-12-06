package model.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.dao.NoiseDAO;
import model.dto.NoiseDTO;

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
	public int myNoiseCount(String userID) throws SQLException {
		return noiseDAO.myNoise(userID);
	}
	
	//현재 소음 순위 (상위 10)
	public List<NoiseDTO> findNoiseRank() throws SQLException {
		return noiseDAO.noiseRank();
	}
	
	// 소음 신고 (-1: 에러, 0: 신고 실패)
	public int noiseReport(String roomInfo) throws SQLException {
		return noiseDAO.noiseReport(roomInfo);
	}

	//DAO TEST
	/*
	public static void main(String[] args) throws SQLException {
		NoiseDAO noiseDao = new NoiseDAO();
		Scanner scanner = new Scanner(System.in);
		
		//방의 소음 개수 myNoise
		System.out.print("roominfo : ");
		String roominfo = scanner.next();
        System.out.println("noise : " + noiseDao.myNoise(roominfo)); 	
        
        //소음 순위
        ArrayList<NoiseDTO> noiseList = noiseDao.noiseRank();
        for (NoiseDTO noise : noiseList) {
        	System.out.println(noise.getRoomInfo() + " " + noise.getCount()); 
        }
        
        //소음 신고 (-1 : 실패)
        System.out.print("reportRoom : ");
		String reportRoom = scanner.next();
        System.out.println("reportRoom : " + noiseDao.noiseReport(reportRoom)); 	
        
		scanner.close();
	}
	*/
}
