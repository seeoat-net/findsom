package model.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.dao.mybatis.mapper.NoiseMapper;
import model.dto.NoiseDTO;

public class NoiseDAO {
	private SqlSessionFactory sqlSessionFactory;
	
	public NoiseDAO() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	//내 방의 소음 신고 개수
	public int myNoise(String userID) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(NoiseMapper.class).selectMyNoise(userID); 
		} finally {
			sqlSession.close();
		}
	}
	
	//현재 소음 순위 (상위 10)
	public List<NoiseDTO> noiseRank() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(NoiseMapper.class).selectNoiseRank(); 
		} finally {
			sqlSession.close();
		}
	}
	
	// 소음 신고 (-1: 에러, 0: 신고 실패)
	public int noiseReport(String roomInfo) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(NoiseMapper.class).updateNoise(roomInfo);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;
		} finally {
			sqlSession.close();
		}
	}
}
