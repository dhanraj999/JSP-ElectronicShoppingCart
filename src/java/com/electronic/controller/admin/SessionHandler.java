package com.electronic.controller.admin;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author Dhannu
 */
@WebListener
public class SessionHandler implements HttpSessionListener{

    private static int  onlineUsers = 0;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        onlineUsers++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
       onlineUsers--;
    }
    
    public static int getOnlineUsers(){
        return onlineUsers;
    }
    
}
