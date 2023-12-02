package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;
import model.LifePattern;

/**
 * 사용자 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스 USERINFO 테이블에 사용자 정보를 추가, 수정, 삭제, 검색 수행
 */
public class UserDAO {
    private JDBCUtil jdbcUtil = null;

    public UserDAO() {
        jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
    }

    /**
     * 사용자 관리 테이블에 새로운 사용자 생성.
     */
    public int create(User user) throws SQLException {
        String sql = "INSERT INTO USERINFO VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] param = new Object[] { user.getUserId(), user.getEmail(), user.getPassword(), user.getPhone(),
                user.getName(), user.getNickname(), user.getAuthentication(), user.isRecruite(),
                (!user.getRoomInfo().equals("배정받지 않음")) ? user.getRoomInfo() : null };
        jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil 에 insert문과 매개 변수 설정

        try {
            int result = jdbcUtil.executeUpdate(); // insert 문 실행
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
     * 기존의 사용자 정보를 수정.
     */
    public int update(User user) throws SQLException {
        String sql = "UPDATE USERINFO "
                + "SET email=?, password=?, phone=?, name=?, nickname=?, roomInfo=?, lifePatterns=? "
                + "WHERE userid=?";
        Object[] param = new Object[] { user.getEmail(), user.getPassword(), user.getPhone(), user.getName(),
                user.getNickname(), (user.getRoomInfo() != "배정받지 않음") ? user.getRoomInfo() : null };
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
        String sql = "SELECT email, password, name, nickname, isRecruite, lifePatterns " + "FROM USERINFO u "
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

                // Life patterns를 가져올 때는 각 패턴을 LifePattern 객체로 생성하여 리스트에 추가
                List<LifePattern> lifePatterns = new ArrayList<>();
                String lifePatternString = rs.getString("lifePatterns");
                if (lifePatternString != null && !lifePatternString.isEmpty()) {
                    String[] patterns = lifePatternString.split(",");
                    for (String patternString : patterns) {
                        LifePattern pattern = new LifePattern(patternString.trim());
                        lifePatterns.add(pattern);
                    }
                }

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

//    public int insertAuthenticationFile(String userId, byte[] authenticationFile) throws SQLException {
//        String sql = "INSERT INTO AuthenticationFile VALUES (?, ?)";
//        Object[] param = new Object[] { userId, authenticationFile };
//        jdbcUtil.setSqlAndParameters(sql, param);
//
//        try {
//            int result = jdbcUtil.executeUpdate();
//            return result;
//        } catch (Exception ex) {
//            jdbcUtil.rollback();
//            ex.printStackTrace();
//        } finally {
//            jdbcUtil.commit();
//            jdbcUtil.close();
//        }
//        return 0;
//    }
//
//    public int updateAuthenticationFile(String userId, byte[] authenticationFile) throws SQLException {
//        // 재학생 인증 파일을 수정할 일은 없겠지만 작성해둠.
//        String sql = "UPDATE AuthenticationFile SET authenticationFile=? WHERE userId=?";
//        Object[] param = new Object[] { authenticationFile, userId };
//        jdbcUtil.setSqlAndParameters(sql, param);
//
//        try {
//            int result = jdbcUtil.executeUpdate();
//            return result;
//        } catch (Exception ex) {
//            jdbcUtil.rollback();
//            ex.printStackTrace();
//        } finally {
//            jdbcUtil.commit();
//            jdbcUtil.close();
//        }
//        return 0;
//    }
//
//    public int insertLifePattern(String userId, List<LifePattern> lifePatterns) throws SQLException {
//        // LifePattern 객체를 문자열로 변환하여 리스트에 추가
//        List<String> lifePatternStrings = new ArrayList<>();
//        for (LifePattern pattern : lifePatterns) {
//            lifePatternStrings.add(pattern.toString());
//        }
//
//        // 리스트를 콤마로 구분된 문자열로 결합
//        String lifePatternString = String.join(", ", lifePatternStrings);
//
//        String sql = "INSERT INTO LifePatterns VALUES (?, ?)";
//        Object[] param = new Object[] { lifePatternString, userId };
//        jdbcUtil.setSqlAndParameters(sql, param);
//
//        try {
//            int result = jdbcUtil.executeUpdate();
//            return result;
//        } catch (Exception ex) {
//            jdbcUtil.rollback();
//            ex.printStackTrace();
//        } finally {
//            jdbcUtil.commit();
//            jdbcUtil.close();
//        }
//        return 0;
//    }
//
//    public int updateLifePatterns(String userId, List<LifePattern> lifePatterns) throws SQLException {
//        // LifePattern 객체를 문자열로 변환하여 리스트에 추가
//        List<String> lifePatternStrings = new ArrayList<>();
//        for (LifePattern pattern : lifePatterns) {
//            lifePatternStrings.add(pattern.toString());
//        }
//
//        // 리스트를 콤마로 구분된 문자열로 결합
//        String lifePatternString = String.join(", ", lifePatternStrings);
//
//        String sql = "UPDATE LifePatterns SET lifePattern=? WHERE userId=?";
//        Object[] param = new Object[] { lifePatternString, userId };
//        jdbcUtil.setSqlAndParameters(sql, param);
//
//        try {
//            int result = jdbcUtil.executeUpdate();
//            return result;
//        } catch (Exception ex) {
//            jdbcUtil.rollback();
//            ex.printStackTrace();
//        } finally {
//            jdbcUtil.commit();
//            jdbcUtil.close();
//        }
//        return 0;
//    }
}
