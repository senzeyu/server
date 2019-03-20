import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendEmail {
    public void send(String serial_ID, String seg_ID, char op_code) {
        final String username = "safetpill477@gmail.com";
        final String password = "Zxczxc123!";

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
        getEmail mail = new getEmail();
        String email_address = mail.get(serial_ID);
        //iterate through string, get all the pill names needed to be taken or refilled

        getPillname pill = new getPillname();
        String pillName = pill.get(serial_ID, seg_ID);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email_address));
            message.setSubject("Testing Subject");
            if(op_code=='t'){
                ((MimeMessage) message).setText("Dear Customer,\n\n"+"It is time to take "+pillName+"\n\nBest,\nECE 477 Team 15");
            }
            else if(op_code == 'f'){
                ((MimeMessage) message).setText("Dear Customer,\n\n"+"It is time to refill "+pillName+"\n\nBest,\nECE 477 Team 15");
            }

            Transport.send(message);
            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}