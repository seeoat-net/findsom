package model.manager;


import model.dao.CommentDAO;
import model.dto.CommentDTO;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommentManager {
    private CommentDAO commentDAO;

    public CommentManager() {
        commentDAO = new CommentDAO();
    }

    // 댓글 생성
    public CommentDTO createComment(CommentDTO comment) throws Exception {
        try {
            return commentDAO.createComment(comment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        
    }
    
    // 댓글 조회
    public List<CommentDTO> freeCommentsByUserID(String userID) {
    	try {
            return commentDAO.freeCommentsByUserID(userID);
        } catch (SQLException e) {
            // 오류 처리
            e.printStackTrace();
            return new ArrayList<>(); // 오류가 발생한 경우, 빈 리스트 반환
        }
	}
    public List<CommentDTO> findCommentsByUserID(String userID) {
        try {
            return commentDAO.findCommentsByUserID(userID);
        } catch (SQLException e) {
            // 오류 처리
            e.printStackTrace();
            return new ArrayList<>(); // 오류가 발생한 경우, 빈 리스트 반환
        }
    }


    // 댓글 삭제
    public boolean deleteComment(int commentID) throws Exception {
        try {
            return commentDAO.deleteComment(commentID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //테스트
    public static void main(String[] args) {
    	 CommentManager manager = new CommentManager();
         try {
             // 댓글 생성 테스트   
             LocalDateTime now = LocalDateTime.now();
             CommentDTO newComment = new CommentDTO(1, "솜솜아", now, "c", 2, 3);
             CommentDTO createdComment = manager.createComment(newComment);
             System.out.println("댓글 작성 결과: " + createdComment);

         } catch (Exception e) {
             e.printStackTrace();
         }
     }

	
    



}
