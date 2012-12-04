package ch.zhaw.students.mail;

public enum MailAccounts {     
	
	
	// Hier bitte die ensprechenden Attribute jeweils anpassen     
	GMX("mail.gmx.net", 25, "login", "passwort", "absender"),     
	GOOGLEMAIL("smtp.googlemail.com", 25, "trutdev@gmail.com", "Au$tral1a", "trutdev@gmail.com"),     
	// Hier k�nnen weitere E-Mail Accounts als ENUM angelegt werden     
	;       
	
	private String smtpHost;     
	private int port;     
	private String username;     
	private String password;     
	private String email;       
	
	
	/**      
	 * Setzt die notwendigen Attribute des MailAccounts      
	 * @param smtpHost - SMTP Host      
	 * @param port - Port      
	 * @param username - Benutzername      
	 * @param password - Passwort      
	 * @param email - Absender E-Mail      
	 * */    
	
	private MailAccounts(String smtpHost, int port,	String username, String password, String email)     {        
		this.smtpHost = smtpHost;         
		this.port = port;         
		this.username = username;         
		this.password = password;         
		this.email = email;     }       
	
	public int getPort()     {         
		return port;     }       
	
	public String getSmtpHost()     {         
		return smtpHost;     }       
	
	public MailAuthenticator getPasswordAuthentication()     {         
		return new MailAuthenticator(username, password);     }       
	
	public String getEmail()     {         
		return email;     }  
}
