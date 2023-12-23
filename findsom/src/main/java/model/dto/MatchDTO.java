package model.dto;

import java.util.ArrayList;

//match시, 필요한 기본 정보
public class MatchDTO implements Comparable<MatchDTO> {
	
	protected String userID;
	protected String nickname;
	protected ArrayList<String> lifePatternList;
	private int cnt;
	
	public MatchDTO(String userID, String nickname) {  
		this.userID = userID;
		this.nickname = nickname;
	}
	public MatchDTO(String userID, ArrayList<String> patterns) {
		this.userID = userID;
		this.lifePatternList = patterns;
	}
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public ArrayList<String> getLifePatternList() {
		return lifePatternList;
	}
	public void setLifePatternList(ArrayList<String> lifePatternList) {
		this.lifePatternList = lifePatternList;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public void plusCnt() {
		this.cnt += 1;
	}
	
	public void patternListChangeToKor() {
		ArrayList<String> patterns = new ArrayList<String>();
		for (String p: lifePatternList) {		
			switch (p) {
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
	            	patterns.add(p); break;
	            }
		}
		this.lifePatternList = patterns;
	}
	
	public void patternChangeToKor(String p) {
			switch (p) {
	            case "morning": 
	            	lifePatternList.add("아침형"); break;
	            case "night":
	            	lifePatternList.add("저녁형"); break;
	            case "smoker": 
	            	lifePatternList.add("흡연자"); break;
	            case "nonSmoker":
	            	lifePatternList.add("비흡연자"); break;
	            case "semester": 
	            	lifePatternList.add("학기중"); break;
	            case "vacation":
	            	lifePatternList.add("방학까지"); break;
	            case "morningShower": 
	            	lifePatternList.add("아침사워"); break;
	            case "nightShower":
	            	lifePatternList.add("밤샤워"); break;
	            case "one": 
	            	lifePatternList.add("알람 한개"); break;
	            case "many":
	            	lifePatternList.add("알람 여러개"); break;
	            case "teethGrinding":
	            	lifePatternList.add("이갈이"); break;
	            case "snoring":
	            	lifePatternList.add("코골이"); break;
	            case "ear":
	            	lifePatternList.add("잠귀 밝음"); break;
	            case "yesFriendship": 
	            	lifePatternList.add("친목O"); break;
	            case "noFriendship":
	            	lifePatternList.add("친목X"); break;
	            case "yesEarphones": 
	            	lifePatternList.add("이어폰O"); break;
	            case "noEarphones":
	            	lifePatternList.add("이어폰X"); break;
	            case "yesclean": 
	            	lifePatternList.add("청결유지"); break;
	            case "noclean":
	            	lifePatternList.add("더러워도 됨"); break;
	            case "yesEatInRoom": 
	            	lifePatternList.add("방 안 취식O"); break;
	            case "noEatInRoom":
	            	lifePatternList.add("방 안 취식X"); break;
	            case "1": 
	            	lifePatternList.add("1층 침대"); break;
	            case "2":
	            	lifePatternList.add("2층 침대"); break;
	            default:
	            	lifePatternList.add(p); break;
	            }
		
	}
	
	public String printPatterns() {
		String result = "";
		for (String s : lifePatternList) {
			result = result + s + ", ";
		}
		result = result + "\n 유사도:" + Integer.toString(cnt);
		return result;
	}
	
	@Override
	public String toString() {
		String result = userID + ", " + nickname + ", ";
		for (String s : lifePatternList) {
			result = result + s + "/";
		}
		result = result + " 유사도:" + Integer.toString(cnt);
		return result;
	}

	@Override
	public int compareTo(MatchDTO o) {
		return o.getCnt() - this.cnt;
	}
}
