package model.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommentDTO {
    // 댓글 식별자
    private int commentID;

    // 댓글 내용
    private String content;

    // 댓글 작성일
    private LocalDateTime commentDate;

    // 댓글 작성자 식별자
    private String userID;

    // 댓글이 속한 콘텐츠(게시글) 식별자
    private int freepostID; // 짝수
    private int findpostID; // 홀수, 
    

    // 생성자
    public CommentDTO(int commentID, String content, LocalDateTime commentDate, String userID, int freepostID, int findpostID) {
        this.commentID = commentID;
        this.content = content;
        this.commentDate = commentDate;
        this.userID = userID;
        this.freepostID = freepostID;
        this.findpostID = findpostID;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
    }
    
 // 날짜/시각 값을 문자열로 변환하여 반환하는 메서드
    public String getCreateAtFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return commentDate.format(formatter);
    }

    // 문자열로부터 날짜/시각 값을 파싱하여 commentDate 필드에 설정하는 메서드
    public void setCreateAtFromString(String commentDateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.commentDate = LocalDateTime.parse(commentDateStr, formatter);
    }
    

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    public int getFreepostID() { return freepostID; }
    public void setFreepostID(int freepostID) {

    	if (freepostID % 2 != 0) {
            throw new IllegalArgumentException("FreepostID must be even");
        }
        this.freepostID = freepostID;
    }

	public int getFindpostID() { return findpostID; }
    public void setFindpostID(int findpostID) {

        if (findpostID % 2 == 0) {
            throw new IllegalArgumentException("FindpostID must be odd");
        }
        this.findpostID = findpostID;
    }
    // toString 메서드
    @Override
    public String toString() {
        return "CommentDTO{" +
                "commentID='" + commentID + '\'' +
                ", content='" + content + '\'' +
                ", commentDate=" + getCreateAtFormatted() +  
                ", userID='" + userID + '\'' +
                '}';
    }
}
