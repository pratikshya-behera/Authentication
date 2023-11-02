package service;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender extends GenerateOTP{
    public static void sendOTP(String recipientEmail, String genOTP) {
        final String senderEmail = "onlyforcodetrials@gmail.com";
        final String senderPassword = "rrjj hkgx cdmb dkhq"; 

        //String recipientEmail = "bpratikshya30@gmail.com";

        String smtpHost = "smtp.gmail.com";
        int smtpPort = 587;

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.debug", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("Authentication OTP");
            message.setText(genOTP);

            Transport.send(message);
            System.out.println("Email sent successfully.");

        } catch (AuthenticationFailedException authEx) {
            System.err.println("Authentication failed. Check your email and password.");
            authEx.printStackTrace();
        } catch (MessagingException msgEx) {
            System.err.println("Failed to send email. Check your SMTP server settings.");
            msgEx.printStackTrace();
        } catch (Exception e) {
            System.err.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

