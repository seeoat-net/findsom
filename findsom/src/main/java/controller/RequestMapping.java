package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.match.MatchController;
import controller.noise.NoiseController;
<<<<<<< HEAD
import controller.notification.CommentController;
import controller.notification.MessageController;
=======
>>>>>>> db76e475bc2d191eccb977f44b093eee6671fe12
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
        mappings.put("/noise", new NoiseController()); 

          // 매칭 게시판 // 
        mappings.put("/match/matching", new MatchController()); 
        mappings.put("/match/matching/detail", new MatchController());
        
        
        // <예림>─────────────────────────────────────────────────────────────────
<<<<<<< HEAD
        mappings.put("/find/findlist", new FindListController());
        mappings.put("/find/findpost", new FindPostController());
        mappings.put("/find/findsearch", new FindSearchContoller());
        mappings.put("/find/findupdate", new FindUpdateController());
        
        mappings.put("/free/freelist", new FreeListController());
        mappings.put("/free/freepost", new FreePostController());
        mappings.put("/free/freeSearch", new FreeSearchController());
        mappings.put("/free/freeUpdate", new FreeUpdateController());     
        
        // <소망>─────────────────────────────────────────────────────────────────
        mappings.put("/notification/message/write", new MessageController());
        mappings.put("/notification/message/view", new MessageController());

        mappings.put("/free/comment", new CommentController());
        mappings.put("/find/comment", new CommentController());
        
        
=======
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

>>>>>>> db76e475bc2d191eccb977f44b093eee6671fe12
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}
