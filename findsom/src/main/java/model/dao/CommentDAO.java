package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.dto.CommentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommentDAO {
    private static final Logger log = LoggerFactory.getLogger(CommentDAO.class);
    private JDBCUtil jdbcUtil = null;

    public CommentDAO() {
        jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
    }

    // 댓글 작성 기능
    public CommentDTO createComment(CommentDTO comment) throws Exception {
        String sql = "INSERT INTO CommentInfo (commentID, content, commentDate, userID, freepostID, findpostID) " + 
                     "VALUES (Sequence_commentID.NEXTVAL, ?, TO_TIMESTAMP(?, 'YYYY-MM-DD HH24:MI:SS'), ?, ?, ?)";
        Object[] param = new Object[] {
            comment.getContent(),
            comment.getCreateAtFormatted(),
            comment.getUserID(),
            comment.getFreepostID() != 0 ? comment.getFreepostID() : null,
            comment.getFindpostID() != 0 ? comment.getFindpostID() : null
        };
        jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil에 insert문과 매개 변수 설정

        String key[] = {"commentID"}; // PK 컬럼 이름
        try {
            jdbcUtil.executeUpdate(key); // insert문 실행
            ResultSet rs = jdbcUtil.getGeneratedKeys();
            if (rs.next()) {
                int generatedKey = rs.getInt(1); // 생성된 PK 값
                comment.setCommentID(generatedKey); // commentID 필드에 저장
            }
            return comment;
        } catch (SQLException ex) {
            log.error("Comment creation failed", ex);
            jdbcUtil.rollback();
            throw ex;
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close(); // resource 반환
        }
    }

 // 댓글 조회 기능
	public List<CommentDTO> freeCommentsByUserID(String userID) throws SQLException {
		List<CommentDTO> comments = new ArrayList<>();
        String sql = "SELECT * FROM CommentInfo WHERE userID = ?";
        jdbcUtil.setSqlAndParameters(sql, new Object[]{userID});

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            while (rs.next()) {
                CommentDTO comment = new CommentDTO(
                    rs.getInt("commentID"),
                    rs.getString("content"),
                    rs.getTimestamp("commentDate").toLocalDateTime(),
                    rs.getString("userID"),
                    rs.getInt("freepostID"),
                    rs.getInt("findpostID")
                );
                comments.add(comment);
            }
        } catch (SQLException ex) {
            log.error("fail", ex);
            throw ex;
        } finally {
            jdbcUtil.close();
        }
        return comments;
	}
    
    public List<CommentDTO> findCommentsByUserID(String userID) throws SQLException {
        List<CommentDTO> comments = new ArrayList<>();
        String sql = "SELECT * FROM CommentInfo WHERE userID = ?";
        jdbcUtil.setSqlAndParameters(sql, new Object[]{userID});

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            while (rs.next()) {
                CommentDTO comment = new CommentDTO(
                    rs.getInt("commentID"),
                    rs.getString("content"),
                    rs.getTimestamp("commentDate").toLocalDateTime(),
                    rs.getString("userID"),
                    rs.getInt("freepostID"),
                    rs.getInt("findpostID")
                );
                comments.add(comment);
            }
        } catch (SQLException ex) {
            log.error("fail", ex);
            throw ex;
        } finally {
            jdbcUtil.close();
        }
        return comments;
    }
  
    
    // 댓글 삭제 기능
    public boolean deleteComment(int commentID) throws Exception {
        String sql = "DELETE FROM CommentInfo WHERE commentID=?";
        jdbcUtil.setSqlAndParameters(sql, new Object[]{commentID});

        try {
            int result = jdbcUtil.executeUpdate();
            return (result > 0);
        } catch (SQLException ex) {
            log.error("Comment delete failed", ex);
            jdbcUtil.rollback();
            throw ex;
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
    }


}
