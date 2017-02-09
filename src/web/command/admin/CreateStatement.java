package web.command.admin;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.dao.DBManager;
import persistence.entity.users.User;
import web.command.Command;

/**
 * @author Golubtsov
 * Used to create a statement
 *
 */
public class CreateStatement extends Command{

	DBManager dbManager = DBManager.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String facultyIdString = request.getParameter("faculty");
		int facultyId = Integer.parseInt(facultyIdString);
		String facultyName = dbManager.getFacultyById(facultyId);
		int maxPlace = dbManager.getMaxPlace(facultyId);
		List<User> users = dbManager.createStatement(facultyId, maxPlace);
		
		for(User user : users) {
			send(user.getName(), facultyName, user.getEmail());
		}
		
		return "facultyinfoadmin&faculty=" + facultyId;
	}
	
	public void send(String name, String faculty, String email) {
		final String username = "your gmail";
        final String password = "gmail password";
        String message = name + "! Вы были зачислены на факультет: " + faculty + "!";
        System.out.println(message);
        
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {
            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress("your gmail"));
            mimeMessage.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(email));
            mimeMessage.setSubject("Зачисление");
            mimeMessage.setContent(message, "text/plain; charset=UTF-8");
            
            System.out.println("HELLO: " + message);
            Transport.send(mimeMessage);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
	}

}
