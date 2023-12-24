package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.match.MatchController;
import controller.noise.NoiseController;
import controller.notification.CommentController;
import controller.notification.CommentListController;
import controller.notification.MessageController;
import controller.notification.NotificationController;
import controller.notification.PostController;
import controller.find.*;
import controller.free.*;
import controller.user.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
        // <시은>─────────────────────────────────────────────────────────────────
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
        
        mappings.put("/user/mypageMain", new MypageController());

        mappings.put("/user/registerUser", new RegisterUserController());
        mappings.put("/user/registerLifePattern", new RegisterLifePatternController());
  
        mappings.put("/user/updateForm", new UpdateFormController());
        mappings.put("/user/updateUser", new UpdateUserController());
        
        mappings.put("/user/delete", new DeleteUserController());
        
        // <다솔>─────────────────────────────────────────────────────────────────
        // // 쉿!게시판 // // 쉿 게시판 접근 시, roominfo가 있는 사람만! 
        mappings.put("/noise", new NoiseController()); 

          // 매칭 게시판 // 
        mappings.put("/match/matching", new MatchController()); 
        mappings.put("/match/detail", new MatchController());

        
        
        // <예림>─────────────────────────────────────────────────────────────────
        mappings.put("/find/findlist", new FindListController()); //글 목록 
        mappings.put("/find/findpost", new FindPostController()); //글 작성
        mappings.put("/find/findsearch", new FindSearchController());
        mappings.put("/find/findupdate", new FindUpdateController());
        mappings.put("/find/findcheck", new FindCheckPostController());
        
        mappings.put("/free/freelist", new FreeListController());
        mappings.put("/free/freepost", new FreePostController());
        mappings.put("/free/freesearch", new FreeSearchController());
        mappings.put("/free/freeupdate", new FreeUpdateController());  
        mappings.put("/free/freecheck", new FreeCheckPostController());
        mappings.put("/free/freeinfo", new FreeListController());
        mappings.put("/free/freepurchase", new FreeListController());
        mappings.put("/free/freeshare", new FreeListController());
        mappings.put("/free/freeother", new FreeListController());
        
        
        // <소망>─────────────────────────────────────────────────────────────────
        mappings.put("/notification/writeMessage", new CommentListController());
        mappings.put("/notification/messagePost", new MessageController());
        mappings.put("/notification/messageView", new MessageController());
        
        mappings.put("/free/comment", new CommentController());
        mappings.put("/find/comment", new CommentController());
        mappings.put("/notification/post", new PostController());
        mappings.put("/notification/comment", new CommentListController());
        
        //mappings.put("/notification/notiList", new NotificationController());
        
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}