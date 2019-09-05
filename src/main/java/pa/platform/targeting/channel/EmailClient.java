package pa.platform.targeting.channel;

import java.io.IOException;

import org.apache.log4j.Logger;

import pa.platform.core.PaConfiguration;
import pa.platform.model.Notification;

import com.sendgrid.Content;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

public class EmailClient {
	
	private static Logger logger = Logger.getLogger(EmailClient.class);
	
	private Notification notif;
	
	
	public EmailClient(Notification notif) {
		super();
		this.notif = notif;
	}


	public void sendMail(){
		logger.info("notif.getEmailAddress()  " + notif.getEmailAddress());
		logger.info("notif.getFromAddress()  " + notif.getFromAddress());
		logger.info("notif.getNotifcationText()  " + notif.getNotifcationText());

		com.sendgrid.Email from = new com.sendgrid.Email("qapricingstrategy@fishbowl.com");
		com.sendgrid.Email to = new com.sendgrid.Email(notif.getEmailAddress());;
		
	
		Content content = new Content("text/html", notif.getNotifcationText());
		Mail mail = new Mail(from, "test mail", to, content);
		
		SendGrid sg = new SendGrid(PaConfiguration.getInstance().getConfiguration("sendgridapikey"));
		Request request = new Request(); 
		request.setMethod(Method.POST);
		request.setEndpoint("mail/send");
		try {
			request.setBody(mail.build());
			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
