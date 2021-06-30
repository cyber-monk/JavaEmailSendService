package com.akshay;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Hello world!
 *
 */
public class App  
{
    public static void main( String[] args )
    {
        System.out.println( "Preparing to send email" );
        
        String message = "This message is for security check..";
        String subject = "Subject confirmation";
        String to = "akshayvdeshmukh1@gmail.com";
        String from = "cybermonk24@gmail.com";
        
//      sendEmail(message, subject, to, from);
        sendAttach(message, subject, to, from);
        
    }

    //This method is responsible to send email [WITH ATTACHMENT]
    private static void sendAttach(String message, String subject, String to, String from) {
    	// Variable for gmail host.
    			String host = "smtp.gmail.com";
    			
    			//Get system properties.
    			Properties properties = System.getProperties();
    			System.out.println("Properties:"+properties);
    			
    			//Setting important information to properties object
  			
    			//Host set
    			properties.put("mail.smtp.host",host);
    			properties.put("mail.smtp.port","465");
    			properties.put("mail.smtp.ssl.enable","true");
    			properties.put("mail.smtp.auth","true");
    			
    			//Step 1: To Get Session object..
    			Session session = Session.getInstance(properties, new Authenticator() {
    				@Override
    				protected PasswordAuthentication getPasswordAuthentication() {
    					
    					return new PasswordAuthentication("ENTER YOUR GMAIL EMAIL", "ENTER PASSWORD OF EMAIL");
    				}
    			});
    				
    			session.setDebug(true);    			
    			
    			//Step 2: Compose the message [Text, multimedia]
    			MimeMessage mimeMessage = new MimeMessage(session);
    			
    			try {
    				//From
    				mimeMessage.setFrom(from);
    				
    				//TO - Adding receipent
    				mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    				
    				//Adding Subject message
    				mimeMessage.setSubject(subject);
    				
  				//Attachment..
    				
    				//File path
    				String path = "C:\\Users\\Akshay\\Downloads\\pic1.jpg";
    				
    				MimeMultipart mimeMultipart = new MimeMultipart();
    				//Text  				
    				MimeBodyPart textMime = new MimeBodyPart();
    				//File
    				MimeBodyPart fileMime = new MimeBodyPart();
    			
    				try {
    					textMime.setText(message);
    					
    					File file = new File(path);
    					fileMime.attachFile(file);
    					
    					mimeMultipart.addBodyPart(textMime);
    					mimeMultipart.addBodyPart(fileMime);
    								
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}		
    				mimeMessage.setContent(mimeMultipart);
    				
    			//Step 3: Send message using trnsport class
    				Transport.send(mimeMessage);
    				
    				System.out.println("Mail send successfully...");
    				
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
		
	}

	//This method is responsible to send email [Without Attachment]
	private static void sendEmail(String message, String subject, String to, String from) {

		// Variable for gmail host.
		String host = "smtp.gmail.com";
		
		//Get system properties.
		Properties properties = System.getProperties();
		System.out.println("Properties:"+properties);
		
		//Setting important information to properties object

		
		//Host set
		properties.put("mail.smtp.host",host);
		properties.put("mail.smtp.port","465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");
		
		//Step 1: To Get Session object..
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication("ENTER YOUR GMAIL EMAIL", "ENTER PASSWORD OF EMAIL");
			}
		});
		
		
		session.setDebug(true);
		
		
		//Step 2: Compose the message [Text, multimedia]
		MimeMessage mimeMessage = new MimeMessage(session);
		
		try {
			//From
			mimeMessage.setFrom(from);
			
			//TO - Adding receipent
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			//Adding Subject message
			mimeMessage.setSubject(subject);
			
			//Adding text to message
			mimeMessage.setText(message);
			
		//Step 3: Send message using trnsport class
			Transport.send(mimeMessage);
			
			System.out.println("Mail send successfully...");
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}

	

}
