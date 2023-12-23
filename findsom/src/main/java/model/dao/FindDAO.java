package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.FindDTO;
import model.LifePattern;
import model.dto.CommentDTO;
import model.dto.MatchDTO;

public class FindDAO {
	private static final Logger log = LoggerFactory.getLogger(FindDAO.class);
	private JDBCUtil jdbcUtil = null;
	
	public FindDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	/**게시글 작성페이지에 내 성향 불러오기 리스트로 만들어 전송 */
	public List<String> getLifePatternsByUserID(String userID) throws SQLException {
	    List<String> lifePatterns = new ArrayList<>();
	    String sql = "SELECT lifePattern FROM LIFEPATTERNS WHERE userID = ?";
//	    LifePattern lifePattern = new LifePattern();  // 객체 생성
	    jdbcUtil.setSqlAndParameters(sql, new Object[]{userID});

	    try {
	        ResultSet rs = jdbcUtil.executeQuery();
	        while (rs.next()) {
	            String lifePattern = rs.getString("lifePattern");
	            switch (lifePattern) {
                case "morning": 
                	lifePatterns.add("아침형");
                	break;
                case "night":
                    lifePatterns.add("저녁형");
                    break;
                case "smoker": 
                	lifePatterns.add("흡연자");
    	            break;
                case "nonSmoker":
                	lifePatterns.add("비흡연자");
    	            break;
                case "semester": 
                	lifePatterns.add("학기중");
    	            break;
                case "vacation":
                	lifePatterns.add("방학까지");
    	            break;
                case "morningShower": 
                	lifePatterns.add("아침사워");
    	            break;
                case "nightShower":
                	lifePatterns.add("밤샤워");
    	            break;
                case "one": 
                	lifePatterns.add("알람 한개");
    	            break;
                case "many":
                	lifePatterns.add("알람 여러개");
    	            break;
                case "teethGrinding":
                	lifePatterns.add("이갈이");
    	            break;
                case "snoring":
                	lifePatterns.add("코골이");
    	            break;
                case "ear":
                	lifePatterns.add("잠귀 밝음");
    	            break;
                case "yesFriendship": 
                	lifePatterns.add("친목O");
    	            break;
                case "noFriendship":
                	lifePatterns.add("친목X");
    	            break;
                case "yesEarphones": 
                	lifePatterns.add("이어폰O");
    	            break;
                case "noEarphones":
                	lifePatterns.add("이어폰X");
    	            break;
                case "yesclean": 
                	lifePatterns.add("청결유지");
    	            break;
                case "noclean":
                	lifePatterns.add("더러워도 됨");
    	            break;
                case "yesEatInRoom": 
                	lifePatterns.add("방 안 취식O");
    	            break;
                case "noEatInRoom":
                	lifePatterns.add("방 안 취식X");
    	            break;
                case "1": 
                	lifePatterns.add("1층 침대");
    	            break;
                case "2":
                	lifePatterns.add("2층 침대");
    	            break;
                default:
                	lifePatterns.add(lifePattern);
                    break;
	            }

	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    } finally {
	        jdbcUtil.close();
	    }
	    return lifePatterns;
	}
	/** 구인 게시판 글 작성. //내성향 DB에서 불러오기*/
	public FindDTO create(FindDTO post)  throws SQLException { //postid sequence쓰는지 확인
								
		List<String> lifepattern = getLifePatternsByUserID(post.getUserID());
		String sql = "INSERT INTO FINDBOARDPOST (findpostID, isAnonymous, title, prefer, mycontent, matecontent, userID) VALUES (Sequence_findPostID.NEXTVAL, ?, ?, ?, ?, ?, ?)";			
		Object[] param = new Object[] { post.getIsAnonymous(), post.getTitle(), post.getPrefer(), post.getMycontent(), post.getMatecontent(), post.getUserID()};

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

	/*** 확인할 게시글 하나 찾아 반환 */
	public FindDTO findCheckPost(int findpostID) throws SQLException {
        String sql = "SELECT * "
        			+ "FROM FINDBOARDPOST "
        			+ "WHERE findpostID=?";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {findpostID});	// JDBCUtil에 query문과 매개 변수 설정
		FindDTO post = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			log.debug("query실행 완료");
			if (rs.next()) {						//  정보 발견
				post = new FindDTO(		// 객체를 생성하여 커뮤니티 정보를 저장
					findpostID,
					rs.getString("isAnonymous"),
					rs.getString("title"),
					rs.getString("prefer"),
					rs.getString("mycontent"),
					rs.getString("matecontent"),
					rs.getString("userID"));
			}
			log.debug("DTO 생성 완료");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return post;
	}
	
	// 특정 포스트아이디로 댓글 조회
    public List<CommentDTO> findCommentsByPostID(int postId) throws SQLException {
        List<CommentDTO> comments = new ArrayList<CommentDTO>();
        String sql = "SELECT * FROM CommentInfo WHERE findpostID = ?";
        jdbcUtil.setSqlAndParameters(sql, new Object[]{ postId });

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            while (rs.next()) {
                CommentDTO comment = new CommentDTO(
                    rs.getInt("commentID"),
                    rs.getString("content"),
                    rs.getTimestamp("commentDate").toLocalDateTime(),
                    rs.getString("userID"),
                    rs.getInt("freepostID"),
                    rs.getInt("findpostID")
                );
                comments.add(comment);
            }
        } catch (SQLException ex) {
            log.error("fail", ex);
            throw ex;
        } finally {
            jdbcUtil.close();
        }
        return comments;
    }
    

    //작성한 게시글 목록을 조회
    public List<FindDTO> findPostsByUserID(String userID) throws SQLException {
        List<FindDTO> posts = new ArrayList<>();
        String sql = "SELECT * FROM FINDBOARDPOST WHERE userID = ? ORDER BY findpostID DESC";
        jdbcUtil.setSqlAndParameters(sql, new Object[]{userID});

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            while (rs.next()) {
                FindDTO post = new FindDTO(
                    rs.getInt("findpostID"),
                    rs.getString("isAnonymous"),
                    rs.getString("title"),
                    rs.getString("prefer"),
                    rs.getString("mycontent"),
                    rs.getString("matecontent"),
                    rs.getString("userID")
                );
                posts.add(post);
            }
        } catch (SQLException ex) {
            log.error("Error fetching user posts", ex);
            throw ex;
        } finally {
            jdbcUtil.close();
        }
        return posts;
    }

	
	/*** 전체 구인 게시글 정보를 검색하여 List에 저장 및 반환	 */
	public List<FindDTO> totalFindList() throws SQLException {

        String sql = "SELECT * "
        		   + "FROM FINDBOARDPOST "
        		   + "ORDER BY findpostID";        
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
	public int totalPosts() throws SQLException {
	    String sql = "SELECT count(findpostID) FROM FINDBOARDPOST";
	    jdbcUtil.setSqlAndParameters(sql, null); // JDBCUtil에 query문 설정

	    try {
	        ResultSet rs = jdbcUtil.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1); // 결과로부터 게시글 수를 반환
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    } finally {
	        jdbcUtil.close(); // resource 반환
	    }
	    return 0; // 오류가 발생하거나 결과가 없는 경우 0을 반환
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
	public int remove(int postID) throws SQLException {
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
	public List<FindDTO> search(String keyword) throws SQLException {
	    String sql = "SELECT * FROM FINDBOARDPOST WHERE title LIKE ? OR prefer LIKE ? OR mycontent LIKE ? OR matecontent LIKE ?";
	    String searchTerm = "%" + keyword + "%";

	    jdbcUtil.setSqlAndParameters(sql, new Object[] { searchTerm, searchTerm, searchTerm, searchTerm });
	    log.debug("serch query 실행 완료");
	    try {
	        ResultSet rs = jdbcUtil.executeQuery();
	        List<FindDTO> searchResults = new ArrayList<>();

	        while (rs.next()) {
	            FindDTO post = new FindDTO(
	                rs.getInt("findpostID"),
	                rs.getString("isAnonymous"),
	                rs.getString("title"),
	                rs.getString("prefer"),
	                rs.getString("mycontent"),
	                rs.getString("matecontent"),
	                rs.getString("userID")
	            );

	            searchResults.add(post);
	        }
	        log.debug("serchresult 실행 완료");
	        return searchResults;
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    } finally {
	        jdbcUtil.close();
	    }

	    return Collections.emptyList(); // 검색 결과가 없을 때 빈 리스트를 반환
	}
	

}