package model.dao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.RegisterUserController;
import model.User;
import model.LifePattern;

/**
 * 사용자 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스 USERINFO 테이블에 사용자 정보를 추가, 수정, 삭제, 검색 수행
 */
public class UserDAO {
    private static final Logger log = LoggerFactory.getLogger(UserDAO.class);
    
    private JDBCUtil jdbcUtil = null;

    public UserDAO() {
        jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
    }

    /**
     * 사용자 관리 테이블에 새로운 사용자 생성.
     */
    public int create(User user) throws SQLException {
        String sql = "INSERT INTO USERINFO (userid, email, password, phone, name, nickname, authentication, isrecruite, roominfo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] param = new Object[] {
                user.getUserId(), 
                user.getEmail(), 
                user.getPassword(), 
                user.getPhone(),
                user.getName(), 
                user.getNickname(), 
                user.getAuthentication(), 
                user.isRecruite(),
                user.getRoomInfo()
        };
        
        log.debug("dao- create(User user) 실행, object param 넣기 완료");
        
        jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil 에 insert문과 매개 변수 설정
        
        log.debug("dao- jdbcUtil.setSqlAndParameters(sql, param) 완료");
        
        try {
            int result = jdbcUtil.executeUpdate(); // insert 문 실행
            
            log.debug("dao- create의 result에서 insert문 실행 완료");
            
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close(); // resource 반환
        }
        return 0;
    }
    
    /**
     * 생활패턴 관리 테이블에 새로운 생활패턴 생성.
     */
    public int createLifePattern(LifePattern lifePattern, User user) throws SQLException {
        String sql = "INSERT INTO LIFEPATTERNS (userID, lifepattern) VALUES (?, ?)";
        
        ArrayList<String> paramList = new ArrayList<String>();
        String[] param = new String[] {
                lifePattern.getIsMorningPerson(),
                lifePattern.getIsSmoker(),
                lifePattern.getEmploymentPeriod(),
                lifePattern.getMbti(),
                lifePattern.getShowerTime(),
                lifePattern.getWakeUpTime(),
                lifePattern.getTeethGrinding(),
                lifePattern.getSnoring(),
                lifePattern.getEar(),
                lifePattern.getHasFriendship(),
                lifePattern.getHasEarphones(),
                lifePattern.getCleanliness(),
                lifePattern.getEatInRoom(),
                lifePattern.getAge(),
                lifePattern.getBedPreference()
        };
        paramList.addAll(Arrays.asList(param));
        
        log.debug("dao- createLifePattern의 param 값 넣기 완료" + paramList);
        // 리스트에서 모든 null 값 제거
        paramList.removeIf(Objects::isNull);
        
        int result = -1;
        
        if (!paramList.isEmpty()) {
            try {
                for (String pl : paramList) {
                    log.debug(sql + user.getUserId() + pl);
                    
                    jdbcUtil.setSqlAndParameters(sql, new Object[] {user.getUserId(), pl});
                    result = jdbcUtil.executeUpdate();
                    
                    log.debug("createLifePattern에서 insert문 각각 실행중");
                }
            } catch (Exception ex) {
                jdbcUtil.rollback();
                ex.printStackTrace();
            } finally {
                jdbcUtil.commit();
                jdbcUtil.close(); // resource 반환
            }
            
            return result;
        }

        return 0; // 유효한 필드가 없는 경우 0을 반환
    }

    /**
     * 기존의 사용자 정보를 수정.
     */
    public int update(User user) throws SQLException {
        String sql = "UPDATE USERINFO "
                + "SET email=?, password=?, phone=?, name=?, nickname=?, isRecruite=? "
                + "WHERE userid=?";
        Object[] param = new Object[] { 
                user.getEmail(), 
                user.getPassword(), 
                user.getPhone(), 
                user.getName(),
                user.getNickname(),
                user.isRecruite(),
//                (!user.getRoomInfo().equals("배정받지 않음")) ? user.getRoomInfo() : null,
                user.getUserId()
        };
        jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil에 update문과 매개 변수 설정

        try {
            int result = jdbcUtil.executeUpdate(); // update 문 실행
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close(); // resource 반환
        }
        return 0;
    }

    /**
     * 사용자 ID에 해당하는 사용자를 삭제.
     */
    public int remove(String userId) throws SQLException {
        String sql = "DELETE FROM USERINFO WHERE userid=?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] { userId }); // JDBCUtil에 delete문과 매개 변수 설정

        try {
            int result = jdbcUtil.executeUpdate(); // delete 문 실행
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close(); // resource 반환
        }
        return 0;
    }

    /**
     * 주어진 사용자 ID에 해당하는 사용자 정보를 데이터베이스에서 찾아 User 도메인 클래스에 저장하여 반환.
     */
    public User findUser(String userId) throws SQLException {
        String sql = "SELECT email, password, phone, name, nickname, authentication, isRecruite, roomInfo " 
                + "FROM USERINFO u "
                + "WHERE userid=? ";
        jdbcUtil.setSqlAndParameters(sql, new Object[] { userId }); // JDBCUtil에 query문과 매개 변수 설정

        try {
            ResultSet rs = jdbcUtil.executeQuery(); // query 실행
            if (rs.next()) { // 학생 정보 발견
                String email = rs.getString("email");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                String name = rs.getString("name");
                String nickname = rs.getString("nickname");
                String authentication = "1";
                String isRecruite = rs.getString("isRecruite");
                String roomInfo = rs.getString("roomInfo");

                User user = new User( // User 객체를 생성하여 학생 정보를 저장
                        userId, email, password, phone, name, nickname, authentication, isRecruite, roomInfo);

                return user;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close(); // resource 반환
        }
        return null;
    }
    
    /**
     * 주어진 사용자 ID에 해당하는 사용자 정보를 데이터베이스에서 찾아 User 도메인 클래스에 저장하여 반환.
     */
    public LifePattern findLifePattern(String userId) throws SQLException {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT LifePattern ");
            sql.append("FROM LifePatterns ");
            sql.append("WHERE userID = ? ");
            
            jdbcUtil.setSqlAndParameters(sql.toString(), new Object[]{ userId });
            
            ArrayList<LifePattern> lifePatternList = new ArrayList<LifePattern>();
            ResultSet rs = jdbcUtil.executeQuery();
            while (rs.next()) {
                String isMorningPerson = "", isSmoker = "", employmentPeriod = "", mbti = "", 
                       showerTime = "", wakeUpTime = "", teethGrinding = "", snoring = "", ear = "",
                       hasFriendship = "", hasEarphones = "", cleanliness = "", eatInRoom = "", age = "", bedPreference = "";
                
                if (rs.getString("LifePattern").equals("mornig") || rs.getString("LifePattern").equals("night")) {
                    isMorningPerson = rs.getString("LifePattern");
                }
                else if (rs.getString("LifePattern").equals("smoker") || rs.getString("LifePattern").equals("nonSmoker")) {
                    isSmoker = rs.getString("LifePattern");
                }
                else if (rs.getString("LifePattern").equals("semester") || rs.getString("LifePattern").equals("vacation")) {
                    employmentPeriod = rs.getString("LifePattern");
                }
                else if (rs.getString("LifePattern").equals("istj") || rs.getString("LifePattern").equals("isfj") || rs.getString("LifePattern").equals("infj")
                        || rs.getString("LifePattern").equals("intj") || rs.getString("LifePattern").equals("istp") || rs.getString("LifePattern").equals("isfp")
                        || rs.getString("LifePattern").equals("infp") || rs.getString("LifePattern").equals("intp") || rs.getString("LifePattern").equals("estp")
                        || rs.getString("LifePattern").equals("esfp") || rs.getString("LifePattern").equals("enfp") || rs.getString("LifePattern").equals("entp")
                        || rs.getString("LifePattern").equals("estj") || rs.getString("LifePattern").equals("esfj") || rs.getString("LifePattern").equals("enfj")
                        || rs.getString("LifePattern").equals("entj")) {
                    mbti = rs.getString("LifePattern");
                }
                else if (rs.getString("LifePattern").equals("morningShower") || rs.getString("LifePattern").equals("nightShower")) {
                    showerTime = rs.getString("LifePattern");
                }
                else if (rs.getString("LifePattern").equals("one") || rs.getString("LifePattern").equals("nightShower")) {
                    wakeUpTime = rs.getString("LifePattern");
                }
                else if (rs.getString("LifePattern").equals("teethGrinding")) {
                    teethGrinding = rs.getString("LifePattern");
                }
                else if (rs.getString("LifePattern").equals("snoring")) {
                    snoring = rs.getString("LifePattern");
                }
                else if (rs.getString("LifePattern").equals("ear")) {
                    ear = rs.getString("LifePattern");
                }
                else if (rs.getString("LifePattern").equals("yesFriendship") || rs.getString("LifePattern").equals("noFriendship")) {
                    hasFriendship = rs.getString("LifePattern");
                }
                else if (rs.getString("LifePattern").equals("yesEarphones") || rs.getString("LifePattern").equals("noEarphones")) {
                    hasEarphones = rs.getString("LifePattern");
                }
                else if (rs.getString("LifePattern").equals("yesclean") || rs.getString("LifePattern").equals("noclean")) {
                    cleanliness = rs.getString("LifePattern");
                }
                else if (rs.getString("LifePattern").equals("yesEatInRoom") || rs.getString("LifePattern").equals("noEatInRoom")) {
                    eatInRoom = rs.getString("LifePattern");
                }
                else if (rs.getString("LifePattern").equals("20") || rs.getString("LifePattern").equals("21")
                        || rs.getString("LifePattern").equals("22") || rs.getString("LifePattern").equals("23")
                        || rs.getString("LifePattern").equals("24") || rs.getString("LifePattern").equals("25")) {
                    age = rs.getString("LifePattern");
                }
                else if (rs.getString("LifePattern").equals("1") || rs.getString("LifePattern").equals("2")) {
                    bedPreference = rs.getString("LifePattern");
                }
                
                LifePattern lifePattern = new LifePattern(
                        isMorningPerson, isSmoker, employmentPeriod, mbti, showerTime,
                        wakeUpTime, teethGrinding, snoring, ear, hasFriendship, hasEarphones,
                        cleanliness, eatInRoom, age, bedPreference);
                
                return lifePattern;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close(); // resource 반환
        }
        return null;
    }


    /**
     * 주어진 사용자 ID에 해당하는 사용자가 존재하는지 검사
     */
    public boolean existingUser(String userId) throws SQLException {
        String sql = "SELECT count(*) FROM USERINFO WHERE userid=?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] { userId }); // JDBCUtil에 query문과 매개 변수 설정

        try {
            ResultSet rs = jdbcUtil.executeQuery(); // query 실행
            if (rs.next()) {
                int count = rs.getInt(1);
                return (count == 1 ? true : false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close(); // resource 반환
        }
        return false;
    }
}
