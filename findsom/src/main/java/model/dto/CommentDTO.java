package model.dto;

import java.util.Date;

public class CommentDTO {
    // 댓글 식별자
    private String commentID;

    // 댓글 내용
    private String content;

    // 댓글 작성일
    private Date commentDate;

    // 댓글 작성자 식별자
    private String userID;

    // 댓글이 속한 콘텐츠(게시글) 식별자
    private String postID;

    // 생성자
    public CommentDTO(String commentID, String content, Date commentDate, String userID, String postID) {
        this.commentID = commentID;
        this.content = content;
        this.commentDate = commentDate;
        this.userID = userID;
        this.postID = postID;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    // toString 메서드
    @Override
    public String toString() {
        return "CommentDTO{" +
                "commentID='" + commentID + '\'' +
                ", content='" + content + '\'' +
                ", commentDate=" + commentDate +
                ", userID='" + userID + '\'' +
                ", postID='" + postID + '\'' +
                '}';
    }
}
