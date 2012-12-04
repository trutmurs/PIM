package ch.zhaw.students.mail;

import java.util.Properties;   
import javax.mail.Message; 
import javax.mail.MessagingException; 
import javax.mail.Session; 
import javax.mail.Transport; 
import javax.mail.internet.AddressException; 
import javax.mail.internet.InternetAddress; 
import javax.mail.internet.MimeMessage;   

public class Mail {     
	
	public static void send(MailAccounts acc, String recipient, String subject,             String text) throws AddressException, MessagingException     {         
		// Properties ?ber die Systemeigenschaften anlegen         
		Properties properties = System.getProperties();       
		//Nachtr?glich hinzugef?gt Martin Trutmann 23.05.2012
		properties.put("mail.smtp.starttls.enable","true"); 
		properties.put("mail.smtp.EnableSSL.enable","true");

		properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
		properties.setProperty("mail.smtp.socketFactory.fallback", "false");   
		//properties.setProperty("mail.smtp.port", "465");   
		properties.setProperty("mail.smtp.socketFactory.port", "465"); 
		
		// Server-Adresse hinzugef?gen         
		properties.setProperty("mail.smtp.host", acc.getSmtpHost());           
		// In diesem Fall nicht notwendig (da der Standardport 25 ist), aber         
		// dennoch wissenswert ist das Setzen des Serverports         
		// (f?r den Fall das beispielsweise die E-Mail verschl?sselt versendet werden soll)         
		properties.setProperty("mail.smtp.port", String.valueOf(acc.getPort()));           
		// In der Regel wird nach Authentifizierungsdaten gefragt, weshalb         
		// dies in den Properties expliziert angegeben werden muss         
		properties.setProperty("mail.smtp.auth", "true");           
		// Eine (Standard)Session wird erstellt.         
		// wird keine Authentifizierung ben?tigt, wird "null" als Attribut ?bertragen         
		Session session = Session.getDefaultInstance(properties, acc.getPasswordAuthentication());           
		// Eine neue Nachricht wird erzeugt         
		MimeMessage msg = new MimeMessage(session);           
		// Von wem kommt die E-Mail?         
		msg.setFrom(new InternetAddress(acc.getEmail()));           
		// Wohin soll die Reise gehen?         
		// CC geht beispielsweise an Message.RecipientType.CC         
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient, false));           
		// Betreff         
		msg.setSubject(subject);           
		// Nachricht         
		msg.setText(text);           
		// E-Mail versenden         
		Transport.send(msg);     

		
		
	}
}

