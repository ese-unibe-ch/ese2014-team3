package pojo;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class MailMail
{

	private Session session = setMailProperties();
	private String from;
	private String recipients;
	private String subject;
	private String text;

 
	public void sendMail(String from, String to, String subject, String msg) throws AddressException, MessagingException {
 
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(recipients));
		message.setSubject(subject);
		message.setText(text);
	
		Transport.send(message);
	
		System.out.println("Done");
	}
	
	public void sendMail() throws AddressException, MessagingException {
		 
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(recipients));
		message.setSubject(subject);
		message.setText(text);
	
		Transport.send(message);
	
		System.out.println("Done");
	}


		public Session getSession() {
			return session;
		}




		public void setSession(Session session) {
			this.session = session;
		}




		public String getFrom() {
			return from;
		}




		public void setFrom(String from) {
			this.from = from;
		}




		public String getRecipients() {
			return recipients;
		}




		public void setRecipients(String recipients) {
			this.recipients = recipients;
		}




		public String getSubject() {
			return subject;
		}




		public void setSubject(String subject) {
			this.subject = subject;
		}




		public String getText() {
			return text;
		}




		public void setText(String text) {
			this.text = text;
		}
		
	
		
		
		private Session setMailProperties() {
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
		
			Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("namibrider@gmail.com","sniper11");
					}
				});
			return session;
		}



}