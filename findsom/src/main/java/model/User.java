package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 사용자 관리를 위해 필요한 도메인 클래스. USERINFO 테이블과 대응됨
 */
public class User {
	private String userId;
	private String email;
	private String password;
	private String phone;
	private String name;
	private String nickname;
	private String authentication;
	private String isRecruite;
	private String roomInfo;
	
	public User() { }		// 기본 생성자
	
	public User(String userId, String email, String password, String phone, String name, String nickname, 
	        String authentication, String isRecruite, String roomInfo) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.name = name;
        this.nickname = nickname;
        this.authentication = authentication;
        this.isRecruite = isRecruite;
        this.roomInfo = roomInfo;
    }

    public User(String userId, String email, String password, String phone, String name,
            String nickname, String isRecruite
            , String roomInfo
            ) { // update를 위한
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.name = name;
        this.nickname = nickname;
        this.isRecruite = isRecruite;
        this.roomInfo = roomInfo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = "1";
    }

    public String isRecruite() {
        return isRecruite;
    }

    public void setRecruite(String isRecruite) {
        this.isRecruite = isRecruite;
    }

    public String getRoomInfo() {
        return roomInfo;
    }

    public void setRoomInfo(String roomInfo) {
        this.roomInfo = roomInfo;
    }

    /* 비밀번호 검사 */
	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.password.equals(password);
	}
	
	public boolean isSameUser(String userid) {
        return this.userId.equals(userid);
    }

	@Override
    public String toString() {
        return "User [userId=" + userId + ", email=" + email + ", password=" + password + ", phone=" + phone + ", name="
                + name + ", nickname=" + nickname + ", authentication=" + authentication + ", isRecruite=" + isRecruite
                + ", roomInfo=" + roomInfo + "]";
    }
}
