package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.CommentDTO;

public class CommentDAO {
    private JDBCUtil jdbcUtil;

    public CommentDAO() {
        jdbcUtil = new JDBCUtil();
    }

    public void close() {
        jdbcUtil.close();
    }

    // 댓글 작성
    public void writeComment(CommentDTO comment) throws SQLException {
        String query = "INSERT INTO Comment(commentID, content, commentDate, userID, postID) VALUES (?, ?, ?, ?, ?)";
        Object[] parameters = {comment.getCommentID(), comment.getContent(), comment.getCommentDate(), comment.getUserID(), comment.getPostID()};
        jdbcUtil.setSqlAndParameters(query, parameters);

        try {
            jdbcUtil.executeUpdate();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
    }

    // 댓글 수정
//    public void modifyComment(CommentDTO comment) throws SQLException {
//        String query = "UPDATE Comment SET content=?, commentDate=?, userID=?, postID=? WHERE commentID=?";
//        Object[] parameters = {comment.getContent(), comment.getCommentDate(), comment.getUserID(), comment.getPostID(), comment.getCommentID()};
//        jdbcUtil.setSqlAndParameters(query, parameters);
//
//        try {
//            jdbcUtil.executeUpdate();
//        } catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//            jdbcUtil.commit();
//            jdbcUtil.close();
//        }
//    }

    // 댓글 삭제
//    public void deleteComment(String commentID) throws SQLException {
//        String query = "DELETE FROM Comment WHERE commentID=?";
//        Object[] parameters = {commentID};
//        jdbcUtil.setSqlAndParameters(query, parameters);
//
//        try {
//            jdbcUtil.executeUpdate();
//        } catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//            jdbcUtil.commit();
//            jdbcUtil.close();
//        }
//    }

    // 특정 콘텐츠에 대한 댓글 조회
    public List<CommentDTO> getCommentsForContent(String contentID) throws SQLException {
        String query = "SELECT * FROM Comment WHERE postID=?";
        Object[] parameters = {contentID};
        jdbcUtil.setSqlAndParameters(query, parameters);

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            List<CommentDTO> comments = new ArrayList<>();

            while (rs.next()) {
                CommentDTO comment = new CommentDTO(query, query, null, query, query);
                comment.setCommentID(rs.getString("commentID"));
                comment.setContent(rs.getString("content"));
                comment.setCommentDate(rs.getDate("commentDate"));
                comment.setUserID(rs.getString("userID"));
                comment.setPostID(rs.getString("postID"));

                comments.add(comment);
            }

            return comments;
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
    }

	public void modifyComment(String idx, String comment) {
		// TODO Auto-generated method stub
		
	}
}

