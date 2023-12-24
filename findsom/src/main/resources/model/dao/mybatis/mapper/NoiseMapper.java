package model.dao.mybatis.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import model.dto.NoiseDTO;

public interface NoiseMapper {
	
	// 내 방의 신고 개수
	public int selectMyNoise(@Param("userID") String userID);
	
	// 소음 순위
	public ArrayList<NoiseDTO> selectNoiseRank();
	
	// 소음 신고
	public int updateNoise(@Param("roomInfo") String roomInfo);
}
