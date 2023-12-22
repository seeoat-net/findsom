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
	    	jdbcUtil.setSqlAndParameters(query1.toString(), new Object[]{"recruiting"});
	    	
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
	        query.append("SELECT p.findpostID, p.isAnonymous, p.title, p.prefer, p.mycontent, p.matecontent, u.nickname ");
	        query.append("FROM findboardpost p, userinfo u ");
	        query.append("WHERE p.userID = u.userID and p.userID = ? ");
	        jdbcUtil.setSqlAndParameters(query.toString(), new Object[]{ userID });
	        ArrayList<FindDTO> finds = new ArrayList<FindDTO>();
	        ResultSet rs = jdbcUtil.executeQuery();

	        
			while (rs.next()) {
				FindDTO dto = new FindDTO(rs.getInt("findpostID"), rs.getString("isAnonymous"), rs.getString("title"),
						rs.getString("prefer"), rs.getString("mycontent"), rs.getString("matecontent"), userID);
				finds.add(dto);
			}
			
			
			StringBuilder query2 = new StringBuilder();
	        query2.append("SELECT LifePattern ");
	        query2.append("FROM LifePatterns ");
	        query2.append("WHERE userID = ? ");
	        jdbcUtil.setSqlAndParameters(query2.toString(), new Object[]{ userID });
	     
    		rs = jdbcUtil.executeQuery();
    		ArrayList<String> patterns = new ArrayList<String>();
			while (rs.next()) {		
				switch (rs.getString("LifePattern")) {
                case "morning": 
                	patterns.add("아침형"); break;
                case "night":
                    patterns.add("저녁형"); break;
                case "smoker": 
                	patterns.add("흡연자"); break;
                case "nonSmoker":
                	patterns.add("비흡연자"); break;
                case "semester": 
                	patterns.add("학기중"); break;
                case "vacation":
                	patterns.add("방학까지"); break;
                case "morningShower": 
                	patterns.add("아침사워"); break;
                case "nightShower":
                	patterns.add("밤샤워"); break;
                case "one": 
                	patterns.add("알람 한개"); break;
                case "many":
                	patterns.add("알람 여러개"); break;
                case "teethGrinding":
                	patterns.add("이갈이"); break;
                case "snoring":
                	patterns.add("코골이"); break;
                case "ear":
                	patterns.add("잠귀 밝음"); break;
                case "yesFriendship": 
                	patterns.add("친목O"); break;
                case "noFriendship":
                	patterns.add("친목X"); break;
                case "yesEarphones": 
                	patterns.add("이어폰O"); break;
                case "noEarphones":
                	patterns.add("이어폰X"); break;
                case "yesclean": 
                	patterns.add("청결유지"); break;
                case "noclean":
                	patterns.add("더러워도 됨"); break;
                case "yesEatInRoom": 
                	patterns.add("방 안 취식O"); break;
                case "noEatInRoom":
                	patterns.add("방 안 취식X"); break;
                case "1": 
                	patterns.add("1층 침대"); break;
                case "2":
                	patterns.add("2층 침대"); break;
                default:
                	patterns.add(rs.getString("LifePattern")); break;
	            }
			}
			
	        MatchDetailDTO detailDTO = new MatchDetailDTO(userID, patterns, finds);
			
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
