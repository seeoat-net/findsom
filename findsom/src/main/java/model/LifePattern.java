package model;

import java.util.Arrays;
import java.util.List;

public class LifePattern {
    private String isMorningPerson; // 아침형 저녁형
    private String isSmoker; // 흡연자 비흡연자
    private String employmentPeriod; // 입사 기간 (학기중/방학까지)
    private String mbti;
    private String showerTime; // 샤워시간
    private String wakeUpTime; // 기상시간(알람)
    private List<String> sleepHabits; // 잠버릇 (이갈이, 코골이, 잠귀)
    private String hasFriendship; // 친목O 친목X
    private String hasEarphones; // 이어폰O 이어폰X
    private String cleanliness; // 청결도
    private String eatInRoom; // 방 안 취식O 방 안 취식X
    private int age; // 나이
    private String preferences; // 우대사항
    private String bedPreference; // 침대 1층/2층 선호
    
    public LifePattern() { }       // 기본 생성자  
    
    public LifePattern(String isMorningPerson, String isSmoker, String employmentPeriod, String mbti, String showerTime,
            String wakeUpTime, List<String> sleepHabits, String hasFriendship, String hasEarphones, String cleanliness,
            String eatInRoom, int age, String preferences, String bedPreference) {
        super();
        this.isMorningPerson = isMorningPerson;
        this.isSmoker = isSmoker;
        this.employmentPeriod = employmentPeriod;
        this.mbti = mbti;
        this.showerTime = showerTime;
        this.wakeUpTime = wakeUpTime;
        this.sleepHabits = sleepHabits;
        this.hasFriendship = hasFriendship;
        this.hasEarphones = hasEarphones;
        this.cleanliness = cleanliness;
        this.eatInRoom = eatInRoom;
        this.age = age;
        this.preferences = preferences;
        this.bedPreference = bedPreference;
    }
    
    public LifePattern(String patternString) {
        String[] values = patternString.split(", ");
        if (values.length == 14) {
            this.isMorningPerson = values[0];
            this.isSmoker = values[1];
            this.employmentPeriod = values[2];
            this.mbti = values[3];
            this.showerTime = values[4];
            this.wakeUpTime = values[5];
            this.sleepHabits = Arrays.asList(values[6].split("/"));
            this.hasFriendship = values[7];
            this.hasEarphones = values[8];
            this.cleanliness = values[9];
            this.eatInRoom = values[10];
            this.age = Integer.parseInt(values[11]);
            this.preferences = values[12];
            this.bedPreference = values[13];
        } else {
            // 유효한 형식의 문자열이 아닐 경우 예외처리 또는 기본값으로 초기화
            throw new IllegalArgumentException("Invalid input string format");
        }
    }

    public String isMorningPerson() {
        return isMorningPerson;
    }
    
    public void setMorningPerson(String isMorningPerson) {
        this.isMorningPerson = isMorningPerson;
    }
    
    public String isSmoker() {
        return isSmoker;
    }
    
    public void setSmoker(String isSmoker) {
        this.isSmoker = isSmoker;
    }
    
    public String getEmploymentPeriod() {
        return employmentPeriod;
    }
    
    public void setEmploymentPeriod(String employmentPeriod) {
        this.employmentPeriod = employmentPeriod;
    }
    
    public String getMbti() {
        return mbti;
    }
    
    public void setMbti(String mbti) {
        this.mbti = mbti;
    }
    
    public String getShowerTime() {
        return showerTime;
    }
    
    public void setShowerTime(String showerTime) {
        this.showerTime = showerTime;
    }
    
    public String getWakeUpTime() {
        return wakeUpTime;
    }
    
    public void setWakeUpTime(String wakeUpTime) {
        this.wakeUpTime = wakeUpTime;
    }
    
    public List<String> getSleepHabits() {
        return sleepHabits;
    }
    
    public void setSleepHabits(List<String> sleepHabits) {
        this.sleepHabits = sleepHabits;
    }
    
    public String isHasFriendship() {
        return hasFriendship;
    }
    
    public void setHasFriendship(String hasFriendship) {
        this.hasFriendship = hasFriendship;
    }
    
    public String isHasEarphones() {
        return hasEarphones;
    }
    
    public void setHasEarphones(String hasEarphones) {
        this.hasEarphones = hasEarphones;
    }
    
    public String getCleanliness() {
        return cleanliness;
    }
    
    public void setCleanliness(String cleanliness) {
        this.cleanliness = cleanliness;
    }
    
    public String isEatInRoom() {
        return eatInRoom;
    }
    
    public void setEatInRoom(String eatInRoom) {
        this.eatInRoom = eatInRoom;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public String getPreferences() {
        return preferences;
    }
    
    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }
    
    public String getBedPreference() {
        return bedPreference;
    }
    
    public void setBedPreference(String bedPreference) {
        this.bedPreference = bedPreference;
    }
    
    @Override
    public String toString() {
        return "LifePattern [isMorningPerson=" + isMorningPerson + ", isSmoker=" + isSmoker + ", employmentPeriod="
                + employmentPeriod + ", mbti=" + mbti + ", showerTime=" + showerTime + ", wakeUpTime=" + wakeUpTime
                + ", sleepHabits=" + sleepHabits + ", hasFriendship=" + hasFriendship + ", hasEarphones=" + hasEarphones
                + ", cleanliness=" + cleanliness + ", eatInRoom=" + eatInRoom + ", age=" + age + ", preferences="
                + preferences + ", bedPreference=" + bedPreference + "]";
    }
}
