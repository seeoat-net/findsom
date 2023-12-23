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
        log.debug(user.getUserId());
        
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
    public int updateUser(User user) throws SQLException {
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
//                user.getRoomInfo(),
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
    public int removeUser(String userId) throws SQLException {
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
    
    public int removeLifePattern(String userId) throws SQLException {
        String sql = "DELETE FROM LIFEPATTERNS WHERE userid=?";
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
            sql.append("SELECT LIFEPATTERN ");
            sql.append("FROM LIFEPATTERNS ");
            sql.append("WHERE USERID=? ");

            jdbcUtil.setSqlAndParameters(sql.toString(), new Object[]{userId});

            ResultSet rs = jdbcUtil.executeQuery();
            LifePattern lifePattern = new LifePattern();  // 객체 생성

            while (rs.next()) {
                String pattern = rs.getString("LifePattern");

                // 각 컬럼에 따라서 LifePattern 객체에 데이터를 저장
                switch (pattern) {
                    case "morning": case "night":
                        lifePattern.setIsMorningPerson(pattern);
                        break;
                    case "smoker": case "nonSmoker":
                        lifePattern.setIsSmoker(pattern);
                        break;
                    case "semester": case "vacation":
                        lifePattern.setEmploymentPeriod(pattern);
                        break;
                    case "istj": case "isfj": case "infj": case "intj": case "istp": case "isfp": case "infp": case "intp":
                    case "estp": case "esfp": case "enfp": case "entp": case "estj": case "enfj": case "entj": case "esfj":
                        lifePattern.setMbti(pattern);
                        break;
                    case "morningShower": case "nightShower":
                        lifePattern.setShowerTime(pattern);
                        break;
                    case "one": case "many":
                        lifePattern.setWakeUpTime(pattern);
                        break;
                    case "teethGrinding":
                        lifePattern.setTeethGrinding(pattern);
                        break;
                    case "snoring":
                        lifePattern.setSnoring(pattern);
                        break;
                    case "ear":
                        lifePattern.setEar(pattern);
                        break;
                    case "yesFriendship": case "noFriendship":
                        lifePattern.setHasFriendship(pattern);
                        break;
                    case "yesEarphones": case "noEarphones":
                        lifePattern.setHasEarphones(pattern);
                        break;
                    case "yesclean": case "noclean":
                        lifePattern.setCleanliness(pattern);
                        break;
                    case "yesEatInRoom": case "noEatInRoom":
                        lifePattern.setEatInRoom(pattern);
                        break;
                    case "20": case "21": case "22": case "23": case "24": case "25":
                        lifePattern.setAge(pattern);
                        break;
                    case "1": case "2":
                        lifePattern.setBedPreference(pattern);
                        break;
                    default:
                        break;
                }
            }

            return lifePattern;  // 루프가 끝난 후에 반환
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
