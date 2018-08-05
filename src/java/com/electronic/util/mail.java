/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electronic.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Dhannu
 */
public class mail {
    ///--------------mail API vars ------------
    private Properties mailServerProperties;
    private Session getMailSession;
    private MimeMessage generateMailMessage;
    
    //----local vars---------------
    final private String SENDER_MAIL="dhanrajgiri247@gmail.com";
    final private String PASSWORD="kathmandu143";
    final private String SMTP="stmp.gmail.com";
    
    //--------------object vars--------
    private String to;
    private String subject;
    private String emailbody;

    public mail(String to, String subject, String emailbody) {
        this.to = to;
        this.subject = subject;
        this.emailbody = emailbody;
    }
    
    
    public boolean sendMail(){
        try {
            ////--------------setup Mail Server Properrties-----------------
            mailServerProperties=System.getProperties();
            mailServerProperties.put("mail.smtp.port", "587");
            mailServerProperties.put("mail.smtp.auth","true");
            mailServerProperties.put("mail.smtp.starttls.enable","true");
            
            
            ///-------------get Mail Session-------------
            
            getMailSession=Session.getDefaultInstance(mailServerProperties,null);
            generateMailMessage=new MimeMessage(getMailSession);
            generateMailMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            generateMailMessage.setSubject("Your Cart|"+subject);
            emailbody+="<br><br>Your Chat Team<br><a href='#'>Visit us</a>";
            generateMailMessage.setContent(emailbody, "text/html");
            
            
            ///-------------get Session and Send Mail----------------
            Transport transport=getMailSession.getTransport("smtp");
            transport.connect(SMTP,SENDER_MAIL,PASSWORD);
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            
            transport.close();
        } catch (Exception e) {
        }
        return false;
    }
    
    
}
