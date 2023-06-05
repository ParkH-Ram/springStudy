package com.dadada.byeworks.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.socket.WebSocketSession;

import com.dadada.byeworks.member.model.vo.Member;

@Controller
@ServerEndpoint(value="/echo")
public class WebSocketChat {
	
	private static final List<Session> sessionList=new ArrayList<Session>();
    private static final Logger logger = LoggerFactory.getLogger(WebSocketChat.class);
    
    // 1대 1로 보내줄 때 id:session으로 담아줄 map
    // 자꾸 user01만 담아주길래 static final 처리 해버림
    private static final Map<String, Session> userSessionsMap = new HashMap<String, Session>();
    
    // 객체 생성하기
    public WebSocketChat() {
        // TODO Auto-generated constructor stub
    	    
       
    }
    
    // 서버 접속
    @OnOpen
    public void onOpen(Session session) {
    	
        logger.info("Open session id:"+session.getId());
        try {
            final Basic basic=session.getBasicRemote();
            basic.sendText("Connection Established");
        }catch (Exception e) {
            // TODO: handle exception
           
        }
        sessionList.add(session);
        
        // 파라미터로 memberId 값을 받아온다
        String id = session.getRequestParameterMap().get("id").get(0);
        
        userSessionsMap.put(id, session);
    }
    
   
    // 메시지 보내기 (아래 onMessage에서 호출)
    private void sendAllSessionToMessage(Session self,String message) {
        try {
        	// 모두에게 보내는 코드라 주석 처리...
//			 for(Session session : WebSocketChat.sessionList) {
//				if(!self.getId().equals(session.getId())) {
//					session.getBasicRemote().sendText(message.split(",")[1]+" : "+message); 
//				} 
//			}
			
        	// 전달 받은 메시지를 ,를 기준으로 나눈다
        	String[] strs = message.split(",");
        	
        	// [0] cmd:쪽지인지 아닌지
        	// [1] caller:보낸 사람 이름
        	// [2] receiverId:받는 사람 아이디
        	if(strs!=null && strs.length == 3) {
        		String cmd = strs[0];
        		String caller = strs[1];
        		String receiverId = strs[2];
        	
        	
        		// 알람을 받을 사람이 로그인 해서 있다면
        		Session loginSession = userSessionsMap.get(receiverId);
        		
        		// 보내드려
        		// 쪽지고, 로그인 하고 있다면
        		if("note".equals(cmd) && loginSession != null) {
        			String msg = caller + "님으로부터 쪽지가 도착했습니다";
        			loginSession.getBasicRemote().sendText(msg);
        		}
        	}
        }catch (Exception e) {
            // TODO: handle exception
          
        }
    }
    
    // 메시지가 들어왔을 때
    @OnMessage
    public void onMessage(String message,Session session) {
       // 아래 코드가 뭘 하는지 모르겠네...
    	logger.info("Message From "+message.split(",")[1] + ": "+message.split(",")[0]);
        try {
            final Basic basic=session.getBasicRemote();
            basic.sendText("to : "+message);
        }catch (Exception e) {
            // TODO: handle exception
       
        }
        
        // 여기서 메시지 보내나봐
        sendAllSessionToMessage(session, message);
    }
    
    @OnError
    public void onError(Throwable e,Session session) {
  
    }
    
    @OnClose
    public void onClose(Session session) {
        logger.info("Session "+session.getId()+" has ended");
  
        
    	// 이렇게 삭제하는 게 맞는진 모르겠는데.. 넣어준 값에 맞춰서 삭제해야 하니까
    	//if(loginUser == null) {
    	//	userSessionsMap.remove(session.getId());
    	//} else {
    	//	userSessionsMap.remove(loginUser.getMemberId());
    	//}
        String id = session.getRequestParameterMap().get("id").get(0);
        
        userSessionsMap.remove(id);
        sessionList.remove(session);
    }

}
