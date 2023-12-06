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
    // 잠버릇 (이갈이, 코골이, 잠귀)
    private String teethGrinding;
    private String snoring;
    private String ear;
    private String hasFriendship; // 친목O 친목X
    private String hasEarphones; // 이어폰O 이어폰X
    private String cleanliness; // 청결도
    private String eatInRoom; // 방 안 취식O 방 안 취식X
    private String age; // 나이
    private String bedPreference; // 침대 1층/2층 선호
    
    public LifePattern() { }       // 기본 생성자  

    public LifePattern(String isMorningPerson, String isSmoker, String employmentPeriod, String mbti, String showerTime,
            String wakeUpTime, String teethGrinding, String snoring, String ear, String hasFriendship,
            String hasEarphones, String cleanliness, String eatInRoom, String age, String bedPreference) {
        this.isMorningPerson = isMorningPerson;
        this.isSmoker = isSmoker;
        this.employmentPeriod = employmentPeriod;
        this.mbti = mbti;
        this.showerTime = showerTime;
        this.wakeUpTime = wakeUpTime;
        this.teethGrinding = teethGrinding;
        this.snoring = snoring;
        this.ear = ear;
        this.hasFriendship = hasFriendship;
        this.hasEarphones = hasEarphones;
        this.cleanliness = cleanliness;
        this.eatInRoom = eatInRoom;
        this.age = age;
        this.bedPreference = bedPreference;
    }

    public String getIsMorningPerson() {
        return isMorningPerson;
    }

    public void setIsMorningPerson(String isMorningPerson) {
        this.isMorningPerson = isMorningPerson;
    }

    public String getIsSmoker() {
        return isSmoker;
    }

    public void setIsSmoker(String isSmoker) {
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

    public String getTeethGrinding() {
        return teethGrinding;
    }

    public void setTeethGrinding(String teethGrinding) {
        this.teethGrinding = teethGrinding;
    }

    public String getSnoring() {
        return snoring;
    }

    public void setSnoring(String snoring) {
        this.snoring = snoring;
    }

    public String getEar() {
        return ear;
    }

    public void setEar(String ear) {
        this.ear = ear;
    }

    public String getHasFriendship() {
        return hasFriendship;
    }

    public void setHasFriendship(String hasFriendship) {
        this.hasFriendship = hasFriendship;
    }

    public String getHasEarphones() {
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

    public String getEatInRoom() {
        return eatInRoom;
    }

    public void setEatInRoom(String eatInRoom) {
        this.eatInRoom = eatInRoom;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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
                + ", teethGrinding=" + teethGrinding + ", snoring=" + snoring + ", ear=" + ear + ", hasFriendship="
                + hasFriendship + ", hasEarphones=" + hasEarphones + ", cleanliness=" + cleanliness + ", eatInRoom="
                + eatInRoom + ", age=" + age + ", bedPreference=" + bedPreference + "]";
    }
}
