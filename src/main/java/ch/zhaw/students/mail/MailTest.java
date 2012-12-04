package ch.zhaw.students.mail;

import javax.mail.MessagingException; 
import javax.mail.internet.AddressException;   
public class MailTest {     
	public static void main(String[] args) throws AddressException, MessagingException     {         
		String recipient = "trutmurs@zhaw.students.ch";         
		String subject = "E-Mail mit Java erzeugt";         
		String text = "Dies ist ein Test Email";           
		
		Mail.send(MailAccounts.GOOGLEMAIL, recipient, subject, text);     } } 

