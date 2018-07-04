package mylas.com.erp.demo.appservices;

import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;


public class EmailSender {
	
	
	
	public JavaMailSender javaMailService(String Username,String Password,String managerEmail, String Message, String Subject) {
	JavaMailSenderImpl sender = new JavaMailSenderImpl();
	SimpleMailMessage message = new SimpleMailMessage();
    sender.setHost("smtp.gmail.com");
    sender.setPort(587);
    sender.setUsername(Username);
    sender.setPassword(Password);
    
    Properties properties = new Properties();
    properties.setProperty("mail.transport.protocol", "smtp");
    properties.setProperty("mail.smtp.auth", Boolean.toString(true));
    properties.setProperty("mail.smtp.starttls.enable", Boolean.toString(true));
    properties.setProperty("mail.debug", Boolean.toString(true));
    properties.setProperty("mail.smtp.port", Integer.toString(465));
    properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    sender.setJavaMailProperties(properties);
    sender.send(new MimeMessagePreparator() {
		   public void prepare(MimeMessage mimeMessage) throws Exception {

			    MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");    
			    mimeMsgHelperObj.setTo(managerEmail);
			    mimeMsgHelperObj.setFrom(Username);    
			    mimeMsgHelperObj.setText(Message);
			    mimeMsgHelperObj.setSubject(Subject);
			   }
			  });
	return sender;
	
	}
}
