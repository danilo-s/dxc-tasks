package it.danilo.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import it.danilo.util.Constants;
import it.danilo.util.MailUtility;

public class EmailServiceImpl implements EmailService {

	@Override
	public void sendEmail(Email email) throws Exception {
		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", MailUtility.getPropertyValue("email.host"));
		// just for testing purposes, using google mail
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "587");

		String username = MailUtility.getPropertyValue("email.username");
		String password = MailUtility.getPropertyValue("email.password");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		// build message based on email configuration
		MimeMessage message = buildMessage(session, email.getBody(), email.getBodyType(),
				MailUtility.getPropertyValue("email.from"), MailUtility.getPropertyValue("email.to"));

		// send the message
		if (!email.isRetry()) {
			try {
				Transport.send(message);
				System.out.println("message sent");
			} catch (MessagingException e) {
				System.out.println("message NOT sent");
				e.printStackTrace();
			}
		} else {
			boolean sent = false;
			int c = 0;
			while (!sent && c < Constants.RETRY_LIMIT) {
				try {
					Transport.send(message);
					sent = true;
					System.out.println("message sent");

				} catch (Exception e) {
					System.out.println("message NOT sent, attempt =" + c);
					c++;
					e.printStackTrace();
				}
			}
			if (!sent) {
				System.out.println("message NOT sent, attempt limit exceeded");
			}
		}
	}

	private MimeMessage buildMessage(Session session, String body, BodyType bodyType, String from, String to)
			throws Exception {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject(MailUtility.getPropertyValue("email.subject"));
		if (bodyType == BodyType.PLAIN_TEXT) {
			message.setText(body);
		} else {
			message.setText(body, "utf-8", "html");
		}
		return message;
	}

}
