package controller.notification;

import model.dao.CommentDAO;
import model.dto.CommentDTO;

import java.sql.SQLException;
import java.util.List;

public class CommentController {
    private CommentDAO commentDAO;

    public CommentController() {
        commentDAO = new CommentDAO();
    }

    // 댓글 작성
    public void writeComment(CommentDTO comment) {
        try {
            commentDAO.writeComment(comment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 댓글 수정
    public void modifyComment(CommentDTO comment) {
        try {
            commentDAO.modifyComment(comment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 댓글 삭제
    public void deleteComment(String commentID) {
        try {
            commentDAO.deleteComment(commentID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 특정 콘텐츠에 대한 댓글 조회
    public List<CommentDTO> getCommentsForContent(String contentID) {
        try {
            return commentDAO.getCommentsForContent(contentID);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
