package model.manager;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.RegisterUserController;
import model.dao.UserDAO;
import model.dto.LifePattern;
import model.dto.User;

/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * UserDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
 */
public class UserManager {
    private static final Logger log = LoggerFactory.getLogger(UserManager.class);
	private static UserManager userMan = new UserManager();
	private UserDAO userDAO;

	private UserManager() {
		try {
			userDAO = new UserDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static UserManager getInstance() {
		return userMan;
	}
	
	public int create(User user) throws SQLException, ExistingUserException {
		if (userDAO.existingUser(user.getUserId()) == true) {
			throw new ExistingUserException(user.getUserId() + "는 존재하는 아이디입니다.");
		}
		return userDAO.create(user);
	}
	
    public int createLifePattern(LifePattern lifePattern, User user) throws SQLException, ExistingUserException {
        return userDAO.createLifePattern(lifePattern, user);
    }

	public int updateUser(User user) throws SQLException, UserNotFoundException {
		return userDAO.updateUser(user);
	}
	
	public int updateLifePattern(String userId, LifePattern lifePattern, User user) throws SQLException {
	    userDAO.removeLifePattern(userId);
	    return userDAO.createLifePattern(lifePattern, user);
	}

	public int removeUser(String userId) throws SQLException, UserNotFoundException {
		return userDAO.removeUser(userId);
	}
	
    public User findUser(String userId)
            throws SQLException, UserNotFoundException {
            User user = userDAO.findUser(userId);
            
            if (user == null) {
                throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
            }       
            return user;
    }
    
    public LifePattern findLifePattern(String userId)
            throws SQLException, UserNotFoundException {
            LifePattern lifePattern = userDAO.findLifePattern(userId);
            
            if (lifePattern == null) {
                throw new UserNotFoundException(userId + "의 생활패턴은 존재하지 않습니다.");
            }
            return lifePattern;
    }

	public boolean login(String userId, String password)
		throws SQLException, UserNotFoundException, PasswordMismatchException {
		User user = findUser(userId);
		
		log.debug(userId + " " + password);

		if (!user.matchPassword(password)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return true;
	}
	
	public UserDAO getUserDAO() {
		return this.userDAO;
	}
}