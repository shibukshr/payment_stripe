package src.com.jmail;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailBySite {
 
	
	public void sendCustomMail(){
		
		 System.out.println("message Start successfully...");
  String host="email-smtp.us-east-1.amazonaws.com";
  final String user="AKIAI6OKQNYMHBTS5NZQ";
  final String password="Ali0R0x9BZDGwNPCuEeBYpUjLLXLXT+Xfp+xjLmI8FmM";
  
  String to="shibu@foundingminds.com";
      
   Properties props = new Properties();
   props.put("mail.smtp.host",host);
   props.put("mail.smtp.auth", "true");
   
   Session session = Session.getDefaultInstance(props,
    new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
	return new PasswordAuthentication(user,password);
      }
    });

   
    try {
     MimeMessage message = new MimeMessage(session);
     message.setFrom(new InternetAddress("admin@yearbook.com"));
     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
     message.setSubject("yearbook subject");
     message.setText("yearbook yearbook yearbookyearbook yearbookyearbook yearbook yearbook yearbook yearbook");

     Transport.send(message);

     System.out.println("message sent successfully...");
 
     } catch (MessagingException e) {e.printStackTrace();}
 }
	 public static void main(String[] args) {
		 SendMailBySite test = new SendMailBySite();
			test.sendCustomMail();
		}


}